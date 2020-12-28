(:: pragma bea:global-element-parameter parameter="$customer1" element="ns1:Customer" location="../../consuming-restful-service/xsd/Customer.xsd" ::)
(:: pragma bea:global-element-return element="ns0:CreateNewCustomer" location="../wsdl/CustomerServiceCRM.wsdl" ::)

declare namespace ns1 = "http://www.somecorp.com/customer";
declare namespace ns0 = "http://www.crm.org/CustomerService/";
declare namespace xf = "http://tempuri.org/exposing-restful-service/transformation/HttpPostToSoap2/";

declare function xf:HttpPostToSoap2($customer1 as element(ns1:Customer))
    as element(ns0:CreateNewCustomer) {
        <ns0:CreateNewCustomer>
            <customer>
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
            </customer>
        </ns0:CreateNewCustomer>
};

declare variable $customer1 as element(ns1:Customer) external;

xf:HttpPostToSoap2($customer1)
