/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttso.demo.jigger;

import buttso.demo.jigger.model.WebLogic;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.eclipse.persistence.jaxb.JAXBContextProperties;

/**
 * Who doesn't like a bit of Jiggy.
 *
 * @author sbutton
 */
public class Jigger {

    private static final Logger LOG = Logger.getLogger(Jigger.class.getName());
    private static final String DEFAULT_WEBLOGIC_TEMPLATE = "create-weblogic.ftl";
    private Configuration configuration = null;

    public static void main(String args[]) {
        Jigger jigMe = new Jigger();

        try {
            jigMe.run(args);
        } catch (Exception ex) {
            Logger.getLogger(Jigger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void run(String args[]) throws Exception {
        //TODO: move to some form of cli/validate method
        if (args.length == 0 || args[0] == null || "".equalsIgnoreCase(args[0])) {
            showHelp();
            System.exit(-1);
        } else {
            File f = new File(args[0]);
            if (!f.exists() || !f.canRead()) {
                showError("Can't read %s", args[0]);
                System.exit(-1);
            }
        }

        String configFilename = args[0];

        initialise();
        WebLogic weblogic = parse(configFilename);

        if (System.getProperties().containsKey("wlst.exec")) {
            fillInTemplatetoWLST(DEFAULT_WEBLOGIC_TEMPLATE, weblogic);
        } else {
            fillInTemplatetoStdout(DEFAULT_WEBLOGIC_TEMPLATE, weblogic);
        }
    }

    private void initialise() throws IOException {
        configuration = new Configuration(Configuration.VERSION_2_3_22);
        configuration.setClassForTemplateLoading(getClass(), "/");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public WebLogic parse(String filename) throws JAXBException, FileNotFoundException {
        Map<String, Object> properties = new HashMap<String, Object>(2);
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        JAXBContext jc = JAXBContext.newInstance(new Class[]{WebLogic.class}, properties);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        StreamSource json = new StreamSource(filename);
        WebLogic weblogic = unmarshaller.unmarshal(json, WebLogic.class).getValue();
        weblogic.setDatetime(new Date().toString());
        weblogic.setJsonconfig(filename.substring(filename.lastIndexOf(File.separator) + 1));
        return weblogic;
    }

    private void fillInTemplatetoStdout(String templateName, WebLogic model) throws IOException, TemplateException {
        Template template = configuration.getTemplate(templateName);
        Writer out = new OutputStreamWriter(System.out);
        StringWriter sout = new StringWriter();
        template.process(model, out);
    }

    private void fillInTemplatetoWLST(String templateName, WebLogic model) throws IOException, TemplateException {
        Template template = configuration.getTemplate(templateName);
        StringWriter out = new StringWriter();
        
        template.process(model, out);
        try {
            Class clz = Class.forName("buttso.demo.jigger.JiggerWLST");
            Method execute = clz.getMethod("execute", new Class[] { java.lang.String.class, buttso.demo.jigger.model.Domain.class });
            execute.invoke(null, out.toString(), model.getDomain());
        } catch (Exception ex) {
            Logger.getLogger(Jigger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showHelp() {
        System.err.println("Jigger - generate a WebLogic domain creation script");
        System.err.println("  domain.json : JSON configuration file");
    }

    private void showError(String msg, Object... args) {
        System.err.println("Jigger - generate a WebLogic domain creation script");
        System.err.printf(String.format("  error: %s\n", msg), args);
    }

}
