package be.jsams.server.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.server.dao.BaseJUnitTestClass;
import be.jsams.server.dao.AddressDao;
import be.jsams.server.model.Address;
import be.jsams.server.model.mock.MockModelGenerator;

/**
 * Test class for {@link AddressDaoImpl}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AddressDaoImplTest extends BaseJUnitTestClass {

    @Autowired
    private AddressDao dao;

    private Address address;

    @Before
    public void setUp() {
        address = MockModelGenerator.generateMockAddress();
    }

    @Test
    public void testAdd() {
        dao.add(address);
        assertNotNull(address.getId());
    }

    @Test(expected = EntityExistsException.class)
    public void testAddNullCity() {
        address.setCity(null);
        dao.add(address);
    }

    @Test(expected = EntityExistsException.class)
    public void testAddNullCountry() {
        address.setCountry(null);
        dao.add(address);
    }

    @Test(expected = EntityExistsException.class)
    public void testAddNullNumber() {
        address.setNumber(null);
        dao.add(address);
    }

    @Test(expected = EntityExistsException.class)
    public void testAddNullStreet() {
        address.setStreet(null);
        dao.add(address);
    }

    @Test
    public void testFindAll() {
        int sizeBefore = dao.findAll().size();
        dao.add(address);
        Address anOtherAddress = new Address();
        anOtherAddress.setCity("Brussels");
        anOtherAddress.setCountry("Belgium");
        anOtherAddress.setNumber("1");
        anOtherAddress.setStreet("Rue Neuve");
        anOtherAddress.setZipCode("1000");
        dao.add(anOtherAddress);
        List<Address> addresses = dao.findAll();
        if (sizeBefore > 0) {
            assertTrue(addresses.size() == (sizeBefore + 2));
        } else {
            assertTrue(addresses.size() == 2);
        }
    }

    @Test
    public void testFindById() {
        dao.add(address);
        Address foundAddress = dao.findById(address.getId());
        assertNotNull(foundAddress);
    }

    @Test
    public void testDelete() {
        dao.add(address);
        dao.delete(address);
        Address foundAddress = dao.findById(address.getId());
        assertNull(foundAddress);
    }

    @Test
    public void testUpdate() {
        dao.add(address);
        String fakeNumber = "101";
        address.setNumber(fakeNumber);
        dao.update(address);
        Address foundAddress = dao.findById(address.getId());
        assertTrue(foundAddress.getNumber().equals(fakeNumber));
    }

}
