# WebLogic Domain Creation Script
# ===============================
# Generated: ${datetime}
# From file: ${jsonconfig}

# Open default domain template
# ============================
readTemplate('${domain.oracleHome}/${domain.template}')

# Configure the Administration Server and SSL port.
# =========================================================
cd('Servers/AdminServer')
set('ListenAddress', ${admin.listenAddress})
set('ListenPort', ${admin.listenPort})

<#if admin.listenPortSSL??>
create('AdminServer','SSL')
cd('SSL/AdminServer')
set('Enabled', 'True')
set('ListenPort', ${admin.listenPortSSL})

cd('/Servers/AdminServer/SSL/AdminServer')
cmo.setHostnameVerificationIgnored(true)
cmo.setHostnameVerifier(None)
cmo.setTwoWaySSLEnabled(false)
cmo.setClientCertificateEnforced(false)
</#if>

# Define the user password for weblogic
# =====================================
cd('/')
cd('Security/base_domain/User/${admin.user}')
cmo.setPassword('${admin.password}')

<#if dataSources?size gt 0>
# Define DataSources 
# ==================
<#list dataSources as datasource>
cd('/')
create('${datasource.name}', 'JDBCSystemResource')
cd('JDBCSystemResource/${datasource.name}/JdbcResource/${datasource.name}')
create('myJdbcDriverParams','JDBCDriverParams')
cd('JDBCDriverParams/NO_NAME_0')
set('DriverName','${datasource.driver}')
set('URL','${datasource.url}')
set('PasswordEncrypted', '${datasource.password}')
set('UseXADataSourceInterface', 'false')
create('myProps','Properties')
cd('Properties/NO_NAME_0')
create('user', 'Property')
cd('Property/user')
cmo.setValue('${datasource.user}')

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

</#list>
</#if>

<#if jmsList?size gt 0>
# Define JMS Resources
# ====================
<#list jmsList as jms>
# Add it
</#list>
</#if>

# Enable REST Management API
# Write the domain and close the domain template
# ==============================================
<#if domain.restEnabled??>
cd('/RestfulManagementServices')
set('base_domain','${domain.restEnabled}')
</#if>

setOption('OverwriteDomain', 'true')
setOption('ServerStartMode','${domain.mode}')

writeDomain('${domain.destination}/${domain.name}')
closeTemplate()

# Exit WLST
# =========
exit()
