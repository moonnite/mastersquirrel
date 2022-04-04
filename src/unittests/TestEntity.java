package unittests;

import mastersquirrel.*;
import mastersquirrel.entities.AEntity;

public class TestEntity extends AEntity {

    public boolean hasStepped = false;

    protected TestEntity(XY pos) {
        super(200,pos);
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        hasStepped = true;
    }

    @Override
    public void updatePosition(XY pos) {

    }
}