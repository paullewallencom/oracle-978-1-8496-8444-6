(:: pragma bea:global-element-parameter parameter="$retrieveCustomerByCriteriaResponse1" element="ns0:RetrieveCustomerByCriteriaResponse" location="../wsdl/CustomerService.wsdl" ::)
(:: pragma bea:global-element-return element="ns3:FindCustomerResponse" location="../wsdl/CustomerManagement.wsdl" ::)

declare namespace ns2 = "http://www.somecorp.com/customer";
declare namespace ns1 = "http://www.somecorp.com/creditcard";
declare namespace ns3 = "http://www.somecorp.org/CustomerManagement";
declare namespace ns0 = "http://www.crm.org/CustomerService/";
declare namespace xf = "http://tempuri.org/basic-osb-service/transformation/TransformFindCustomerResponse/";

declare function xf:TransformFindCustomerResponse($retrieveCustomerByCriteriaResponse1 as element(ns0:RetrieveCustomerByCriteriaResponse))
    as element(ns3:FindCustomerResponse) {
        <ns3:FindCustomerResponse>
            <Customer>
                <ns2:ID>{ xs:long($retrieveCustomerByCriteriaResponse1/customers/id) }</ns2:ID>
                <ns2:FirstName>{ data($retrieveCustomerByCriteriaResponse1/customers/firstname) }</ns2:FirstName>
                <ns2:LastName>{ data($retrieveCustomerByCriteriaResponse1/customers/lastname) }</ns2:LastName>
                <ns2:EmailAddress>{ data($retrieveCustomerByCriteriaResponse1/customers/emailAddress) }</ns2:EmailAddress>
                <ns2:Addresses>
                    <ns2:Address>
                        <ns2:Street>{ data($retrieveCustomerByCriteriaResponse1/customers/address/street) }</ns2:Street>
                        <ns2:PostalCode>{ data($retrieveCustomerByCriteriaResponse1/customers/address/zipcode) }</ns2:PostalCode>
                        <ns2:City>{ data($retrieveCustomerByCriteriaResponse1/customers/address/city) }</ns2:City>
                    </ns2:Address>
                </ns2:Addresses>
                <ns2:Rating>{ data($retrieveCustomerByCriteriaResponse1/customers/rating) }</ns2:Rating>
                <ns2:Gender>{ data($retrieveCustomerByCriteriaResponse1/customers/gender) }</ns2:Gender>
            </Customer>
        </ns3:FindCustomerResponse>
};

declare variable $retrieveCustomerByCriteriaResponse1 as element(ns0:RetrieveCustomerByCriteriaResponse) external;

xf:TransformFindCustomerResponse($retrieveCustomerByCriteriaResponse1)
