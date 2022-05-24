package mastersquirrel;

import mastersquirrel.entities.AEntity;
import mastersquirrel.entities.MasterSquirrel;

public interface EntityContext {
    public XY getSize();
    public void move(AEntity entity, XY moveDirection);
    public void spawnMiniSquirrel(int energy, MasterSquirrel parent, XY dir);
    public void killAndRespawn(AEntity e);
}