package mastersquirrel.util.ui.console.consoletest;

import mastersquirrel.util.ui.console.CommandTypeInfo;

public enum ConsoleTestCommandType implements CommandTypeInfo {
    HELP("help"," * list all commands",null),
    EXIT("exit"," * exit program", null),
    ADDI("addi","<param1> <param2> * simple integer add", new Class<?>[] {int.class, int.class}),
    ADDF("addf","<param1> <param2> * simple flaot add", new Class<?>[] {float.class, float.class}),
    ECHO("echo","<param1> <param2> * echos param1 string param2 times", new Class<?>[] {String.class, int.class});

    private final String name;
    private final String helpText;
    private final Class<?>[] obligParams;
    private final Class<?>[] optParams;

    ConsoleTestCommandType(String name, String helpText, Class<?>[] obligParams, Class<?>... optParams){
        this.name = name;
        this.helpText = helpText;

        //obligatory parameters
        if(obligParams == null){
            this.obligParams = new Class<?>[0];
        }
        else{
            this.obligParams = new Class<?>[obligParams.length];
            System.arraycopy(obligParams, 0, this.obligParams, 0, obligParams.length);
        }
        //optional parameters
        if(optParams == null){
            this.optParams = new Class<?>[0];
        }
        else{
            this.optParams = new Class<?>[optParams.length];
            System.arraycopy(optParams, 0, this.optParams, 0, optParams.length);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getHelpText() {
        return helpText;
    }

    @Override
    public Class<?>[] getObligParams() {
        return obligParams;
    }

    @Override
    public Class<?>[] getOptParams() {
        return optParams;
    }
}