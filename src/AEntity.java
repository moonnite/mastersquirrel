public abstract class AEntity implements IEntity{
    private static int entityCount = 0;

    protected int ID;
    protected final String type;
    protected Position position;
    protected final int startEnergy;
    protected int energy;

    protected AEntity (int startEnergy){
        ID = entityCount++;
        this.startEnergy = startEnergy;
        position = new Position();
        type = this.getClass().getSimpleName();

        //Handle List
        EntityList.getInstance().put(this);
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
                ", type='" + type + '\'' +
                ", position=" + position +
                ", startEnergy=" + startEnergy +
                ", energy=" + energy +
                '}';
    }

}