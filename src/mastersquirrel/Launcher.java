package mastersquirrel;

public class Launcher {
    public static void main(String[] args) {

        BoardConfig boardConfig = new BoardConfig(
                new XY(20, 10),
                20,
                4,
                5,
                3,
                4);

        Board board = new Board(boardConfig);
        State state = new State(board);

        //modularer Character: hier einfach eine andere UI Implementierung angeben statt ConsoleUI
        //(diese muss natürlich UI implementen)
        ConsoleUI consoleUI = new ConsoleUI();

        GameImpl game = new GameImpl(state, consoleUI);

        game.run(state.getBoard().flatten());
    }
}