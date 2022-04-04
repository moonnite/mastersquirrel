package mastersquirrel;

public class State {

    private final Board board;
    private static int highscore = 0;

    public State(Board board){
        this.board = board;
    }

    public void update(){

    }

    public FlattenedBoard getFlattenedBoard(){
        return board.flatten();
    }

    public Board getBoard() {
        return board;
    }

    public int getHighscore() {
        return State.highscore;
    }

    public void setHighscore(int highscore) {
        State.highscore = highscore;
    }
}