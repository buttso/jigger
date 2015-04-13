package buttso.demo.jigger;

import buttso.demo.jigger.model.Domain;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JiggerWLST {

    static Object interpreter = null;
    private static final Logger LOG = Logger.getLogger(JiggerWLST.class.getName());

    private JiggerWLST() {
    }

    public static void execute(String wlst, Domain domain) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {

        LOG.log(Level.INFO, "Executing WLST for domain \"{0}\" to directory \"{1}\"", 
                new Object[] { domain.getName(), domain.getDestination()});

        // Use reflection here so no dev time dependencies on weblogic libraries
        if (interpreter == null) {
            Class clz;
            clz = Class.forName("weblogic.management.scripting.utils.WLSTInterpreter");
            interpreter = clz.newInstance();
        }

        // Output some or all of the WLST input script
        if (System.getProperties().containsKey("wlst.detail")) {
            LOG.log(Level.INFO, "\n>WLST Script\n>>>>>>>>>>\n{0}\n<<<<<<<<<<\n", wlst);
        } else {
            LOG.log(Level.INFO, "\nWLST Script\n>>>>>>>>>>>\n{0}\n<<<<<<<<<<\n", 
                    wlst.substring(0, wlst.length() / 2 > 512 ? 512 : wlst.length()));
        }

        // Invoke the WLST interpreter using reflection
        Method exec = interpreter.getClass().getMethod("exec", new Class[]{java.lang.String.class});
        exec.invoke(interpreter, wlst);

        // Verify the domain was generated 
        if (validateDomain(domain.getDestination(), domain.getName(), "startWebLogic.sh")) {
            LOG.log(Level.INFO, "Successfully created domain \"{0}\" in \"{1}\"",
                    new Object[]{domain.getName(), domain.getDestination() + File.separator + domain.getName()});
        } else {
            LOG.severe("Domain wasn't created as expected");
        }
    }

    /**
     * Validate a domain has been created by checking for existence of expected
     * file.
     *
     * @param domainDirectory
     * @return true if domain has been created
     */
    private static boolean validateDomain(String domainDirectory, String domainName, String expectFile) {
        LOG.log(Level.FINE, "Validating domain generation in {0}/{1}", new Object[] { domainDirectory, domainName });
        File dirToCheck = new File(domainDirectory + File.separator + domainName);
        if (dirToCheck.exists()
                && Arrays.asList(dirToCheck.list()).contains(expectFile)) {
            return true;
        }
        return false;
    }

}
