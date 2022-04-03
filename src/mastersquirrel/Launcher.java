package mastersquirrel;

public class Launcher {
    public static void main(String[] args) {
        //GameImpl game = new GameImpl();
        Board board = new Board();
        FlattenedBoard flattenedBoard = board.flatten();
        ConsoleUI consoleUI = new ConsoleUI();

        consoleUI.render(flattenedBoard);
    }
}