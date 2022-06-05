package mastersquirrel;

import java.util.ArrayList;

public class BoardConfig {
    public final XY BOARD_SIZE;
    public final int WALL_COUNT;
    public final int GOOD_BEAST_COUNT;
    public final int BAD_BEAST_COUNT;
    public final int GOOD_PLANT_COUNT;
    public final int BAD_PLANT_COUNT;

    public final int MINI_SQUIRREL_COUNT;
    public final int MASTER_SQUIRREL_COUNT;

    public final boolean botMode = true;
    public final long botSteps = 80;

    public final ArrayList<String> bots = new ArrayList<String>();

    public BoardConfig(XY BOARD_SIZE,
                       int WALL_COUNT,
                       int GOOD_BEAST_COUNT,
                       int BAD_BEAST_COUNT,
                       int GOOD_PLANT_COUNT,
                       int BAD_PLANT_COUNT,
                       int MINI_SQUIRREL_COUNT,
                       int MASTER_SQUIRREL_COUNT) {
        this.BOARD_SIZE = BOARD_SIZE;
        this.WALL_COUNT = WALL_COUNT;
        this.GOOD_BEAST_COUNT = GOOD_BEAST_COUNT;
        this.BAD_BEAST_COUNT = BAD_BEAST_COUNT;
        this.GOOD_PLANT_COUNT = GOOD_PLANT_COUNT;
        this.BAD_PLANT_COUNT = BAD_PLANT_COUNT;
        this.MINI_SQUIRREL_COUNT = MINI_SQUIRREL_COUNT;
        this.MASTER_SQUIRREL_COUNT = MASTER_SQUIRREL_COUNT;

        //bots.add("Terminator");
        bots.add("Namn");
        bots.add("Asam");
    }
}