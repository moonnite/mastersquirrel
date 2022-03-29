public abstract class NoneMovable extends AEntity {

    protected NoneMovable(int e) {
        super(e);
    }

    @Override
    public void nextStep() {
        //should not move
    }
}