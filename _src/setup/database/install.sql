DROP USER osb_cookbook CASCADE;

CREATE USER osb_cookbook IDENTIFIED BY osb_cookbook
    DEFAULT TABLESPACE users TEMPORARY TABLESPACE temp;

GRANT CONNECT, RESOURCE TO osb_cookbook;
GRANT CREATE VIEW TO osb_cookbook;
GRANT EXECUTE ON DBMS_AQADM TO osb_cookbook;
GRANT EXECUTE ON DBMS_AQIN TO osb_cookbook;
GRANT EXECUTE ON DBMS_AQ TO osb_cookbook;

CONNECT osb_cookbook/osb_cookbook@xe

@@cr_objects.sql
@@ins_data.sql

@@customer_pck.sps
@@customer_pck.spb
