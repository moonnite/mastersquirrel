package mastersquirrel.util.ui.console;

import mastersquirrel.Exeptions.ScanExeption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

public class CommandScanner {
    private final CommandTypeInfo[] commandTypeInfos;
    private final BufferedReader inputReader;
    private PrintStream outputStream;

    public CommandScanner(CommandTypeInfo[] commandTypes, BufferedReader bufferedReader){
        this.commandTypeInfos = commandTypes;
        inputReader = bufferedReader;
    }

    public Command next(){

        //get next console line
        String newLine;
        try{
            newLine = inputReader.readLine();
        }
        catch (Exception e){
            return null;
        }

        //split input string on spaces
        String[] splittedOutput = newLine.split("[ ]+");

        //check if input matches a command type (+ arguments)
        for (CommandTypeInfo cT : commandTypeInfos) {
            if(splittedOutput[0].toLowerCase().equals(cT.getName())){
                //Check for wrong parameterCount
                if(splittedOutput.length-1 != cT.getParamTypes().length){
                    throw new ScanExeption("wrong parameter count");
                }
                //Check for command without parameters
                if(splittedOutput.length-1 == 0){
                    return new Command(cT,null);
                }

//                for(int i = 1; i < splittedOutput.length; i++){
//                    try{
//                        Class<?> clazz = cT.getParamTypes()[i];
//                        clazz.cast(splittedOutput[i]);
//                    }
//                    catch (Exception e){
//                        throw new ScanExeption("wrong parameter type(s)");
//                    }
//                }

                //command with parameters
                return new Command(cT,Arrays.copyOfRange(splittedOutput,1,splittedOutput.length));
            }
        }
        //if user only presses enter
        if(newLine.equals("")) return null;
        //if user presses enter with wrong input
        throw new ScanExeption("no such command");
    }
}
