public class MasterSquirrel extends Squirrel{
    @Override
    public void nextStep() {

    }

    @Override
    public void updateEnergy(int energyDelta) {

    }

    @Override
    public void updatePosition(Position pos) {

    }

    public void giveEnergyToMiniSquirrel(MiniSquirrel mS, int energy){
        //only donate energy when player squirrel has enough energy
        if(this.energy < energy) return;

        mS.updateEnergy(energy);
        updateEnergy(-energy);
    }
}
