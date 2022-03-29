public abstract class AEntity implements IEntity{
    private static int entityCount = 0;

    protected int ID;
    protected Position position;
    protected final int startEnergy;
    protected int energy;


    protected AEntity (int startEnergy){
        ID = entityCount++;
        this.startEnergy = startEnergy;
        position = new Position();
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
        return "AEntity{" +
                "ID=" + ID +
                ", position=" + position +
                ", energy=" + energy +
                '}';
    }
}