public abstract class AEntity implements Comparable{
    private static int entityCount = 0;

    protected int genID(){
        return entityCount++;
    }

    protected Position position;
    protected static int energy;

}