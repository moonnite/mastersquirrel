package mastersquirrel;

public abstract class Game{

    State state;
    private final int FPS = 50;
    protected boolean paused = false;

    public Game(State state){
        this.state = state;
    }

    public void run(BoardView boardView){
        while(true){

            render(boardView);

            processInput();

            if(!paused) update();

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

    public abstract void receiveInput(String keyEvent);

    protected abstract void update(); //change current game state

    public abstract void render(BoardView boardView); //display game state
}
