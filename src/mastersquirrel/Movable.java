package mastersquirrel;

public abstract class Movable extends AEntity {
    protected Movable(int e) {
        super(e);
    }

    @Override
    public void nextStep() {
        randomMovement();
    }

    public void randomMovement(){
        position = XY.add(position, position.genNewDir());
    }

}