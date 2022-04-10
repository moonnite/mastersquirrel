package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.RandomDirection;
import mastersquirrel.XY;

public class MasterSquirrel extends Squirrel{
    public MasterSquirrel(XY pos){
        super(500,pos);
        type = EntityType.MASTERSQUIRREL;
    }

    public void spawnMiniSquirrel(int energy){
        //only donate energy when player squirrel has enough energy
        if(this.energy < energy) return;
        MiniSquirrel ms = new MiniSquirrel(energy, XY.add(position,position.genNewDir()),this);
        updateEnergy(-energy);
    }
}