/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttso.demo.jigger.model;

/**
 * This class represents the configuration values for the AdminServer that is 
 * to be created within the desire domain. 
 * 
 * @author sbutton
 */
public class Admin {
    
    private String listenAddress = "127.0.0.1";
    private String listenPort = "7001";
    private String listenPortSSL;
    private String user;
    private String password;

    /** 
     * The listen address of the admin server.
     * 
     * @return listen address
     */
    public String getListenAddress() {
        return listenAddress;
    }

    public void setListenAddress(String listenAddress) {
        this.listenAddress = listenAddress;
    }

    /** 
     * The listen port of the admin server.
     * 
     * @return listen port
     */
    public String getListenPort() {
        return listenPort;
    }

    public void setListenPort(String listenPort) {
        this.listenPort = listenPort;
    }

    /** 
     * The SSL listen address of the admin server.
     * 
     * @return SSL listen address
     */
    public String getListenPortSSL() {
        return listenPortSSL;
    }

    public void setListenPortSSL(String listenPortSSL) {
        this.listenPortSSL = listenPortSSL;
    }

    /** 
     * The user for the admin server.
     * 
     * @return admin user
     */   
    public String getUser() {
        return user;
    }

    public void setUser(String username) {
        this.user = username;
    }

    /** 
     * The password for the user for the admin server.
     * 
     * @return admin user password
     */
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin: { " + "listenAddress:" + listenAddress + ", listenPort:" + listenPort + ", listenPortSSL:" + listenPortSSL + ", user:" + user + ", password:" + password + " }";
    }
    
    

    
    
}
