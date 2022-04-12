package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.EntitySet;
import mastersquirrel.nanaastar.Pathfinding;
import mastersquirrel.XY;

public class MiniSquirrel extends Squirrel{

    private final AEntity parent;

    public MiniSquirrel(int energy,XY pos,AEntity parent){
        super(energy,pos);
        this.parent = parent;
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        updateEnergy(-1);
        if(energy == 0){
            kill();
        }
        super.nextStep(entityContext);
    }

    @Override
    public void updateEnergy(int energyDelta) {

    }

    public void kill(){
        EntitySet.getInstance().pull(getID());
        Pathfinding.removeFromSquirrelList(this);
    }

    public AEntity getParent() {
        return parent;
    }
}