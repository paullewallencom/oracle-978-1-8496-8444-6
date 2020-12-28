(:: pragma bea:global-element-parameter parameter="$customer1" element="ns1:Customer" location="../xsd/Customer.xsd" ::)
(:: pragma bea:global-element-return element="ns0:RetrieveCustomerByCriteriaResponse" location="../wsdl/CustomerServiceCRM.wsdl" ::)

declare namespace ns1 = "http://www.somecorp.com/customer";
declare namespace ns0 = "http://www.crm.org/CustomerService/";
declare namespace xf = "http://tempuri.org/consuming-restful-service/transformation/HttpGetToSoap2/";

declare function xf:HttpGetToSoap2($customer1 as element(ns1:Customer))
    as element(ns0:RetrieveCustomerByCriteriaResponse) {
        <ns0:RetrieveCustomerByCriteriaResponse>
            <customers>
                <id>{ xs:int($customer1/ns1:ID) }</id>
                <firstname>{ data($customer1/ns1:FirstName) }</firstname>
                <lastname>{ data($customer1/ns1:LastName) }</lastname>
                <gender>{ data($customer1/ns1:Gender) }</gender>
                <emailAddress>{ data($customer1/ns1:EmailAddress) }</emailAddress>
                <rating>{ data($customer1/ns1:Rating) }</rating>
                <address>
                    <street>{ data($customer1/ns1:Addresses/ns1:Address[1]/ns1:Street) }</street>
                    <zipcode>{ data($customer1/ns1:Addresses/ns1:Address[1]/ns1:PostalCode) }</zipcode>
                    <city>{ data($customer1/ns1:Addresses/ns1:Address[1]/ns1:City) }</city>
                </address>
            </customers>
        </ns0:RetrieveCustomerByCriteriaResponse>
};

declare variable $customer1 as element(ns1:Customer) external;

xf:HttpGetToSoap2($customer1)
