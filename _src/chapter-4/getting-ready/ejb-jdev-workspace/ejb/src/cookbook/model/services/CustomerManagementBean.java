package cookbook.model.services;

import cookbook.model.entities.Address;
import cookbook.model.entities.CreditCard;
import cookbook.model.entities.Customer;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.ejb.Stateless;


@Stateless(name = "CustomerManagement", mappedName = "CustomerManagementService")
public class CustomerManagementBean implements CustomerManagement {
    public CustomerManagementBean() {
    }

    public Customer findCustomer(Long id){
      Customer customer = new Customer();
      if (id == 100) {
        customer.setId(id);
        customer.setFirstName("Larry");
        customer.setLastName("Ellision");
        customer.setGender("Male");
        customer.setEmailAddress("larry@oracle.com");
        customer.setBirthDate(new Date());
      } else {
        customer.setId(id);
        customer.setFirstName("Larry");
        customer.setLastName("Ellision");
        customer.setGender("Male");
        customer.setEmailAddress("larry@oracle.com");
        customer.setBirthDate(new Date());

        List<Address> addresses = new ArrayList<Address>();
        Address firstAddress = new Address();
        firstAddress.setCity("Redwood shores");
        firstAddress.setStreet("1 Oracle parkway");
        firstAddress.setPostalCode("1234");
        firstAddress.setCountry("US");
        addresses.add(firstAddress);
        customer.setAddresses(addresses);      
      }    
      
      CreditCard creditCard = new CreditCard();
      creditCard.setCardIssuer("VISA");
      creditCard.setCardNumber("12324344");
      creditCard.setCardholderName("Larry");
      creditCard.setCardValidationCode(123L);
      customer.setCreditCard(creditCard);

      return customer;
    }

    public List findAllCustomers() {
      List custs = new ArrayList();
      
      Customer customer = new Customer();
      customer.setId(new Long(1));
      customer.setFirstName("Larry");
      customer.setLastName("Ellision");
      customer.setGender("Male");
      customer.setEmailAddress("larry@oracle.com");
      customer.setBirthDate(new Date());
      
      custs.add(customer);
      
      customer.setId(new Long(1));
      customer.setFirstName("Peter");
      customer.setLastName("Sample");
      customer.setGender("Male");
      customer.setEmailAddress("sample@oracle.com");
      customer.setBirthDate(new Date());

      custs.add(customer);

      return custs;
    }

}
