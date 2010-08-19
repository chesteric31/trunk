CREATE TABLE CUSTOMER
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(100) NOT NULL,
	NUM_VAT VARCHAR(50),
	DEFAULT_DISCOUNT_RATE REAL(5,2),
	BANK_1 VARCHAR(100),
	BANK_2 VARCHAR(100),
	CREDIT_LIMIT REAL(12,2),
	APPLICABLE_VAT REAL(5,2),
	DESCRIPTION VARCHAR(100),
	FK_ADDRESS_DELIVERY BIGINT NOT NULL,
	FK_ADDRESS_BILLING BIGINT NOT NULL,
	FK_PAYMENT_MODE BIGINT NOT NULL,
	FK_CONTACT_INFORMATION BIGINT NOT NULL,
	FK_CIVILITY BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB;

ALTER TABLE CUSTOMER
	ADD FOREIGN KEY (FK_ADDRESS_DELIVERY)
	REFERENCES ADDRESS (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;

ALTER TABLE CUSTOMER
	ADD FOREIGN KEY (FK_ADDRESS_BILLING)
	REFERENCES ADDRESS (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;

ALTER TABLE CUSTOMER
	ADD FOREIGN KEY (FK_CIVILITY)
	REFERENCES CIVILITY (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;

ALTER TABLE CUSTOMER
	ADD FOREIGN KEY (FK_CONTACT_INFORMATION)
	REFERENCES CONTACT_INFORMATION (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;

ALTER TABLE CUSTOMER
	ADD FOREIGN KEY (FK_PAYMENT_MODE)
	REFERENCES PAYMENT_MODE (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;