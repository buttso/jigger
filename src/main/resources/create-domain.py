# WebLogic Domain Creation Script
# ===============================
# Generated: ${wls.datetime}
# From file: ${wls.jsonconfig}

# Open default domain template
# ============================
readTemplate("${wls.domain.oracleHome}/${wls.domain.template}")

# Configure the Administration Server and SSL port.
# =========================================================
cd('Servers/AdminServer')
set('ListenAddress','127.0.0.1')
set('ListenPort', ${wls.admin.listenPort})

<#if wls.admin.listenPortSSL??>
create('AdminServer','SSL')
cd('SSL/AdminServer')
set('Enabled', 'True')
set('ListenPort', 7002)

cd('/Servers/AdminServer/SSL/AdminServer')
cmo.setHostnameVerificationIgnored(true)
cmo.setHostnameVerifier(None)
cmo.setTwoWaySSLEnabled(false)
cmo.setClientCertificateEnforced(false)
</#if>

# Define the user password for weblogic
# =====================================
cd('/')
cd('Security/base_domain/User/${wls.admin.user}')
cmo.setPassword(${wls.admin.password})

<#if wls.jms??>
# Create a JMS Server
# ===================
cd('/')
create('${wls.jms.servername}', 'JMSServer')

# Create a JMS System resource
# ============================
cd('/')
create('myJmsSystemResource', 'JMSSystemResource')
cd('JMSSystemResource/myJmsSystemResource/JmsResource/NO_NAME_0')

# Create a JMS Queue and its subdeployment
# ========================================
myq=create('myQueue','Queue')
myq.setJNDIName('jms/myqueue')
myq.setSubDeploymentName('myQueueSubDeployment')

cd('/')
cd('JMSSystemResource/myJmsSystemResource')
create('myQueueSubDeployment', 'SubDeployment')

# Target resource
cd('/')
assign('JMSServer', 'myJMSServer', 'Target', 'AdminServer')
assign('JMSSystemResource.SubDeployment', 'myJmsSystemResource.myQueueSubDeployment', 'Target', 'myJMSServer')

</#if>

<#if wls.ds??>
# Create and configure a JDBC Data Source, and sets the JDBC user
# ===============================================================
# IF YOU WANT TO HAVE A DEFAULT DATA SOURCE CREATED, UNCOMMENT THIS SECTION BEFORE BUILD

cd('/')
create('myDataSource', 'JDBCSystemResource')
cd('JDBCSystemResource/myDataSource/JdbcResource/myDataSource')
create('myJdbcDriverParams','JDBCDriverParams')
cd('JDBCDriverParams/NO_NAME_0')
set('DriverName','org.apache.derby.jdbc.ClientDriver')
set('URL','jdbc:derby://localhost:1527/db;create=true')
set('PasswordEncrypted', 'PBPUBLIC')
set('UseXADataSourceInterface', 'false')
create('myProps','Properties')
cd('Properties/NO_NAME_0')
create('user', 'Property')
cd('Property/user')
cmo.setValue('PBPUBLIC')

cd('/JDBCSystemResource/${datasource.name}/JdbcResource/${datasource.name}')
create('myJdbcDataSourceParams','JDBCDataSourceParams')
cd('JDBCDataSourceParams/NO_NAME_0')
set('JNDIName', java.lang.String("${datasource.jndi}"))

cd('/JDBCSystemResource/${datasource.name}/JdbcResource/${datasource.name}')
create('myJdbcConnectionPoolParams','JDBCConnectionPoolParams')
cd('JDBCConnectionPoolParams/NO_NAME_0')
set('TestTableName','SYSTABLES')

# Target resource to the servers 
# ==============================
assign('JDBCSystemResource', '${datasource.name}', 'Target', 'AdminServer')
</#if>

# Enable REST Management API
# Write the domain and close the domain template
# ==============================================
cd('/RestfulManagementServices')
set('base_domain','True')

setOption('OverwriteDomain', 'true')
setOption('ServerStartMode','${wls.domain.mode}')

writeDomain('${wls.domain.destination}/${wls.domain.name}')
closeTemplate()

# Exit WLST
# =========
exit()
