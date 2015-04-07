/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttso.demo.jigger.model;

/**
 *
 * @author sbutton
 */
public class DataSource {
    /*
     "name": "datasource-1",
     "jndi": "jdbc/datasource-1",
     "user": "app",
     "password": "app",
     "uri": "jdbc:oracle:thin://@localhost:1521/demo",
     "driver": "oracle.jdbc.DataSource"
     */
    
    private String name;
    private String jndi;
    private String driver;
    private String url;
    private String user;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJndi() {
        return jndi;
    }

    public void setJndi(String jndi) {
        this.jndi = jndi;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DataSource: { " + "name: " + name + ", jndi: " + jndi + ", driver: " + driver + ", user: " + user + ", password: " + password + " }";
    }

}
