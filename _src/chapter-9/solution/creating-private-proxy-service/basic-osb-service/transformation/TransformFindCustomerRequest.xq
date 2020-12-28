(:: pragma bea:global-element-parameter parameter="$findCustomer1" element="ns1:FindCustomer" location="../wsdl/CustomerManagement.wsdl" ::)
(:: pragma bea:global-element-return element="ns0:RetrieveCustomerByCriteria" location="../wsdl/CustomerService.wsdl" ::)

declare namespace ns1 = "http://www.somecorp.org/CustomerManagement";
declare namespace ns0 = "http://www.crm.org/CustomerService/";
declare namespace xf = "http://tempuri.org/basic-osb-service/transformation/TransformFindCustomerRequest/";

declare function xf:TransformFindCustomerRequest($findCustomer1 as element(ns1:FindCustomer))
    as element(ns0:RetrieveCustomerByCriteria) {
        <ns0:RetrieveCustomerByCriteria>
            <criterias>
                <criteria>
                    <criteriaField>id</criteriaField>
                    <criteriaValue>{ xs:string($findCustomer1/ID) }</criteriaValue>
                </criteria>
            </criterias>
        </ns0:RetrieveCustomerByCriteria>
};

declare variable $findCustomer1 as element(ns1:FindCustomer) external;

xf:TransformFindCustomerRequest($findCustomer1)
