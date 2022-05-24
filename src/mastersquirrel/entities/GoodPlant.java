package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.XY;

public class GoodPlant extends NoneMovable{
    public GoodPlant(XY pos){
        super(150,pos);
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        if(energy <= 0) entityContext.killAndRespawn(this);

        //does not move
    }

    @Override
    public void updateEnergy(int energyDelta) {
        energy += energyDelta;
    }

}