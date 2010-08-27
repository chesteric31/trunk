CREATE TABLE BTS.CREDIT_NOTE_DETAIL
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	QUANTITY INT NOT NULL,
	PRICE REAL(12,2) NOT NULL,
	DESCRIPTION VARCHAR(100),
	VAT_APPLICABLE REAL(5,2),
	FK_CREDIT_NOTE BIGINT NOT NULL,
	FK_BILL_DETAIL BIGINT NOT NULL,
	FK_BILL BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB;

ALTER TABLE BTS.CREDIT_NOTE_DETAIL
	ADD FOREIGN KEY (FK_CREDIT_NOTE)
	REFERENCES BTS.CREDIT_NOTE (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;

ALTER TABLE BTS.CREDIT_NOTE_DETAIL
	ADD FOREIGN KEY (FK_BILL_DETAIL, FK_BILL)
	REFERENCES BTS.BILL_DETAIL (ID, FK_BILL)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;