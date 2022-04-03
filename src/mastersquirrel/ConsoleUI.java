package mastersquirrel;

import mastersquirrel.entities.AEntity;
import mastersquirrel.entities.EntityType;

public class ConsoleUI implements UI {
    @Override
    public void render(BoardView view) {
        AEntity[][] boardArray= view.getBoardArray();

        for (int i = 0; i < boardArray[1].length; i++) {
            for (int j = 0; j < boardArray[0].length; j++) {
                EntityType type = EntityType.EMPTY;
                if(boardArray[i][j] != null){
                    type = boardArray[i][j].getType();
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
                        System.out.print("\u001B[32m");
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


    }
}
