package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.XY;

public class Wall extends NoneMovable{
    public Wall(XY pos){
        super(-30,pos);
    }

    @Override
    public void nextStep(EntityContext entityContext) {

    }

    @Override
    public void updateEnergy(int energyDelta) {

    }
}