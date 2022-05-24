package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.exeptions.NotEnoughEnergyException;
import mastersquirrel.XY;

public class MasterSquirrel extends Squirrel{

    protected boolean newMiniSquirrelSpawn = false;
    protected int newMiniSquirrelEnergy;
    protected XY newMiniSquirrelDirection;

    public MasterSquirrel(XY pos){
        super(500000,pos);
        type = EntityType.MASTERSQUIRREL;
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        if(newMiniSquirrelSpawn){
            entityContext.spawnMiniSquirrel(newMiniSquirrelEnergy,this,this.position.genNewDir());
            newMiniSquirrelSpawn = false;
        }
        super.nextStep(entityContext);
    }

    public void spawnMiniSquirrel(int energy) throws NotEnoughEnergyException {
        //only donate energy when player squirrel has enough energy
        if(this.energy < energy) throw new NotEnoughEnergyException();
        updateEnergy(-energy);
        newMiniSquirrelSpawn = true;
        newMiniSquirrelEnergy = energy;
        newMiniSquirrelDirection = position.genNewDir();
    }
}