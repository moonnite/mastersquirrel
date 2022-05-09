package mastersquirrel.util.ui;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import mastersquirrel.BoardView;
import mastersquirrel.entities.AEntity;
import mastersquirrel.entities.EntityType;
import mastersquirrel.entities.Squirrel;
import mastersquirrel.nanaastar.Pathfinding;
import mastersquirrel.util.ui.console.Command;

import java.util.ArrayList;

public class FxUI extends BorderPane implements UI {

    private final int SCALE = 10;

    private final BorderPane canvasPane;
    private final BorderPane infoPaneRight;

    public FxUI() {
        //top
        MenuBar menuBar = createMenuBar();
        this.setTop(menuBar);

        //bottom
        Label message = new Label("messages");
        this.setBottom(message);

        //middle left
        Label controls = new Label("Controls");
        Button pauseBtn = new Button("Pause");
        Button resumeBtn = new Button("Resume");
        VBox vboxLeft = new VBox();
        vboxLeft.getChildren().addAll(controls,pauseBtn,resumeBtn);

        Separator separator = new Separator();
        vboxLeft.getChildren().add(new Separator());

        for(EntityType e : EntityType.values()){
            Text color = new Text("■");
            color.setFill(e.getColor());
            TextFlow tempTextFlow = new TextFlow();
            tempTextFlow.getChildren().addAll(color,new Text(" "+e.getType()));
            vboxLeft.getChildren().add(tempTextFlow);
        }

        vboxLeft.setSpacing(5);
        this.setLeft(vboxLeft);

        //middle center
        this.canvasPane = new BorderPane();
        this.setCenter(canvasPane);

        //middle right
        infoPaneRight = new BorderPane();
        VBox vboxRight = new VBox();
        vboxRight.setSpacing(5);
        infoPaneRight.setCenter(vboxRight);
        this.setRight(infoPaneRight);
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

    private void updateInfo(ArrayList<Squirrel> squirrelArrayList){
        VBox vBox = (VBox) infoPaneRight.getCenter();
        vBox.getChildren().clear();

        for(Squirrel s : squirrelArrayList){
            Label label = new Label(s.getType()+": "+s.getEnergy());
            vBox.getChildren().add(label);
        }
    }

    private void drawBoardInPane(AEntity[][] boardArray){
        if(canvasPane.getChildren().isEmpty()){
            canvasPane.setCenter(new Canvas(boardArray.length * SCALE, boardArray[0].length * SCALE));
        }
        Canvas canvas = (Canvas) canvasPane.getCenter();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        for (int x = 0; x < boardArray.length; x++){
            for (int y = 0; y < boardArray[0].length; y++){
                EntityType type = EntityType.EMPTY;
                if (boardArray[x][y] != null) type = boardArray[x][y].getType();

                graphicsContext.setFill(type.getColor());
                graphicsContext.fillRect(x*SCALE,y*SCALE,SCALE,SCALE);
            }
        }
    }

    @Override
    public void render(BoardView boardView) {
        //TODO: spielfeld aktualisieren

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                drawBoardInPane(boardView.getBoardArray());
                updateInfo(Pathfinding.getSquirrelArrayList());
            }
        });

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
