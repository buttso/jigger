# weblogic-with-arquillian

*Author: Steve Button*  

*Date: April 2015*

This project contains a simple utility that generates a WebLogic domain creation script from a JSON config file that describes the domain, such as:

```json
{
    "domain": {
        "oracleHome": "/Users/sbutton/Oracle/Middleware",
        "name": "dev",
        "destination": "/tmp",
        "restEnabled": "true",
        "mode": "dev"
    },
    "admin": {
        "user": "weblogic",
        "password": "welcome123"
    }
}
```
Which in turn will produce the corresponding WLST offline domain creation script, such as:
```py
# WebLogic Domain Creation Script
# ===============================
# Generated: Tue Apr 07 15:59:49 CST 2015
# From file: dev.json

# Open default domain template
# ============================
readTemplate('/Users/sbutton/Oracle/Middleware//wlserver/common/templates/wls/wls.jar')

# Configure the Administration Server and SSL port.
# =========================================================
cd('Servers/AdminServer')
set('ListenAddress', '127.0.0.1')
set('ListenPort', '7001')

# Define the user password for weblogic
# =====================================
cd('/')
cd('Security/base_domain/User/weblogic')
cmo.setPassword('welcome123')

# Enable REST Management API
# Write the domain and close the domain template
# ==============================================
cd('/RestfulManagementServices')
set('base_domain','true')

setOption('OverwriteDomain', 'true')
setOption('ServerStartMode','dev')

writeDomain('/tmp/dev')
closeTemplate()

# End Script
# ==========

```

## Invoking WLST Automatically
Jigger can automatically execute the generated WLST using the following command line, specifying -Dwlst.exec, adding weblogic.jar to the CLASSPATH and directly invoking the Jigger class:

```$java -Dwlst.exec -Dwlst.detail -cp ./jigger-jar-with-dependencies.jar:$ORACLE_HOME/wlserver/server/lib/weblogic.jar buttso.demo.jigger.Jigger dev.json```




