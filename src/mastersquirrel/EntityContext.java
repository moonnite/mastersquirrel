package mastersquirrel;

import mastersquirrel.entities.AEntity;

public interface EntityContext {
    public XY getSize();
    public void move(AEntity entity, XY moveDirection);
}