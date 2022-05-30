package mastersquirrel;

public class State {

    private final Board board;
    private static int highscore = 0;
    private static long remainingSteps = 0;
    private static boolean botState = false;

    public State(Board board){
        this.board = board;
    }

    public void update() {

    }

    public static long getRemainingSteps() {
        return remainingSteps;
    }

    public static boolean getBotState(){
        return botState;
    }

    // returns true if 0 has been reached
    public static boolean decrementRemainingSteps(){
        if (remainingSteps - 1 >= 0) {
            remainingSteps--;
            return false;
        }
        return true;
    }

    public static void setRemainingSteps(long steps){
        remainingSteps = steps;
    }

    public static void setBotState(boolean state){
        botState = state;
    }

    public Board getBoard() {
        return board;
    }

    public FlattenedBoard flattenBoard(){return board.flatten();}

    public int getHighscore() {
        return State.highscore;
    }

    public void setHighscore(int highscore) {
        State.highscore = highscore;
    }
}