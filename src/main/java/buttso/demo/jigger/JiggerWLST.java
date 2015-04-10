package buttso.demo.jigger;

import java.util.logging.Logger;
import weblogic.management.scripting.utils.WLSTInterpreter;
import org.python.util.InteractiveInterpreter;

public class JiggerWLST {
    
    static InteractiveInterpreter interpreter = null;

    private JiggerWLST() {
    }
    
    public static void execInline(String wlst, String domainName) {
        Logger.getAnonymousLogger().info("Executing WLST for domain [" + domainName);
        if(interpreter == null) {
             interpreter = new WLSTInterpreter();
        }
        // Logger.getAnonymousLogger().fine(wlst.substring(0, wlst.length()/2 > 512? 512: wlst.length()));
        //Logger.getAnonymousLogger().info(wlst.substring(0, wlst.length()/2));
        Logger.getAnonymousLogger().info(wlst);
        interpreter.exec(wlst);
    }
}

 

