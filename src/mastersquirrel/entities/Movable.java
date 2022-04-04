package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.XY;

public abstract class Movable extends AEntity {
    protected Movable(int e, XY pos) {
        super(e,pos);
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        entityContext.move(this,position.genNewDir());
    }

    public void randomMovement(){
        position = XY.add(position, position.genNewDir());
    }

}