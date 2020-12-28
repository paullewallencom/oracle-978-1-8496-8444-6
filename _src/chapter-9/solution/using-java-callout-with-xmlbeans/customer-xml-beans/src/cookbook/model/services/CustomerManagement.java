package cookbook.model.services;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import com.somecorp.creditcard.CardIssuerTyp;
import com.somecorp.creditcard.CreditCardDocument;
import com.somecorp.creditcard.CreditCardTyp;
import com.somecorp.customer.CustomerDocument;
import com.somecorp.customer.CustomerTyp;

public class CustomerManagement {
	public static XmlObject findCustomer(long id) throws XmlException {
		String customer = String
				.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
						+ "<tns:Customer xmlns:credit=\"http://www.somecorp.com/creditcard\" xmlns:tns=\"http://www.somecorp.com/customer\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
						+ "  <tns:ID>%s</tns:ID>\n"
						+ "  <tns:FirstName>Cookbook</tns:FirstName>\n"
						+ "  <tns:LastName>XmlObject</tns:LastName>\n"
						+ "  <tns:EmailAddress>osb.cookbook@packt.com</tns:EmailAddress>\n"
						+ "  <tns:Addresses>\n"
						+ "	 <tns:Address>\n"
						+ "		<tns:Street>String</tns:Street>\n"
						+ "		<tns:PostalCode>String</tns:PostalCode>\n"
						+ "		<tns:City>String</tns:City>\n"
						+ "		<tns:Country>String</tns:Country>\n"
						+ "     </tns:Address>\n"
						+ "  </tns:Addresses>\n"
						+ "  <tns:BirthDate>1967-08-13</tns:BirthDate>\n"
						+ "  <tns:Rating>A</tns:Rating>\n"
						+ "  <tns:Gender>String</tns:Gender>\n"
						+ "  <tns:CreditCard>\n"
						+ "	<credit:CardIssuer>visa</credit:CardIssuer>\n"
						+ "	<credit:CardNumber>String</credit:CardNumber>\n"
						+ "	<credit:CardholderName>cookbook user</credit:CardholderName>\n"
						+ "	<credit:ExpirationDate>2012-01-01</credit:ExpirationDate>\n"
						+ "	<credit:CardValidationCode>2147483647</credit:CardValidationCode>\n"
						+ "  </tns:CreditCard>\n" + "</tns:Customer>\n",
						Long.toString(id));

		XmlObject customerXmlObject = XmlObject.Factory.parse(customer);
		return customerXmlObject;
	}

	public static CustomerTyp findCustomerObject(long id) {
		CustomerDocument custDoc = CustomerDocument.Factory.newInstance();
		CustomerTyp customer = custDoc.addNewCustomer();
		customer.setID(id);
		customer.setFirstName("Cookbook");
		customer.setLastName("XmlObject");
		customer.setEmailAddress("osb.cookbook@packt.com");
		customer.setAddresses(null);

		CreditCardDocument creditDoc = CreditCardDocument.Factory.newInstance();
		CreditCardTyp creditCard = creditDoc.addNewCreditCard();
		creditCard.setCardholderName("cookbook user");
		creditCard.setCardIssuer(CardIssuerTyp.VISA);
		customer.setCreditCard(creditCard);
		return customer;
	}

}
