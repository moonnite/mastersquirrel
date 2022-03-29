public abstract class Movable extends AEntity {
    protected Movable(int e) {
        super(e);
    }

    @Override
    public void nextStep() {
        randomMovement();
    }

    public void randomMovement(){
        position = position.addPosition(position, position.genNewDir());
    }

}