/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttso.demo.jigger;

import buttso.demo.jigger.model.WebLogic;
import javax.xml.bind.JAXBException;
import org.junit.Assert;

import org.junit.Test;

/**
 *
 * @author sbutton
 */
public class ParserTest extends BaseTest {
    
    @Test
    public void testDevConfig() throws JAXBException {

        WebLogic weblogic = getWebLogic("dev.json");
        
        Assert.assertNotNull(weblogic);
        Assert.assertNotNull(weblogic.getDomain());
        Assert.assertNotNull(weblogic.getAdmin());
        Assert.assertTrue("dev".equals(weblogic.getDomain().getName()));
        Assert.assertTrue("admin should have default listenAddress of 127.0.0.1", "127.0.0.1".equals(weblogic.getAdmin().getListenAddress   ()));
        Assert.assertTrue("admin should have default listenPort of 7001", "7001".equals(weblogic.getAdmin().getListenPort()));        
        Assert.assertTrue("admin user should be weblogic", "weblogic".equals(weblogic.getAdmin().getUser()));
        Assert.assertTrue("password user should be weblogic123", "welcome123".equals(weblogic.getAdmin().getPassword()));
    }
    
    @Test
    public void testFullConfig() throws JAXBException {
       
        WebLogic weblogic = getWebLogic("production.json");
        
        Assert.assertNotNull(weblogic);
        Assert.assertNotNull(weblogic.getDomain());
        Assert.assertNotNull(weblogic.getAdmin());
        Assert.assertTrue("production".equals(weblogic.getDomain().getName()));
        Assert.assertTrue("127.0.0.1".equals(weblogic.getAdmin().getListenAddress()));
        Assert.assertTrue("17001".equals(weblogic.getAdmin().getListenPort()));
        Assert.assertTrue("17443".equals(weblogic.getAdmin().getListenPortSSL()));
        Assert.assertTrue("domain should be in production mode", "prod".equals(weblogic.getDomain().getMode())) ;
        Assert.assertTrue("admin user should be weblogic", "weblogic".equals(weblogic.getAdmin().getUser()));        
        Assert.assertTrue("password user should be a!b@c#d$", "a!b@c#d$".equals(weblogic.getAdmin().getPassword()));
    }
    
}
