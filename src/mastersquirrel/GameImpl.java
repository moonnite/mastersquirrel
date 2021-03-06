package mastersquirrel;

import javafx.application.Platform;
import mastersquirrel.entities.bots.MasterSquirrelBot;
import mastersquirrel.entities.bots.MiniSquirrelBot;
import mastersquirrel.entities.bots.botapi.HandOperatedFactory;
import mastersquirrel.exeptions.NotEnoughEnergyException;
import mastersquirrel.exeptions.ScanExeption;
import mastersquirrel.entities.HandOperatedMasterSquirrel;
import mastersquirrel.nanaastar.Pathfinding;
import mastersquirrel.util.ui.FxUI;
import mastersquirrel.util.ui.UI;
import mastersquirrel.util.ui.console.Command;
import mastersquirrel.util.ui.console.GameCommandType;

import java.lang.reflect.Method;
import java.util.Arrays;

public class GameImpl extends Game{

    HandOperatedMasterSquirrel handOperatedMasterSquirrel;
    MasterSquirrelBot masterSquirrelBot;
    MiniSquirrelBot miniSquirrelBot;
    EntitySet entitySet = EntitySet.getInstance();
    private UI ui;

    public GameImpl(State state, UI ui) {
        super(state);
        this.ui = ui;
        //handOperatedMasterSquirrel = new HandOperatedMasterSquirrel(new XY(9,1));
        //entitySet.put(handOperatedMasterSquirrel);
        //MiniSquirrelBot miniSquirrelBot = new MiniSquirrelBot(1000, new XY(12,5), handOperatedMasterSquirrel, new HandOperatedFactory());
        //EntitySet.getInstance().put(miniSquirrelBot);
    }

    public void receiveInput(String keyEvent){
        switch (keyEvent){
            case "w" -> w();
            case "a" -> a();
            case "s" -> s();
            case "d" -> d();
            case "togglePause" -> switchPauseState();
            case "q" -> q();
        }
    }

    private void switchPauseState() {
        paused = !paused;
    }

    @Override
    protected void processInput() {

        Command command = ui.getCommand();

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
        }
    }

    private void exit(){
        System.exit(0);
    }

    private void help(){
        ui.help();
    }

    private void all(){
        ui.message("Entities on board: ");
        ui.message(entitySet.listToString());
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
    private void q(){
        try{
            handOperatedMasterSquirrel.spawnMiniSquirrel(10);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    private void masterenergy(){
        ui.message("MasterSquirrel Energy: ");
        ui.message(Integer.toString(handOperatedMasterSquirrel.getEnergy()));
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
        if(State.getBotState() && State.decrementRemainingSteps()){
            state.getBoard().reset();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ((FxUI) ui).togglePause();
                }
            });
        }
    }

    @Override
    public void render(BoardView boardView) {
        ui.render(state.flattenBoard());
    }

}