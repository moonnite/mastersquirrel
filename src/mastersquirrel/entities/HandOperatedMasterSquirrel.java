package mastersquirrel.entities;

import mastersquirrel.*;

public class HandOperatedMasterSquirrel extends MasterSquirrel{
    UI consoleUI;

    public HandOperatedMasterSquirrel(XY pos, UI consoleUI) {
        super(pos);
        this.consoleUI = consoleUI;
        System.out.println();
        System.out.println((Pathfinding.squirrelsInRange(pos,6)).toString());
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        if(cooldown > 0){
            cooldown--;
            return;
        }

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