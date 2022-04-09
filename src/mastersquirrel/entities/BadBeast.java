package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.XY;

public class BadBeast extends Movable{

    private final int MAX_COOLDOWN = 3;
    private final int MAX_BITES = 7;
    private int remainingBites = MAX_BITES;

    public BadBeast(XY pos){
        super(-150,pos);
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

    //returns true if max amount of bites reached --> should die
    public boolean onBite(){
        remainingBites--;
        if(remainingBites == 0){
            remainingBites = MAX_BITES;
            return true;
        }
        return false;
    }

    @Override
    public void updateEnergy(int energyDelta) {

    }
}
