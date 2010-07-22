package be.jsams.server.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import be.jsams.server.model.Address;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;

@ContextConfiguration("classpath:ApplicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SocietyServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private SocietyService societyService;

	private Society newSociety = null;

	@Before
	public void setUp() throws Exception {
		newSociety = new Society();
		newSociety.setLabel("LABEL");

		LegalForm form = new LegalForm();
		form.setLabel("Form");
		societyService.createLegalForm(form);
		newSociety.setLegalForm(form);
		
		Address societyAddress = new Address();
		societyAddress.setCity("Brussels");
		societyAddress.setCountry("Belgium");
		societyAddress.setNumber("1B");
		societyAddress.setStreet("Rue Neuve");
		societyAddress.setZipCode(1000);
		newSociety.setAddress(societyAddress);
		
		ContactInformation societyContactInformation = new ContactInformation();
		societyContactInformation.setEmail("x.x@x.com");
		societyContactInformation.setGsm("+32499555444");
		societyContactInformation.setPhone("+322123456");
		newSociety.setContactInformation(societyContactInformation);
		
		String activity = "Activity";
		newSociety.setActivity(activity);
		newSociety.setCapital(new BigDecimal(100.00D));
		newSociety.setResponsible("Binard Eric");
	}

	@Test
	public void testPersist() {
		societyService.create(newSociety);
		List<Society> societies = societyService.findAll();
		assertNotNull(societies);
	}

	@Test
	@Rollback(value = false)
	public void testRemove() {
		societyService.create(newSociety);
		societyService.delete(newSociety);
		List<Society> societies = societyService.findAll();
		assertTrue(societies == null || societies.isEmpty());
	}

	@Test
	public void testUpdate() {
		societyService.create(newSociety);
		newSociety.setLabel("Updated Label");
		societyService.update(newSociety);
		Society foundSociety = societyService.findById(newSociety.getId());
		assertNotNull(foundSociety);
	}

	@Test
	public void testFindById() {
		societyService.create(newSociety);
		Long id = newSociety.getId();
		Society society = societyService.findById(id);
		assertNotNull(society);
	}

	@Test
	public void testFindAll() {
		societyService.create(newSociety);
		List<Society> societies = societyService.findAll();
		assertTrue(societies != null && !societies.isEmpty());
	}

}