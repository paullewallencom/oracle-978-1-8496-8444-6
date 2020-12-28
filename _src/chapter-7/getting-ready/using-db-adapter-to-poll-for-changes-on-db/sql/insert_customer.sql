declare
  -- Non-scalar parameters require additional processing 
  customer customer_typ;
begin
  customer := customer_typ(NULL, 'Peter', 'Sample', 'peter.sample@hotmail.com', SYSDATE -200, 'A'
                          , credit_card_typ('VISA', '0123 3232 3232 4343', 'Peter', 'Sample', '07/2015')
                          , NULL);                  -- no addresses
  -- Call the procedure
  customer_pck.save_customer(in_customer => customer);
end;
/
commit;