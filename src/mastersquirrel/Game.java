package mastersquirrel;

public abstract class Game{

    State state;
    private final int FPS = 1;

    public Game(State state){
        this.state = state;
    }

    public void run(BoardView boardView){
        while(true){
            render(boardView);
            processInput();
            update();
            try{
                Thread.sleep(1000/FPS);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //process user Input
    protected abstract void processInput();

    protected abstract void update(); //change current game state

    public abstract void render(BoardView boardView); //display game state
}
