package mastersquirrel;

import mastersquirrel.entities.HandOperatedMasterSquirrel;

public class GameImpl extends Game{

    HandOperatedMasterSquirrel handOperatedMasterSquirrel;
    EntitySet entitySet = EntitySet.getInstance();
    UI consoleUI;

    public GameImpl(State state, UI consoleUI) {
        super(state);
        this.consoleUI = consoleUI;
        handOperatedMasterSquirrel = new HandOperatedMasterSquirrel(new XY(5,5),consoleUI);
        entitySet.put(handOperatedMasterSquirrel);
    }

    @Override
    protected void processInput() {

    }

    @Override
    protected void update() {
        entitySet.nextStep(state.getBoard().flatten());
    }

    @Override
    public void render(BoardView boardView) {
        consoleUI.render(state.getBoard().flatten());
    }
}