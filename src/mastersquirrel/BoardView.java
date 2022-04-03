package mastersquirrel;

import mastersquirrel.entities.AEntity;
import mastersquirrel.entities.EntityType;

public interface BoardView {
    public EntityType getEntityType(int x, int y);
    public XY getSize();
    public AEntity[][] getBoardArray();
}