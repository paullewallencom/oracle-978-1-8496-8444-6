﻿prompt Disabling triggers for COUNTRY_T...
alter table COUNTRY_T disable all triggers;
prompt Disabling triggers for PERSON_T...
alter table PERSON_T disable all triggers;
prompt Disabling triggers for ADDRESS_T...
alter table ADDRESS_T disable all triggers;
prompt Disabling triggers for CUSTOMER_T...
alter table CUSTOMER_T disable all triggers;
prompt Disabling foreign key constraints for ADDRESS_T...
alter table ADDRESS_T disable constraint FK_ADR_COU;
alter table ADDRESS_T disable constraint FK_ADR_PER;
prompt Disabling foreign key constraints for CUSTOMER_T...
alter table CUSTOMER_T disable constraint FK_CUS_PER;
prompt Deleting CUSTOMER_T...
delete from CUSTOMER_T;
commit;
prompt Deleting ADDRESS_T...
delete from ADDRESS_T;
commit;
prompt Deleting PERSON_T...
delete from PERSON_T;
commit;
prompt Deleting COUNTRY_T...
delete from COUNTRY_T;
commit;
prompt Loading COUNTRY_T...
insert into COUNTRY_T (ID, NAME, ISO_ALPHA_CODE, ISO_NUM_CODE)
values (1, 'Switzerland', 'CH', 40);
insert into COUNTRY_T (ID, NAME, ISO_ALPHA_CODE, ISO_NUM_CODE)
values (2, 'Germany', 'GE', 41);
insert into COUNTRY_T (ID, NAME, ISO_ALPHA_CODE, ISO_NUM_CODE)
values (3, 'Netherland', 'NE', 42);
commit;
prompt 3 records loaded
prompt Loading PERSON_T...
insert into PERSON_T (ID, FIRST_NAME, LAST_NAME, EMAIL, BIRTH_DATE)
values (1, 'Peter', 'Sample', 'peter.sample@hotmail.com', to_date('13-06-1968', 'dd-mm-yyyy'));
commit;
prompt 1 records loaded
prompt Loading ADDRESS_T...
insert into ADDRESS_T (ID, PERSON_ID, ADDRESS_TYPE, STREET, ZIP_CODE, CITY, COUNTRY_ID)
values (1, 1, 'HOME', 'Somestreet 101', '9999', 'Somewhere', 1);
commit;
prompt 1 records loaded
prompt Loading CUSTOMER_T...
insert into CUSTOMER_T (ID, PERSON_ID, RATING, CC_TYPE, CC_NUMBER, CC_EXPIRATION, CC_CVC)
values (1, 1, 'A', 'VISA', '1111 1111 1111 1111', '12/2015', '099');
commit;
prompt 1 records loaded
prompt Enabling foreign key constraints for ADDRESS_T...
alter table ADDRESS_T enable constraint FK_ADR_COU;
alter table ADDRESS_T enable constraint FK_ADR_PER;
prompt Enabling foreign key constraints for CUSTOMER_T...
alter table CUSTOMER_T enable constraint FK_CUS_PER;
prompt Enabling triggers for COUNTRY_T...
alter table COUNTRY_T enable all triggers;
prompt Enabling triggers for PERSON_T...
alter table PERSON_T enable all triggers;
prompt Enabling triggers for ADDRESS_T...
alter table ADDRESS_T enable all triggers;
prompt Enabling triggers for CUSTOMER_T...
alter table CUSTOMER_T enable all triggers;
set feedback on
set define on
prompt Done.