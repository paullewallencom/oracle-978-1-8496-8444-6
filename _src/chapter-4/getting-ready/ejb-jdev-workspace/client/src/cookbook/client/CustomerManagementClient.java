package cookbook.client;

import cookbook.model.entities.Customer;
import cookbook.model.services.CustomerManagement;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CustomerManagementClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            CustomerManagement customerManagement = 
              (CustomerManagement)context.lookup("CustomerManagementService#cookbook.model.services.CustomerManagement");
//            CustomerManagement customerManagement = 
//                (CustomerManagement)context.lookup("CustomerManagement#cookbook.model.services.CustomerManagement");

            Customer customer = customerManagement.findCustomer(100L);
            printCustomer(customer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://localhost:7001");
        env.put(Context.SECURITY_PRINCIPAL,"weblogic");
        env.put(Context.SECURITY_CREDENTIALS,"welcome1");
        return new InitialContext( env );
    }

    private static void printCustomer(Customer customer) {
        System.out.println( "id = " + customer.getId() );
        System.out.println( "email = " + customer.getEmailAddress() );
        System.out.println( "firstName = " + customer.getFirstName() );
        System.out.println( "lastName = " + customer.getLastName() );
        System.out.println( "gender = " + customer.getGender() );
        System.out.println( "rating = " + customer.getRating());
    }
}
