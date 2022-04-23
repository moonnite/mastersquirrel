package mastersquirrel.util.ui.console.consoletest;

import mastersquirrel.Exeptions.ScanExeption;
import mastersquirrel.util.ui.console.Command;
import mastersquirrel.util.ui.console.CommandScanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class ConsoleTestCommandsProcessor {

    private final PrintStream outputStream = System.out;
    private final BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        ConsoleTestCommandsProcessor cP = new ConsoleTestCommandsProcessor();
        cP.process();
    }

    public void process() {
        CommandScanner commandScanner = new CommandScanner(ConsoleTestCommandType.values(), inputReader);
        while(true){
            //...

            Command command = commandScanner.next();

            if(command == null){
                continue;
            }

            ConsoleTestCommandType commandType = (ConsoleTestCommandType) command.getCommandType();

            switch(commandType){
                case EXIT -> System.exit(0);
                case HELP -> help();
                case ADDI -> {
                    int[] iParams;
                    try{
                        iParams = Arrays.stream((String[])command.getParams()).mapToInt(Integer::parseInt).toArray();
                    }
                    catch (Exception e){
                        throw new ScanExeption("wrong parameter type(s)");
                    }
                    outputStream.println(iParams[0]+iParams[1]);
                }
                case ADDF -> {
                    Float[] fParams;
                    try{
                        //sadly no .mapToFloat
                        String[] sParams = (String[])command.getParams();
                        fParams = new Float[sParams.length];
                        for(int i = 0; i < sParams.length; i++){
                            fParams[i] = Float.parseFloat(sParams[i]);
                        }
                    }
                    catch (Exception e){
                        throw new ScanExeption("wrong parameter type(s)");
                    }
                    outputStream.println(fParams[0]+fParams[1]);
                }
                case ECHO -> {
                    int number;
                    try{
                        number = Integer.parseInt((String)command.getParams()[1]);
                    }
                    catch (Exception e){
                        throw new ScanExeption("wrong parameter type(s)");
                    }
                    for(int i = 0; i < number; i++){
                        outputStream.println(command.getParams()[0]);
                    }
                }
            }
        }
    }

    private void help(){
        for(ConsoleTestCommandType t : ConsoleTestCommandType.values()){
            outputStream.println(t.getName()+" "+t.getHelpText());
        }
    }
}
