package cookbook.converter;

import com.bea.wli.sb.transports.ejb.ITypeConverter;

import cookbook.model.entities.Address;
import cookbook.model.entities.Customer;

import java.util.List;

public class AddressConverter implements ITypeConverter{
  public static Address[] convert(List addressList) {
       int listSize = addressList.size();
       Address[] addrArray =  new Address[listSize];
       addrArray = (Address[])addressList.toArray(addrArray);
       return addrArray;
     }
}
