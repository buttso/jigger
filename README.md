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
}```




