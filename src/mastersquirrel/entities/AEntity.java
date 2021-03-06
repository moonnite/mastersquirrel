package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.EntitySet;
import mastersquirrel.XY;
import mastersquirrel.nanaastar.Pathfinding;

public abstract class AEntity implements IEntity{
    private static int entityCount = 0;

    protected final int ID;
    protected EntityType type;
    protected XY position;
    protected final int startEnergy;
    protected int energy;

    protected AEntity (int startEnergy,XY pos){
        ID = entityCount++;
        this.startEnergy = startEnergy;
        energy = this.startEnergy;
        position = pos;

        for(EntityType entityType : EntityType.values()){
            if(entityType.getType().equals(this.getClass().getSimpleName())){
                type = entityType;
                break;
            }
        }

        //Handle List (Auto Subscribe to list on create)
        //EntitySet.getInstance().put(this);
    }

    public void resetEnergy(){
        energy = startEnergy;
    }

    public int getID(){
        return ID;
    }

    public int getEnergy(){
        return energy;
    }

    public EntityType getType(){
        return type;
    }

    public XY getPosition(){
        return this.position;
    }

    public void updateEnergy(int energyDelta){
        energy += energyDelta;
    }

    public int compareTo(Object o)
    {
        AEntity e = (AEntity) o;
        return Integer.compare(this.getID(), e.getID());
    }

    public void updatePosition(XY xy){
        position = xy;
    }

    @Override
    public String toString() {
        return "mastersquirrel.entities.AEntity{" +
                "ID=" + ID +
                ", type='" + type + '\'' +
                ", position=" + position +
                ", startEnergy=" + startEnergy +
                ", energy=" + energy +
                '}';
    }

    public abstract void nextStep(EntityContext entityContext);
}