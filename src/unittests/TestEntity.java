package unittests;

import mastersquirrel.*;
import mastersquirrel.entities.AEntity;

public class TestEntity extends AEntity {

    public boolean hasStepped = false;

    protected TestEntity() {
        super(200);
    }

    @Override
    public void nextStep() {
        hasStepped = true;
    }

    @Override
    public void updatePosition(XY pos) {

    }
}