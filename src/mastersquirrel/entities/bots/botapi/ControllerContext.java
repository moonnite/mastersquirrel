package mastersquirrel.entities.bots.botapi;

import mastersquirrel.XY;
import mastersquirrel.entities.EntityType;

public interface ControllerContext {
    XY getViewLowerLeft();
    XY getViewUpperRight();
    XY locate();
    EntityType getEntityAt(XY xy) throws OutOfViewException;
    boolean isMine(XY xy) throws OutOfViewException;
    void move(XY direction);
    void spawnMiniBot(XY direction, int energy) throws SpawnException;
    int getEnergy();
    void implode(int impactRadius);
    XY directionOfMaster();
    long getRemainingSteps();
    default void shout(String text){};
}