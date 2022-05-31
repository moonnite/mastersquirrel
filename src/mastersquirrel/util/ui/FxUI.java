package mastersquirrel.util.ui;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import mastersquirrel.BoardView;
import mastersquirrel.Game;
import mastersquirrel.GameImpl;
import mastersquirrel.RandomDirection;
import mastersquirrel.entities.AEntity;
import mastersquirrel.entities.EntityType;
import mastersquirrel.entities.Squirrel;
import mastersquirrel.entities.bots.MasterSquirrelBot;
import mastersquirrel.entities.bots.MiniSquirrelBot;
import mastersquirrel.nanaastar.Pathfinding;
import mastersquirrel.util.ui.console.Command;

import java.util.ArrayList;
import java.util.Iterator;

public class FxUI extends BorderPane implements UI{

    private int pixelScale = 10;

    final KeyCombination kbPause = new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN);

    private final BorderPane canvasPane;
    private final BorderPane infoPaneRight;

    private Button pauseBtn;
    private Button resumeBtn;
    private MenuItem pauseMenuItem;
    private MenuItem resumeMenuItem;

    private Label message;

    private boolean paused = false;

    private Game game;
    private Stage stage;

    public FxUI() {

        botMap = new HashMap<Integer,Color>();

        // top
        MenuBar menuBar = createMenuBar();
        this.setTop(menuBar);

        // bottom
        message = new Label("messages");
        this.setBottom(message);

        // middle left
        Label controls = new Label("Controls");
        createControlButtons();
        vboxLeft = new VBox();
        vboxLeft.getChildren().addAll(controls,pauseBtn,resumeBtn);

        // Info
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

        // middle center
        this.canvasPane = new BorderPane();
        this.setCenter(canvasPane);

        // middle right
        infoPaneRight = new BorderPane();
        VBox vboxRight = new VBox();
        vboxRight.setSpacing(5);
        infoPaneRight.setCenter(vboxRight);
        this.setRight(infoPaneRight);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        // check if keyEvent matches key combination
        if(kbPause.match((keyEvent))){
            togglePause();
            return;
        }
        //pixel board scaling
        if(keyEvent.getCode().toString().equals("PLUS")){
            pixelScale ++;
            canvasPane.getChildren().clear();
        }
        if(keyEvent.getCode().toString().equals("MINUS")){
            if(pixelScale-1 > 0){
                pixelScale--;
                canvasPane.getChildren().clear();
            }
        }
        // convert key input to String and pass it to game (controller) class
        game.receiveInput(keyEvent.getCode().toString().toLowerCase());
    }

    private void togglePause(){
        // toggle on call (switch pause state)
        paused = !paused;
        // link pause buttons and menu items to pause state
        pauseBtn.setDisable(paused);
        resumeBtn.setDisable(!paused);
        pauseMenuItem.setDisable(paused);
        resumeMenuItem.setDisable(!paused);
        // call pause input on game object
        game.receiveInput("togglePause");
        // display pause/resume status message
        if(paused)
            message.setText("Game paused");
        else
            message.setText("Game resumed");
    }

    private void createControlButtons(){
        // create buttons with default visibility
        pauseBtn = new Button("Pause");
        resumeBtn = new Button("Resume");
        resumeBtn.setDisable(true);
        // link buttons to pause event
        pauseBtn.setOnAction(e ->{
            togglePause();
        });
        resumeBtn.setOnAction(e ->{
            togglePause();
        });
    }

    private MenuBar createMenuBar() {
        Menu fileMenuItem = new Menu("File");
        pauseMenuItem = new MenuItem("Pause (Strg+P)");
        resumeMenuItem = new MenuItem("Resume (Strg+P)");
        resumeMenuItem.setDisable(true);
        MenuItem stopMenuItem = new MenuItem("Stop (Alt+F4)");

        fileMenuItem.getItems().addAll(pauseMenuItem,resumeMenuItem,stopMenuItem);
        // link menu items to events
        pauseMenuItem.setOnAction(e ->{
            togglePause();
        });
        resumeMenuItem.setOnAction(e ->{
            togglePause();
        });
        stopMenuItem.setOnAction(e ->{
            System.exit(0);
        });

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenuItem);
        return menuBar;
    }

    private void addToLegend(String s, Color color){
        //⬤
        Text text = new Text("⬤");
        text.setFill(color);
        TextFlow tempTextFlow = new TextFlow();
        tempTextFlow.getChildren().addAll(text,new Text(" "+s));
        vboxLeft.getChildren().add(tempTextFlow);
    }

    private void updateInfo(ArrayList<Squirrel> squirrelArrayList){
        VBox vBox = (VBox) infoPaneRight.getCenter();
        vBox.getChildren().clear();

        ArrayList<Squirrel> copySquirrelArray = new ArrayList<Squirrel>(squirrelArrayList);

        for(Squirrel s : copySquirrelArray){
            if(s == null) continue;
            try{
                MasterSquirrelBot bot = (MasterSquirrelBot)s;
                Label label = new Label(bot.getName()+": "+s.getEnergy());
                vBox.getChildren().add(label);
                continue;
            }catch (Exception e){

            }
            Label label = new Label(s.getType()+": "+s.getEnergy());
            vBox.getChildren().add(label);
        }
    }

    private void drawBoardInPane(AEntity[][] boardArray){
        if(canvasPane.getChildren().isEmpty()){
            canvasPane.setCenter(new Canvas(boardArray.length * pixelScale, boardArray[0].length * pixelScale));
        }
        Canvas canvas = (Canvas) canvasPane.getCenter();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        for (int x = 0; x < boardArray.length; x++){
            for (int y = 0; y < boardArray[0].length; y++){
                EntityType type = EntityType.EMPTY;
                if (boardArray[x][y] != null) type = boardArray[x][y].getType();

                graphicsContext.setFill(type.getColor());
                graphicsContext.fillRect(x* pixelScale,y* pixelScale, pixelScale, pixelScale);
            }
        }
    }

    @Override
    public void render(BoardView boardView) {
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

    public void setGame(GameImpl game) {
        this.game = game;
    }

    public void setStage(Stage stage){
        this.stage = stage;
        stage.getScene().setOnKeyPressed(this::onKeyPressed);
    }
}
