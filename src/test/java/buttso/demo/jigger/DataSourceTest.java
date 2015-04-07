/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttso.demo.jigger;

import buttso.demo.jigger.model.DataSource;
import buttso.demo.jigger.model.WebLogic;
import javax.xml.bind.JAXBException;
import org.junit.Assert;

import org.junit.Test;

/**
 *
 * @author sbutton
 */
public class DataSourceTest extends BaseTest {

    @Test
    public void testSingleDataSourceConfig() throws JAXBException {
        WebLogic weblogic = getWebLogic("datasource.json");
        System.out.println("");
        System.out.println(weblogic);
        System.out.println("");

        Assert.assertNotNull(weblogic);
        Assert.assertNotNull(weblogic.getDomain());
        Assert.assertNotNull(weblogic.getAdmin());
        Assert.assertNotNull("DataSources should not be null", weblogic.getDataSources() != null);
        Assert.assertTrue(weblogic.getDataSources().size() == 1);
        DataSource ds = weblogic.getDataSources().get(0);
        Assert.assertTrue("datasource-1".equals(ds.getName()));
        Assert.assertTrue("jdbc/datasource-1".equals(ds.getJndi()));
        Assert.assertTrue("jdbc:derby://localhost:1527/demo;create=true".equals(ds.getUrl()));
        Assert.assertTrue("app".equals(ds.getUser()));
        Assert.assertTrue("app".equals(ds.getPassword()));
    }

    @Test
    public void testManyDataSourceConfig() throws JAXBException {
        WebLogic weblogic = getWebLogic("datasource-many.json");
        System.out.println("");
        System.out.println(weblogic);
        System.out.println("");
        Assert.assertNotNull(weblogic);
        Assert.assertNotNull(weblogic.getDomain());
        Assert.assertNotNull(weblogic.getAdmin());
        Assert.assertNotNull("DataSources should not be null", weblogic.getDataSources() != null);
        Assert.assertTrue(weblogic.getDataSources().size() == 2);

        for (DataSource ds : weblogic.getDataSources()) {
            String name = ds.getName();
            Assert.assertTrue(("jdbc/" + name).equals(ds.getJndi()));
            Assert.assertTrue("app".equals(ds.getUser()));
            Assert.assertTrue("app".equals(ds.getPassword()));

        }

    }

}
