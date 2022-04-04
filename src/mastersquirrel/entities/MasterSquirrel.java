package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.RandomDirection;
import mastersquirrel.XY;

public class MasterSquirrel extends Squirrel{
    public MasterSquirrel(XY pos){
        super(500,pos);
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        super.nextStep(entityContext);
    }

    @Override
    public void updateEnergy(int energyDelta) {

    }

    public void spawnMiniSquirrel(int energy){
        //only donate energy when player squirrel has enough energy
        if(this.energy < energy) return;
        MiniSquirrel ms = new MiniSquirrel(energy, XY.add(position,position.genNewDir()));
        updateEnergy(-energy);
    }
}