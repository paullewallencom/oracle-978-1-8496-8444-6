package cookbook.model.services;

import cookbook.model.entities.Customer;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface CustomerManagement {

    
    public Customer findCustomer(Long id);
    public List findAllCustomers();

}
