package mastersquirrel.entities;

import mastersquirrel.*;
import mastersquirrel.nanaastar.Pathfinding;
import mastersquirrel.util.ui.UI;

public class HandOperatedMasterSquirrel extends MasterSquirrel{

    private XY dir;
    private boolean inputUpdated = false;

    public HandOperatedMasterSquirrel(XY pos, UI consoleUI) {
        super(pos);
        type = EntityType.HANDOPERATEDMASTERSQUIRREL;
        Pathfinding.addToSquirrelList(this);
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        if(cooldown > 0){
            cooldown--;
            return;
        }

        if(inputUpdated) {
            entityContext.move(this, dir);
            inputUpdated = false;
        }

        if(newMiniSquirrelSpawn){
            entityContext.spawnMiniSquirrel(newMiniSquirrelEnergy,this,newMiniSquirrelDirection);
            newMiniSquirrelSpawn = false;
        }
    }
    
    public void setInput(XY dir){
        inputUpdated = true;
        this.dir = dir;
    }
}