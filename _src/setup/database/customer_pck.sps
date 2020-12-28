CREATE OR REPLACE PACKAGE customer_pck
IS

FUNCTION get_customer(in_customer_id IN customer_t.id%TYPE) RETURN customer_typ;
PROCEDURE save_customer(in_customer IN customer_typ); 

END customer_pck;
/