package mastersquirrel;

import mastersquirrel.entities.bots.botapi.ControllerContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ControllerContextLogHandler implements InvocationHandler {

    private ControllerContext controllerContext;

    public  ControllerContextLogHandler(ControllerContext controllerContext){
        this.controllerContext = controllerContext;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        Log.log("calling" + method + " with params " + Arrays.toString(args));
        Object result = null;
        try{
            result = method.invoke(controllerContext,args);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
}
