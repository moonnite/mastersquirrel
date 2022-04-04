package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.XY;

public class BadPlant extends NoneMovable{

    public BadPlant(XY pos){
        super(-300, pos);
    }

    @Override
    public void nextStep(EntityContext entityContext) {

    }

    @Override
    public void updateEnergy(int energyDelta) {

    }
}