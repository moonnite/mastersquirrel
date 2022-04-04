package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.XY;

public class GoodBeast extends Movable{

    public GoodBeast(XY pos){
        super(300,pos);
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        super.nextStep(entityContext);
    }

    @Override
    public void updateEnergy(int energyDelta) {

    }
}
