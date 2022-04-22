package mastersquirrel.util.ui.console;

public enum GameCommandType implements CommandTypeInfo {

        HELP("help"," * list all commands"),
        EXIT("exit"," * exit program"),

        ALL("all"," * print all Entitys on board"),

        LEFT("a"," * move left"),
        UP("w"," * move up"),
        DOWN("s"," * move down"),
        RIGHT("d"," * move right"),

        MASTER_ENERGY("masterenergy"," * print Energy of Master Squirrel"),
        SPAWN_MINI("spawnmini","<param1> * spawn Mini Squirrel with param1 amount of energy", int.class);

        private final String name;
        private final String helpText;
        private final Class<?> param1;
        private final Class<?> param2;

    GameCommandType(String name, String helpText){
            this.name = name;
            this.helpText = helpText;
            this.param1 = null;
            this.param2 = null;
        }

    GameCommandType(String name, String helpText, Class<?> param1){
        this.name = name;
        this.helpText = helpText;
        this.param1 = param1;
        this.param2 = null;
    }

    GameCommandType(String name, String helpText, Class<?> param1, Class<?> param2){
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
            if(param1 == null && param2 == null){
                return new Class<?>[] {};
            }
            else if(param1 == null){
                return new Class<?>[] {param2};
            }
            else if(param2 == null){
                return new Class<?>[] {param1};
            }
            return new Class<?>[] {param1,param2};
        }
}