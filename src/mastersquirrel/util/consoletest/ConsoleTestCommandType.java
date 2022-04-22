package mastersquirrel.util.consoletest;

import mastersquirrel.util.ui.console.CommandTypeInfo;

public enum ConsoleTestCommandType implements CommandTypeInfo {
    HELP("help"," * list all commands"),
    EXIT("exit"," * exit program"),
    ADDI("addi","<param1> <param2> * simple integer add", int.class, int.class),
    ADDF("addf","<param1> <param2> * simple flaot add", float.class, float.class),
    ECHO("echo","<param1> <param2> * echos param1 string param2 times", String.class, int.class);

    private final String name;
    private final String helpText;
    private final Class<?> param1;
    private final Class<?> param2;

    ConsoleTestCommandType(String name, String helpText){
        this.name = name;
        this.helpText = helpText;
        this.param1 = null;
        this.param2 = null;
    }

    ConsoleTestCommandType(String name, String helpText, Class<?> param1, Class<?> param2){
        this.name = name;
        this.helpText = helpText;
        this.param1 = param1;
        this.param2 = param2;
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
    public Class<?>[] getParamTypes() {
        if(param1 == null || param2 == null){
            return new Class<?>[] {};
        }
        return new Class<?>[] {param1,param2};
    }
}