package mastersquirrel;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.stage.WindowEvent;
import mastersquirrel.util.ui.*;

import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends Application {

    private static UI ui;
    private static GameImpl game;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mastersquirrel");
        primaryStage.setScene(new Scene((FxUI) ui, 1100, 700));

        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                System.exit(0);
            }
        });
        ((FxUI) ui).setStage(primaryStage);
    }

    public static void main(String[] args) {

        BoardConfig boardConfig = new BoardConfig(
                new XY(50, 50),
                36,
                10,
                10,
                10,
                10,
                0,
                0);

        Board board = new Board(boardConfig);
        State state = new State(board);

        if (args.length != 0 && args[0].equals("-console")) {
            System.out.println("Running on console:");
            ui = new ConsoleUI();
            game = new GameImpl(state, ui);
            startGame(state.getBoard().flatten());
        } else {
            System.out.println("Running on GUI:");
            //run GUI view of game
            ui = new FxUI();
            game = new GameImpl(state, ui);
            FxUI fxUI = (FxUI)ui;
            fxUI.setGame(game);
            startGame(state.getBoard().flatten());
            launch(args);
        }
    }

    public static void startGame(BoardView board) {
        //delay in seconds
        double waitFor = 0.01;
        //convert for 3 rounds in milliseconds
        waitFor = (waitFor / 3) * 1000;

        Timer timer = new Timer();

        System.out.print("Starting Game");

        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                i++;
                if (i == 4) {
                    timer.cancel();
                    System.out.println();
                    game.run(board);
                    return;
                }
                System.out.print(".");
            }
        }, 0, (int) waitFor);
    }
}