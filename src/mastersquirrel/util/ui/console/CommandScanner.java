package mastersquirrel.util.ui.console;

import mastersquirrel.exeptions.ScanExeption;

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
        String newLine = "";

        try {
            if(inputReader.ready()){
                newLine = inputReader.readLine();
            }
            else{
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //split input string on spaces
        String[] splittedOutput = newLine.split("[ ]+");

        //check if input matches a command type (+ arguments)
        for (CommandTypeInfo cT : commandTypeInfos) {
            if(splittedOutput[0].toLowerCase().equals(cT.getName())){
                //Check for oblig param count
                if(splittedOutput.length-1 < cT.getObligParams().length){
                    throw new ScanExeption("too few arguments");
                }
                //Check for opt param count
                if(splittedOutput.length-1-cT.getObligParams().length > cT.getOptParams().length){
                    throw new ScanExeption("too many arguments");
                }
                //Check for command without parameters
                if(splittedOutput.length-1 == 0){
                    return new Command(cT,null);
                }

                //here you may validate parameter types
                //...

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
