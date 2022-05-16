package mastersquirrel.entities.bots.botapi;

import mastersquirrel.XY;
import mastersquirrel.entities.EntityType;

public interface ControllerContext {
    XY getViewLowerLeft();
    XY getViewUpperRight();
    EntityType getEntityAt(XY xy);
    void move(XY direction);
    void spawnMiniBot(XY direction, int energy);
    int getEnergy();
}