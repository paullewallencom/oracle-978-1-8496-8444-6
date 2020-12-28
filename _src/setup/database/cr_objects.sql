CREATE TABLE customer_t (id NUMBER(16) NOT NULL
			, person_id NUMBER(16)
			, rating VARCHAR2(3)
			, cc_type VARCHAR2(10)
			, cc_number VARCHAR2(20)
			, cc_expiration VARCHAR2(7)
			, cc_cvc VARCHAR2(5));

ALTER TABLE customer_t ADD CONSTRAINT pk_customer_t PRIMARY KEY (id);

CREATE TABLE person_t (id NUMBER(16) NOT NULL
			, first_name VARCHAR2(50) NOT NULL
			, last_name VARCHAR2(50) NOT NULL
			, email VARCHAR2(100)
			, birth_date DATE);

ALTER TABLE person_t ADD CONSTRAINT pk_person_t PRIMARY KEY (id);

CREATE TABLE address_t (id NUMBER(16) NOT NULL
			, person_id NUMBER(16) NOT NULL
			, address_type VARCHAR2(50)
			, street VARCHAR2(50)
			, zip_code VARCHAR2(10)
			, city VARCHAR2(50)
			, country_id NUMBER(16));

ALTER TABLE address_t ADD CONSTRAINT pk_address_t PRIMARY KEY (id);

CREATE TABLE country_t (id NUMBER(16)
			, name VARCHAR2(50)
			, iso_alpha_code VARCHAR2(3)
			, iso_num_code NUMBER(3));

ALTER TABLE country_t ADD CONSTRAINT pk_country_t PRIMARY KEY (id);


ALTER TABLE customer_t ADD CONSTRAINT fk_cus_per FOREIGN KEY (person_id) REFERENCES person_t(id);
ALTER TABLE address_t ADD CONSTRAINT fk_adr_per FOREIGN KEY (person_id) REFERENCES person_t(id);
ALTER TABLE address_t ADD CONSTRAINT fk_adr_cou FOREIGN KEY (country_id) REFERENCES country_t(id);


CREATE OR REPLACE TYPE country_typ AS OBJECT (
		id			NUMBER,
		name			VARCHAR2(50),
		iso_alpha_code		VARCHAR2(3),
		iso_num_code		NUMBER(3));
/

CREATE OR REPLACE TYPE address_typ AS OBJECT (
		id			NUMBER,
		address_type		VARCHAR2(20),
		street			VARCHAR2(50),
		zip_code		VARCHAR2(10),
		city			VARCHAR2(200),
		country			country_typ);
/

CREATE OR REPLACE TYPE address_l AS TABLE OF address_typ;
/

CREATE OR REPLACE TYPE credit_card_typ AS OBJECT (
		card_type		VARCHAR2(10),
		card_number		VARCHAR2(20),
		card_holder_first_name	VARCHAR2(50),
		card_holder_last_name	VARCHAR2(50),
		expiration_date	VARCHAR2(7)
		);
/

CREATE OR REPLACE TYPE customer_typ AS OBJECT (
		id			NUMBER,
		first_name		VARCHAR2(100),
		last_name		VARCHAR2(100),
		email			VARCHAR2(100),
		birth_date		DATE,
		rating			VARCHAR2(3),
		credit_card		credit_card_typ,
		addresses		address_l);
/

CREATE OR REPLACE TYPE event_typ as OBJECT
(
   id NUMBER(19)
   , event_type VARCHAR2(30)
   , event_time TIMESTAMP
);
/

-- Create table
begin
  sys.dbms_aqadm.create_queue_table(
                  queue_table => 'EVENT_QUEUE_T',
                  queue_payload_type => 'OSB_COOKBOOK.EVENT_TYP',
                  sort_list => 'ENQ_TIME',
                  compatible => '10.0.0',
                  primary_instance => 0,
                  secondary_instance => 0,
                  storage_clause => 'tablespace USERS pctfree 10 initrans 1 maxtrans 255 storage ( initial 64K minextents 1 maxextents unlimited )');
end;
/

begin
  sys.dbms_aqadm.create_queue(
                queue_name => 'EVENT_QUEUE',
                queue_table => 'EVENT_QUEUE_T',
                queue_type => sys.dbms_aqadm.normal_queue,
                max_retries => 5,
                retry_delay => 0,
                retention_time => 0);
                
  sys.dbms_aqadm.start_queue( queue_name => 'EVENT_QUEUE', enqueue => TRUE, dequeue => TRUE);
                
end;
/

BEGIN
  sys.dbms_aqadm.create_queue_table(
                queue_table => 'CUSTOMER_QUEUE_T',
                queue_payload_type => 'SYS.XMLTYPE',
                storage_clause => 'PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255',
                sort_list => 'ENQ_TIME',
                compatible => '8.1.3');
END;
/

BEGIN
  sys.dbms_aqadm.create_queue(
                queue_name => 'CUSTOMER_QUEUE',
                queue_table => 'CUSTOMER_QUEUE_T',
                queue_type => sys.dbms_aqadm.normal_queue,
                max_retries => 5,
                retry_delay => 0);
  sys.dbms_aqadm.start_queue( queue_name => 'CUSTOMER_QUEUE', enqueue => TRUE, dequeue => TRUE);
                
END;
/


CREATE SEQUENCE person_seq;
CREATE SEQUENCE customer_seq;
CREATE SEQUENCE address_seq;