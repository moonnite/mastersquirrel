package mastersquirrel;

public abstract class Game implements UI {

    public void run(){
        while(true){
            render();
            processInput();
            update();
        }
    }

    //process user Input
    protected void processInput(){

    }

    protected abstract void update(); //change current game state

    protected abstract void render(); //display game state

    @Override
    public void render(BoardView view) {

    }
}
