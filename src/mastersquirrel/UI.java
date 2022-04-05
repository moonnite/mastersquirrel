package mastersquirrel;

public interface UI {
    //public MoveCommand getCommand();
    public void render(BoardView boardView);
    public String getInput();
}