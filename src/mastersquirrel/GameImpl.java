package mastersquirrel;

import mastersquirrel.Exeptions.NotEnoughEnergyException;
import mastersquirrel.Exeptions.ScanExeption;
import mastersquirrel.entities.HandOperatedMasterSquirrel;
import mastersquirrel.util.ui.console.Command;
import mastersquirrel.util.ui.console.GameCommandType;

public class GameImpl extends Game{

    HandOperatedMasterSquirrel handOperatedMasterSquirrel;
    EntitySet entitySet = EntitySet.getInstance();
    UI consoleUI;

    public GameImpl(State state, UI consoleUI) {
        super(state);
        this.consoleUI = consoleUI;
        handOperatedMasterSquirrel = new HandOperatedMasterSquirrel(new XY(9,1),consoleUI);
        entitySet.put(handOperatedMasterSquirrel);
    }

    @Override
    protected void processInput() {

        Command command = consoleUI.getCommand();

        if(command == null){
            return;
        }

        GameCommandType commandType = (GameCommandType) command.getCommandType();

        switch(commandType){
            case EXIT -> System.exit(0);
            case HELP -> consoleUI.help();
            case ALL -> {
                consoleUI.message("Entities on board: ");
                consoleUI.message(entitySet.listToString());
            }
            case UP -> handOperatedMasterSquirrel.setInput(XY.UP);
            case DOWN -> handOperatedMasterSquirrel.setInput(XY.DOWN);
            case LEFT -> handOperatedMasterSquirrel.setInput(XY.LEFT);
            case RIGHT -> handOperatedMasterSquirrel.setInput(XY.RIGHT);
            case SPAWN_MINI -> {
                int miniEnergy;
                try{
                    miniEnergy = Integer.parseInt((String)command.getParams()[0]);
                }
                catch (Exception e){
                    throw new ScanExeption("wrong parameter type(s)");
                }
                try{
                    handOperatedMasterSquirrel.spawnMiniSquirrel(miniEnergy);
                }
                catch(NotEnoughEnergyException e){
                    e.printStackTrace();
                }
            }
            case MASTER_ENERGY -> {
                consoleUI.message("MasterSquirrel Energy: ");
                consoleUI.message(Integer.toString(handOperatedMasterSquirrel.getEnergy()));
            }
        }
    }

    @Override
    protected void update() {
        entitySet.nextStep(state.flattenBoard());
    }

    @Override
    public void render(BoardView boardView) {
        consoleUI.render(state.flattenBoard());
    }
}