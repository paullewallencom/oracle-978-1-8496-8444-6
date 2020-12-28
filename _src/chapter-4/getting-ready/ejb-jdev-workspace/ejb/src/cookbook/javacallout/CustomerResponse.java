package cookbook.javacallout;

import cookbook.model.entities.Customer;

public class CustomerResponse {
    public CustomerResponse() {
    }

    public static Object enrichCustomerResponse(Object cust ){
      Customer customer = (Customer)cust;
      
      // assign the rating ....
      if (customer.getFirstName().equals("Larry")) {
        customer.setRating("AAA");
      } else {
        customer.setRating("AA");
      }
      
      return customer;
    }
}
