/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttso.demo.jigger.model;

/**
 * This class represents the configuration values for a Domain that is to be
 * created.  The values represented specifically relate to the settings that can
 * be applied specifically to a domain, such as name, startup mode, etc.  It 
 * does not represent the entire WebLogic domain concept.
 *  
 * @author sbutton
 */
public class Domain {
     
    private final String TEMPLATE_DEFAULT = "wlserver/common/templates/wls/wls.jar";
    
    private String oracleHome;
    private String template = TEMPLATE_DEFAULT;
    private String name;
    private String destination;
    private String mode = "dev";

    /**
     * The OracleHome that his domain will use.
     * 
     * @return ORACLE_HOME to use
     */
    public String getOracleHome() {
        return oracleHome;
    }

    public void setOracleHome(String oracleHome) {
        this.oracleHome = oracleHome;
    }

    /**
     * The WLST template from which to base the domain on.  Defaults to 
     * /wlserver/common/templates/wls/wls.jar.
     * 
     * @return Path of WLST template to use.
     */
    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * Domain name
     * @return name of domain
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Directory location of domain when generated.
     * 
     * @return Path to location of generated domain
     */
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    /**
     * Start up mode of domain - dev or prod, defaults to dev.
     * 
     * @return Start up mode of domain
     */
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "Domain: { " + "oracleHome:" + oracleHome + ", template:" + template + ", name:" + name + ", destination:" + destination + ", mode:" + mode + " }";
    }
    
    
    
}
