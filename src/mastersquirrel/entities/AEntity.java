package mastersquirrel.entities;

import mastersquirrel.EntitySet;
import mastersquirrel.XY;

public abstract class AEntity implements IEntity{
    private static int entityCount = 0;

    protected final int ID;
    protected final String type;
    protected XY position;
    protected final int startEnergy;
    protected int energy;

    protected AEntity (int startEnergy){
        ID = entityCount++;
        this.startEnergy = startEnergy;
        position = new XY();
        type = this.getClass().getSimpleName();

        //Handle List
        //EntitySet.getInstance().put(this);
    }

    public int getID(){
        return ID;
    }

    public int getEnergy(){
        return energy;
    }

    public void updateEnergy(int energyDelta){
        energy += energyDelta;
    }

    public int compareTo(Object o)
    {
        AEntity e = (AEntity) o;
        return Integer.compare(this.getID(), e.getID());
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

}