package mastersquirrel.entities;

import mastersquirrel.XY;

public class HandOperatedMasterSquirrel extends MasterSquirrel{
    ConsoleUI consoleUI;

    public HandOperatedMasterSquirrel(XY pos, ConsoleUI consoleUI) {
        super(pos);
        this.consoleUI = consoleUI;
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        switch(consoleUI.getInput()){
            case "w"-> {
                entityContext.move(this,XY.UP);
            }
            case "a" -> {
                entityContext.move(this,XY.LEFT);
            }
            case "s" -> {
                entityContext.move(this,XY.DOWN);
            }
            case "d" -> {
                entityContext.move(this,XY.RIGHT);
            }
            default -> {
                System.out.println("Wrong Input");
            }
        }
    }
}