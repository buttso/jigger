/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttso.demo.jigger;

import buttso.demo.jigger.model.WebLogic;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.eclipse.persistence.jaxb.JAXBContextProperties;

/**
 *
 * @author sbutton
 */
public abstract class BaseTest {

    WebLogic getWebLogic(String filename) throws JAXBException {
        Unmarshaller unmarshaller = getUnmarshaller();
        StreamSource json = new StreamSource("src" + File.separator + "test" + File.separator + "resources" + File.separator + filename);
        WebLogic weblogic = unmarshaller.unmarshal(json, WebLogic.class).getValue();
        weblogic.setDatetime(new Date().toString());
        weblogic.setJsonconfig(filename.substring(filename.lastIndexOf(File.separator) + 1));
        return weblogic;
    }

    Unmarshaller getUnmarshaller() throws JAXBException {
        Map<String, Object> properties = new HashMap<String, Object>(2);
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        JAXBContext jc = JAXBContext.newInstance(new Class[]{WebLogic.class}, properties);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return unmarshaller;
    }
    
}
