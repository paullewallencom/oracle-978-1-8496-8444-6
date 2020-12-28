(:: pragma bea:global-element-parameter parameter="$storeCustomer1" element="ns3:StoreCustomer" location="../wsdl/CustomerManagement.wsdl" ::)
(:: pragma bea:global-element-return element="ns0:CustomerTCollection" location="../adapter/UpdateToDB/xsd/UpdateCustomer_table.xsd" ::)

declare namespace ns2 = "http://www.somecorp.com/customer";
declare namespace ns1 = "http://www.somecorp.com/creditcard";
declare namespace ns3 = "http://www.somecorp.org/CustomerManagement";
declare namespace ns0 = "http://xmlns.oracle.com/pcbpel/adapter/db/top/UpdateCustomer";
declare namespace xf = "http://tempuri.org/using-db-adapter-to-update-to-table/transformation/createUpdateCustomerMsg/";

declare function xf:createUpdateCustomerMsg($storeCustomer1 as element(ns3:StoreCustomer))
    as element(ns0:CustomerTCollection) {
        <ns0:CustomerTCollection>
            <ns0:CustomerT>
                <ns0:id>{ data($storeCustomer1/Customer/ns2:ID) }</ns0:id>
                <ns0:personId>{ data($storeCustomer1/Customer/ns2:ID) }</ns0:personId>
                <ns0:rating>{ data($storeCustomer1/Customer/ns2:Rating) }</ns0:rating>
                <ns0:ccType>{ data($storeCustomer1/Customer/ns2:CreditCard/ns1:CardIssuer) }</ns0:ccType>
                <ns0:ccNumber>{ data($storeCustomer1/Customer/ns2:CreditCard/ns1:CardNumber) }</ns0:ccNumber>
                <ns0:ccExpiration>{ data($storeCustomer1/Customer/ns2:CreditCard/ns1:ExpirationDate) }</ns0:ccExpiration>
                <ns0:ccCvc>{ xs:string($storeCustomer1/Customer/ns2:CreditCard/ns1:CardValidationCode) }</ns0:ccCvc>
            </ns0:CustomerT>
        </ns0:CustomerTCollection>
};

declare variable $storeCustomer1 as element(ns3:StoreCustomer) external;

xf:createUpdateCustomerMsg($storeCustomer1)
