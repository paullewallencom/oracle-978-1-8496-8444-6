(:: pragma bea:global-element-parameter parameter="$retrieveCustomerByCriteriaResponse1" element="ns0:RetrieveCustomerByCriteriaResponse" location="../wsdl/CustomerServiceCRM.wsdl" ::)
(:: pragma bea:global-element-return element="ns1:Customer" location="../xsd/Customer.xsd" ::)

declare namespace ns1 = "http://www.somecorp.com/customer";
declare namespace ns0 = "http://www.crm.org/CustomerService/";
declare namespace xf = "http://tempuri.org/exposing-restful-service/transformation/SoapToHttpGet2/";

declare function xf:SoapToHttpGet2($retrieveCustomerByCriteriaResponse1 as element(ns0:RetrieveCustomerByCriteriaResponse))
    as element(ns1:Customer) {
        <ns1:Customer>
            <ns1:ID>{ xs:long($retrieveCustomerByCriteriaResponse1/customers/id) }</ns1:ID>
            <ns1:FirstName>{ data($retrieveCustomerByCriteriaResponse1/customers/firstname) }</ns1:FirstName>
            <ns1:LastName>{ data($retrieveCustomerByCriteriaResponse1/customers/lastname) }</ns1:LastName>
            <ns1:EmailAddress>{ data($retrieveCustomerByCriteriaResponse1/customers/emailAddress) }</ns1:EmailAddress>
            <ns1:Addresses>
                {
                    let $address := $retrieveCustomerByCriteriaResponse1/customers/address
                    return
                        <ns1:Address>
                            <ns1:Street>{ data($address/street) }</ns1:Street>
                            <ns1:PostalCode>{ data($address/zipcode) }</ns1:PostalCode>
                            <ns1:City>{ data($address/city) }</ns1:City>
                        </ns1:Address>
                }
            </ns1:Addresses>
            <ns1:Rating>{ data($retrieveCustomerByCriteriaResponse1/customers/rating) }</ns1:Rating>
            <ns1:Gender>{ data($retrieveCustomerByCriteriaResponse1/customers/gender) }</ns1:Gender>
        </ns1:Customer>
};

declare variable $retrieveCustomerByCriteriaResponse1 as element(ns0:RetrieveCustomerByCriteriaResponse) external;

xf:SoapToHttpGet2($retrieveCustomerByCriteriaResponse1)
