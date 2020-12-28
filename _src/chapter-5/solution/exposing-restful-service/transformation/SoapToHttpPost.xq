(:: pragma bea:global-element-parameter parameter="$createNewCustomerResponse1" element="ns0:CreateNewCustomerResponse" location="../wsdl/CustomerServiceCRM.wsdl" ::)
(:: pragma bea:global-element-parameter parameter="$customer1" element="ns1:Customer" location="../xsd/Customer.xsd" ::)
(:: pragma bea:global-element-return element="ns1:Customer" location="../xsd/Customer.xsd" ::)

declare namespace ns1 = "http://www.somecorp.com/customer";
declare namespace ns0 = "http://www.crm.org/CustomerService/";
declare namespace xf = "http://tempuri.org/exposing-restful-service/transformation/SoapToHttpPost2/";

declare function xf:SoapToHttpPost2($createNewCustomerResponse1 as element(ns0:CreateNewCustomerResponse),
    $customer1 as element(ns1:Customer))
    as element(ns1:Customer) {
        <ns1:Customer>
            <ns1:ID>{ xs:long($createNewCustomerResponse1/id) }</ns1:ID>
            <ns1:FirstName>{ data($customer1/ns1:FirstName) }</ns1:FirstName>
            <ns1:LastName>{ data($customer1/ns1:LastName) }</ns1:LastName>
            <ns1:EmailAddress>{ data($customer1/ns1:EmailAddress) }</ns1:EmailAddress>
            <ns1:Addresses>{ $customer1/ns1:Addresses/@* , $customer1/ns1:Addresses/node() }</ns1:Addresses>
            <ns1:BirthDate>{ data($customer1/ns1:BirthDate) }</ns1:BirthDate>
            <ns1:Rating>{ data($customer1/ns1:Rating) }</ns1:Rating>
            <ns1:Gender>{ data($customer1/ns1:Gender) }</ns1:Gender>
        </ns1:Customer>
};

declare variable $createNewCustomerResponse1 as element(ns0:CreateNewCustomerResponse) external;
declare variable $customer1 as element(ns1:Customer) external;

xf:SoapToHttpPost2($createNewCustomerResponse1,
    $customer1)
