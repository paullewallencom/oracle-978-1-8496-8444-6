import sys
from java.lang import System

print 'Deleting the system resources ....'
connect('weblogic','welcome1','t3://localhost:7001')
edit()
startEdit()
try:
    jmsMySystemResource = delete("OsbCookbookResources","JMSSystemResource") 
    jmsMyServer1 = delete("OsbCookbookJMSServer","JMSServer") 
except:
    print "##### Error while deleting JMS resources"

try:
    cd('/')
    cmo.destroyJDBCSystemResource(getMBean('/SystemResources/OsbCookbookDS'))
except:
    print "##### Error while deleting JDBC resources"

save()
activate(block="true")
print 'System Resource removed ...'
disconnect()