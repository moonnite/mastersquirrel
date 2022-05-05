package mastersquirrel;

import mastersquirrel.Exeptions.NotEnoughEnergyException;
import mastersquirrel.Exeptions.ScanExeption;
import mastersquirrel.entities.HandOperatedMasterSquirrel;
import mastersquirrel.util.ui.UI;
import mastersquirrel.util.ui.console.Command;
import mastersquirrel.util.ui.console.GameCommandType;

import java.lang.reflect.Method;
import java.util.Arrays;

public class GameImpl extends Game{

    HandOperatedMasterSquirrel handOperatedMasterSquirrel;
    EntitySet entitySet = EntitySet.getInstance();
    UI consoleUI;

    public GameImpl(State state, UI ui) {
        super(state);
        this.consoleUI = ui;
        handOperatedMasterSquirrel = new HandOperatedMasterSquirrel(new XY(9,1),ui);
        entitySet.put(handOperatedMasterSquirrel);
    }

    @Override
    protected void processInput() {

        Command command = consoleUI.getCommand();

        if(command == null){
            return;
        }

        GameCommandType commandType = (GameCommandType) command.getCommandType();
        try{
            Method method;
            if(command.getParams() != null){
                method = this.getClass().getDeclaredMethod(commandType.getName(), Object[].class);
                System.out.println(Arrays.toString(method.getParameterTypes()));
                method.invoke(this, (Object)command.getParams());
            }
            else{
                method = this.getClass().getDeclaredMethod(commandType.getName());
                method.invoke(this);
            }
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("sheise, die girbts nic");
        }
    }

    private void exit(){
        System.exit(0);
    }

    private void help(){
        consoleUI.help();
    }

    private void all(){
        consoleUI.message("Entities on board: ");
        consoleUI.message(entitySet.listToString());
    }

    private void w(){
        handOperatedMasterSquirrel.setInput(XY.UP);
    }
    private void s(){
        handOperatedMasterSquirrel.setInput(XY.DOWN);
    }
    private void a(){
        handOperatedMasterSquirrel.setInput(XY.LEFT);
    }
    private void d(){
        handOperatedMasterSquirrel.setInput(XY.RIGHT);
    }

    private void masterenergy(){
        consoleUI.message("MasterSquirrel Energy: ");
        consoleUI.message(Integer.toString(handOperatedMasterSquirrel.getEnergy()));
    }

    private void spawnmini(Object[] params){
        int miniEnergy;
        try{
            miniEnergy = Integer.parseInt((String)params[0]);
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

    @Override
    protected void update() {
        entitySet.nextStep(state.flattenBoard());
    }

    @Override
    public void render(BoardView boardView) {
        consoleUI.render(state.flattenBoard());
    }
}