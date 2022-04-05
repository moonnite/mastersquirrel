package mastersquirrel;

import javax.swing.text.View;

public abstract class Game{

    State state;

    public Game(State state){
        this.state = state;
    }

    public void run(BoardView boardView){
        while(true){
            render(boardView);
            processInput();
            update();
        }
    }

    //process user Input
    protected abstract void processInput();

    protected abstract void update(); //change current game state

    public abstract void render(BoardView boardView); //display game state
}
