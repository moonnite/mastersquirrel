package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.XY;

public class GoodBeast extends Movable{

    private final int MAX_COOLDOWN = 3;
    private final int CHASE_RADIUS = 6;

    public GoodBeast(XY pos){
        super(300,pos);
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        if(cooldown > 0){
            cooldown--;
            return;
        }
        cooldown = MAX_COOLDOWN;
        super.nextStep(entityContext);
    }

    @Override
    public void updateEnergy(int energyDelta) {

    }

    public int getChaseRadius() {
        return CHASE_RADIUS;
    }
}
