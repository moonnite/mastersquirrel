package mastersquirrel.entities;
import mastersquirrel.EntityContext;
import mastersquirrel.XY;

public abstract class NoneMovable extends AEntity {

    protected NoneMovable(int e,XY pos) {
        super(e, pos);
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        //should not move
    }
}