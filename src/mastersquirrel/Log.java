package mastersquirrel;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
    private static final Logger logger = Logger.getLogger(Log.class.getName());

    public static void addHandler(Handler handler){
        logger.addHandler(handler);
    }

    public static void log(String s, Level l){
        logger.log(l,s);
    }

    public static void log(String s){
        logger.log(Level.INFO,s);
    }
}