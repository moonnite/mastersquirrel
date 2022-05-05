package mastersquirrel.util.ui;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import mastersquirrel.BoardView;
import mastersquirrel.util.ui.console.Command;

public class FxUI extends BorderPane implements UI {

    public FxUI() {

        //top
        MenuBar menuBar = createMenuBar();
        this.setTop(menuBar);

        //bottom
        Label message = new Label("messages");
        this.setBottom(message);

        //middle left

        //middle center

        //middle right


    }

    private MenuBar createMenuBar() {
        Menu fileMenuItem = new Menu("File");
        MenuItem pauseMenuItem = new MenuItem("Pause");
        MenuItem resumeMenuItem = new MenuItem("Resume");
        MenuItem stopMenuItem = new MenuItem("Stop");

        fileMenuItem.getItems().addAll(pauseMenuItem,resumeMenuItem,stopMenuItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenuItem);
        return menuBar;
    }

    @Override
    public void render(BoardView boardView) {
        //TODO: spielfeld aktualisieren
    }

    @Override
    public Command getCommand() {
        return null;
    }

    @Override
    public void message(String msg) {

    }

    @Override
    public void help() {

    }
}
