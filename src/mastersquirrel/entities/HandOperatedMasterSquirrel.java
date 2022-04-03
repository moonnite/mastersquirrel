package mastersquirrel.entities;

import mastersquirrel.XY;

import java.util.Scanner;

public class HandOperatedMasterSquirrel extends MasterSquirrel{

    Scanner s = new Scanner(System.in);

    public HandOperatedMasterSquirrel() {
        super();
    }

    @Override
    public void nextStep() {
        String input = s.nextLine();
        switch(input){
            case "w"-> {
                position = XY.add(position, XY.UP);
            }
            case "a" -> {
                position = XY.add(position, XY.LEFT);
            }
            case "s" -> {
                position = XY.add(position, XY.DOWN);
            }
            case "d" -> {
                position = XY.add(position, XY.RIGHT);
            }
            default -> {
                System.out.println("Wrong Input");
            }
        }
    }
}