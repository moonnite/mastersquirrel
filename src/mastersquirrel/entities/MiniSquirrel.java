package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.XY;

public class MiniSquirrel extends Squirrel{
    public MiniSquirrel(int energy,XY pos){
        super(energy,pos);
    }
    @Override
    public void nextStep(EntityContext entityContext) {

    }

    @Override
    public void updateEnergy(int energyDelta) {

    }
}