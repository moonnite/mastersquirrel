package mastersquirrel.util.ui.console;

import java.util.ArrayList;

public enum GameCommandType implements CommandTypeInfo {

        HELP("help"," * list all commands", null),
        EXIT("exit"," * exit program",null),

        ALL("all"," * print all Entitys on board",null),

        LEFT("a"," * move left",null),
        UP("w"," * move up",null),
        DOWN("s"," * move down",null),
        RIGHT("d"," * move right",null),

        MASTER_ENERGY("masterenergy"," * print Energy of Master Squirrel",null),
        SPAWN_MINI("spawnmini","<param1> * spawn Mini Squirrel with param1 amount of energy", new Class<?>[] {int.class});

        private final String name;
        private final String helpText;
        private final Class<?>[] obligParams;
        private final Class<?>[] optParams;

    GameCommandType(String name, String helpText, Class<?>[] obligParams, Class<?>... optParams){
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