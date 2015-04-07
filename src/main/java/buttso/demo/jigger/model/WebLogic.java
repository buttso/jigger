/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttso.demo.jigger.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class WebLogic {

    private String datetime;
    private String jsonconfig;
    private Domain domain;
    private Admin admin;

    @XmlElement(name = "jms")
    private List<JMS> jmsList = new ArrayList<>();
    @XmlElement(name = "datasource")
    private List<DataSource> dataSources = new ArrayList<>();
    

    public WebLogic() {
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getJsonconfig() {
        return jsonconfig;
    }

    public void setJsonconfig(String jsonconfig) {
        this.jsonconfig = jsonconfig;
    }

    /**
     * The domain specific configuration elements for the desired domain
     * configuration.
     *
     * @return Domain
     */
    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    /**
     * The AdminServer specific configuration elements for the AdminServer
     * within the desired domain configuration.
     *
     * @return Admin
     */
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    /**
     * The JMS specific configuration elements.
     *
     * @return List of JMS configuration elements
     */
    public List<JMS> getJmsList() {
        return jmsList;
    }

    public void setJmsList(List<JMS> jmsList) {
        this.jmsList = jmsList;
    }

    /**
     * The JMS specific configuration elements.
     *
     * @return List of JMS configuration elements
     */
    public List<DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<DataSource> dataSourceList) {
        this.dataSources = dataSources;
    }

    @Override
    public String toString() {
        return "WebLogic: { " + "domain:" + domain + ", admin:" + admin + ", datasource: " + Arrays.deepToString(dataSources.toArray()) + " }";
    }

}
