(:: pragma  parameter="$getCustomer" type="anyType" ::)
(:: pragma bea:global-element-return element="ns0:RetrieveCustomerByCriteriaResponse" location="../wsdl/CustomerServiceCRM.wsdl" ::)

declare namespace ns0 = "http://www.crm.org/CustomerService/";
declare namespace xf = "http://tempuri.org/03_implementing-a-rest-server-with-osb/transformation/HttpPostToSoap_new/";

declare function xf:HttpPostToSoap_new($getCustomer as element(*))
    as element(*) {
        <ns0:RetrieveCustomerByCriteriaResponse>
            <customers>
                <id/>
                <firstname>{ data($getCustomer/firstName) }</firstname>
                <lastname>{ data($getCustomer/lastName) }</lastname>
                <gender>{ data($getCustomer/gender) }</gender>
                <emailAddress>{ data($getCustomer/emailaddress) }</emailAddress>
                <rating>{ data($getCustomer/rating) }</rating>
                <address>
                    <street>{ data($getCustomer/addresses/address[1]/street) }</street>
                    <zipcode>{ data($getCustomer/addresses/address[1]/postalcode) }</zipcode>
                    <city>{ data($getCustomer/addresses/address[1]/city) }</city>
                </address>
            </customers>
        </ns0:RetrieveCustomerByCriteriaResponse>
};

declare variable $getCustomer as element(*) external;

xf:HttpPostToSoap_new($getCustomer)