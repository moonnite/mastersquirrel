package mastersquirrel;

import mastersquirrel.util.ui.console.Command;

import java.io.IOException;

public interface UI {
    public void render(BoardView boardView);
    public String getInput();
}