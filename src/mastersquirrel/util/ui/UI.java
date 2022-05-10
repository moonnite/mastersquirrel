package mastersquirrel.util.ui;

import javafx.stage.Stage;
import mastersquirrel.BoardView;
import mastersquirrel.GameImpl;
import mastersquirrel.util.ui.console.Command;

import java.io.IOException;

public interface UI {
    public void render(BoardView boardView);
    public Command getCommand();
    public void message(String msg);
    public void help();
}