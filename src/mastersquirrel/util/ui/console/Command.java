package mastersquirrel.util.ui.console;

public class Command {

    private final CommandTypeInfo commandType;
    private final Object[] params;

    public Command(CommandTypeInfo commandType, Object[] params){
        this.commandType = commandType;
        this.params = params;
    }

    public Object[] getParams(){
        return params;
    }

    public Object getCommandType() {
        return commandType;
    }
}
