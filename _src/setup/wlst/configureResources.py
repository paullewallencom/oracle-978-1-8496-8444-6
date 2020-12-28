import sys
from java.lang import System

def createDataSource():
	print "Creating datasource OsbCookbookDS..."

	cd('/')
	cmo.createJDBCSystemResource('OsbCookbookDS')
	
	cd('/JDBCSystemResources/OsbCookbookDS/JDBCResource/OsbCookbookDS')
	cmo.setName('OsbCookbookDS')
	
	cd('/JDBCSystemResources/OsbCookbookDS/JDBCResource/OsbCookbookDS/JDBCDataSourceParams/OsbCookbookDS')
	set('JNDINames',jarray.array([String('jdbc.OsbCookbookDS')], String))
	
	cd('/JDBCSystemResources/OsbCookbookDS/JDBCResource/OsbCookbookDS/JDBCDriverParams/OsbCookbookDS')
	cmo.setUrl('jdbc:oracle:thin:@localhost:1521:XE')
	cmo.setDriverName('oracle.jdbc.xa.client.OracleXADataSource')
	setEncrypted('Password', 'Password_1311063258765', 'Script1311063196203Config', 'Script1311063196203Secret')
	
	cd('/JDBCSystemResources/OsbCookbookDS/JDBCResource/OsbCookbookDS/JDBCConnectionPoolParams/OsbCookbookDS')
	cmo.setTestTableName('SQL SELECT 1 FROM DUAL\r\n\r\n')
	
	cd('/JDBCSystemResources/OsbCookbookDS/JDBCResource/OsbCookbookDS/JDBCDriverParams/OsbCookbookDS/Properties/OsbCookbookDS')
	cmo.createProperty('user')
	
	cd('/JDBCSystemResources/OsbCookbookDS/JDBCResource/OsbCookbookDS/JDBCDriverParams/OsbCookbookDS/Properties/OsbCookbookDS/Properties/user')
	cmo.setValue('osb_cookbook')
	
	cd('/JDBCSystemResources/OsbCookbookDS/JDBCResource/OsbCookbookDS/JDBCDataSourceParams/OsbCookbookDS')
	cmo.setGlobalTransactionsProtocol('TwoPhaseCommit')
	
	cd('/SystemResources/OsbCookbookDS')
	set('Targets',jarray.array([ObjectName('com.bea:Name=AdminServer,Type=Server')], ObjectName))

def createJMS():
	print "Creating WebServiceJMSServer..."
	jmsserver1mb = create('OsbCookbookJMSServer','JMSServer')
	jmsserver1mb.addTarget(servermb) 
	jmsserver1mb.setNotes("This JMS server is used by workshop 5")
	
	print "Creating the WebServiceResources JMS Module..."
	jmsMySystemResource = create("OsbCookbookResources","JMSSystemResource")
	jmsMySystemResource.addTarget(servermb) 
	jmsMySystemResource.setNotes("A JMS system module to contain the connection factory and the JMS queue")
    
	print "Creating subdeployment..."
	subDep1mb = jmsMySystemResource.createSubDeployment('OsbCookbookJMSSubdeploy')
	subDep1mb.addTarget(jmsserver1mb)

	theJMSResource = jmsMySystemResource.getJMSResource()
	
	print "Creating connection factory..."
	connfact1 = theJMSResource.createConnectionFactory('OsbCookbookCF')
	connfact1.setJNDIName('jms.OsbCookbookCF')
	connfact1.setLocalJNDIName('local.jms.OsbCookbookCF')
	connfact1.setSubDeploymentName('OsbCookbookJMSSubdeploy')
	connfact1.setNotes("Use the JNDI name to connect to the JMS queue")
	
	print "Creating XA connection factory..."
	connfact1 = theJMSResource.createConnectionFactory('OsbCookbookXACF')
	connfact1.setJNDIName('jms.OsbCookbookXACF')
	connfact1.setLocalJNDIName('local.jms.OsbCookbookXACF')
	connfact1.setSubDeploymentName('OsbCookbookJMSSubdeploy')
	connfact1.setNotes("Use the JNDI name to connect to the JMS queue")
	connfact1.transactionParams.setXAConnectionFactoryEnabled(1)
	
	print "Creating SourceQueue..."
	jmsqueue1 = theJMSResource.createQueue('SourceQueue')
	jmsqueue1.setJNDIName('jms.SourceQueue')
	jmsqueue1.setSubDeploymentName('OsbCookbookJMSSubdeploy')
	
	print "Creating DestinationQueue..."
	jmsqueue1 = theJMSResource.createQueue('DestinationQueue')
	jmsqueue1.setJNDIName('jms.DestinationQueue')
	jmsqueue1.setSubDeploymentName('OsbCookbookJMSSubdeploy')

	print "Creating RequestQueue..."
	jmsqueue1 = theJMSResource.createQueue('RequestQueue')
	jmsqueue1.setJNDIName('jms.RequestQueue')
	jmsqueue1.setSubDeploymentName('OsbCookbookJMSSubdeploy')

	print "Creating ResponseQueue..."
	jmsqueue1 = theJMSResource.createQueue('ResponseQueue')
	jmsqueue1.setJNDIName('jms.ResponseQueue')
	jmsqueue1.setSubDeploymentName('OsbCookbookJMSSubdeploy')

	print "Creating ErrorQueue..."
	jmsqueue1 = theJMSResource.createQueue('ErrorQueue')
	jmsqueue1.setJNDIName('jms.ErrorQueue')
	jmsqueue1.setSubDeploymentName('OsbCookbookJMSSubdeploy')
	
	print "Creating DestinationTopic..."
	jmsqueue1 = theJMSResource.createTopic('DestinationTopic')
	jmsqueue1.setJNDIName('jms.DestinationTopic')
	jmsqueue1.setSubDeploymentName('OsbCookbookJMSSubdeploy')

	
	print "Creating SourceTopic..."
	jmsqueue1 = theJMSResource.createTopic('SourceTopic')
	jmsqueue1.setJNDIName('jms.SourceTopic')
	jmsqueue1.setSubDeploymentName('OsbCookbookJMSSubdeploy')
	
print "Connecting to the server ..."
connect('weblogic','welcome1','t3://localhost:7001')
edit()
startEdit()

servermb=getMBean("Servers/AdminServer")
if servermb is None:
    print 'servermb Value is Null'

else:
	createJMS()
	createDataSource()
   
try:
    save()
    activate(block="true")
    print "script returns SUCCESS"   
except:
    print "Error while trying to save and/or activate!!!"
    dumpStack()
