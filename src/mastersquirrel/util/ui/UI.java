package mastersquirrel.util.ui;

import mastersquirrel.BoardView;
import mastersquirrel.util.ui.console.Command;

import java.io.IOException;

public interface UI {
    public void render(BoardView boardView);
    public Command getCommand();
    public void message(String msg);
    public void help();
}