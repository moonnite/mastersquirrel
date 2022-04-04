package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.XY;

public class Wall extends NoneMovable{
    public Wall(){
        super(-30);
    }

    @Override
    public void nextStep(EntityContext entityContext) {

    }

    @Override
    public void updateEnergy(int energyDelta) {

    }
}