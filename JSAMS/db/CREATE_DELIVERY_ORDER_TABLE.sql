CREATE TABLE BTS.DELIVERY_ORDER
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	CREATION_DATE DATE NOT NULL,
	REMARK VARCHAR(100),
	DISCOUNT_RATE REAL(5,2),
	FK_CUSTOMER BIGINT NOT NULL,
	FK_ADDRESS_DELIVERY BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB;

ALTER TABLE BTS.DELIVERY_ORDER
	ADD FOREIGN KEY (FK_CUSTOMER)
	REFERENCES BTS.CUSTOMER (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;

ALTER TABLE BTS.DELIVERY_ORDER
	ADD FOREIGN KEY (FK_ADDRESS_DELIVERY)
	REFERENCES BTS.ADDRESS (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;