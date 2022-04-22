package mastersquirrel.util.ui.console;

public interface CommandTypeInfo {
    public String getName();
    public String getHelpText();
    public Class<?>[] getParamTypes();
}