package cookbook.converter;

import com.bea.wli.sb.transports.ejb.ITypeConverter;

import cookbook.model.entities.Customer;

import java.util.List;

public class CustomerConverter implements ITypeConverter {
  public static Customer[] convert(List customerList) {
       int listSize = customerList.size();
       Customer[] custArray =  new Customer[listSize];
       custArray = (Customer[])customerList.toArray(custArray);
       return custArray;
     }

   }
