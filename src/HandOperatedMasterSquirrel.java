import java.util.Scanner;

public class HandOperatedMasterSquirrel extends MasterSquirrel{

    Scanner s = new Scanner(System.in);

    private final Position UP = new Position(0,-1);
    private final Position DOWN = new Position(0,1);
    private final Position LEFT = new Position(-1,0);
    private final Position RIGHT = new Position(1,0);

    public HandOperatedMasterSquirrel() {
        super();
    }

    @Override
    public void nextStep() {
        String input = s.nextLine();
        switch(input){
            case "w"-> {
                position = position.addPosition(position, UP);
            }
            case "a" -> {
                position = position.addPosition(position, LEFT);
            }
            case "s" -> {
                position = position.addPosition(position, DOWN);
            }
            case "d" -> {
                position = position.addPosition(position, RIGHT);
            }
            default -> {
                System.out.println("Wrong Input");
            }
        }
    }
}