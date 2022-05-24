package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.EntitySet;
import mastersquirrel.nanaastar.Pathfinding;
import mastersquirrel.XY;

import java.util.Iterator;

public class MiniSquirrel extends Squirrel{

    private final AEntity parent;

    public MiniSquirrel(int energy,XY pos,AEntity parent){
        super(energy,pos);
        this.parent = parent;
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        if(energy > 0){
            updateEnergy(-1);
        }
        super.nextStep(entityContext);
    }

    @Override
    public void updateEnergy(int energyDelta) {
        energy+=energyDelta;
        if(energy <= 0){
            kill();
        }
    }

    public void kill(){
        if(EntitySet.getInstance().get(getID()) != null){
            EntitySet.getInstance().pull(getID());
            Pathfinding.removeFromSquirrelList(this);
        }
    }

    public AEntity getParent() {
        return parent;
    }
}