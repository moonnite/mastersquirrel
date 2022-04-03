package mastersquirrel.entities;

import mastersquirrel.XY;

public class MasterSquirrel extends Squirrel{
    public MasterSquirrel(){
        super(500);
    }

    @Override
    public void nextStep() {
        super.nextStep();
    }

    @Override
    public void updateEnergy(int energyDelta) {

    }

    @Override
    public void updatePosition(XY pos) {

    }

    public void spawnMiniSquirrel(int energy){
        //only donate energy when player squirrel has enough energy
        if(this.energy < energy) return;
        MiniSquirrel ms = new MiniSquirrel(energy);
        updateEnergy(-energy);
    }
}