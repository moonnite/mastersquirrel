package mastersquirrel;

public class Launcher {
    public static void main(String[] args) {
        Board board = new Board();
        State state = new State(board);
        ConsoleUI consoleUI = new ConsoleUI();

        GameImpl game = new GameImpl(state, consoleUI);

        game.run(state.getFlattenedBoard());
    }
}