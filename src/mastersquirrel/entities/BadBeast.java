package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.XY;

public class BadBeast extends Movable{

    public BadBeast(XY pos){
        super(-150,pos);
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        super.nextStep(entityContext);
    }

    @Override
    public void updateEnergy(int energyDelta) {

    }
}
