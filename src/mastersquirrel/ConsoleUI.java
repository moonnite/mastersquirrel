package mastersquirrel;

import mastersquirrel.entities.AEntity;
import mastersquirrel.entities.EntityType;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleUI implements UI {

    Scanner s = new Scanner(System.in);

    @Override
    public void render(BoardView view) {
        AEntity[][] boardArray= view.getBoardArray();

        //print Board
        for (int i = 0; i < boardArray[0].length; i++) {
            for (int j = 0; j < boardArray.length; j++) {
                EntityType type = EntityType.EMPTY;
                if(boardArray[j][i] != null){
                    type = boardArray[j][i].getType();
                }
                switch (type) {
                    case GOODBEAST -> {
                        System.out.print("\u001B[92m");
                        System.out.print("■");
                    }
                    case BADBEAST -> {
                        System.out.print("\u001B[91m");
                        System.out.print("■");
                    }
                    case GOODPLANT -> {
                        System.out.print("\u001B[93m");
                        System.out.print("■");
                    }
                    case BADPLANT -> {
                        System.out.print("\u001B[95m");
                        System.out.print("■");
                    }
                    case WALL -> {
                        System.out.print("\u001B[96m");
                        System.out.print("■");
                    }
                    case HANDOPERATEDMASTERSQUIRREL -> {
                        System.out.print("\u001B[97m");
                        System.out.print("■");
                    }
                    case EMPTY -> {
                        System.out.print("\u001B[30m");
                        System.out.print("□");
                    }
                    default -> {
                        System.out.print("\u001B[30m");
                        System.out.print("□");
                    }
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        printStats(boardArray);

    }

    public void printStats(AEntity[][] boardArray) {
        for (int i = 0; i < boardArray[0].length; i++) {
            for (int j = 0; j < boardArray.length; j++) {
                EntityType type = EntityType.EMPTY;
                if (boardArray[j][i] != null) {
                    if (boardArray[j][i].getType() != EntityType.WALL) {
                        System.out.println(boardArray[j][i].getID() + " " + boardArray[j][i].getType() + " Energy:" + boardArray[j][i].getEnergy()+ "                         " + boardArray[j][i].getPosition());
                    }
                }
            }
        }
    }

    public String getInput(){
        return s.nextLine();
    }
}
