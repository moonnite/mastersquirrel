package mastersquirrel.entities;

import mastersquirrel.EntityContext;
import mastersquirrel.XY;

public interface IEntity extends Comparable{
    //warum nicht diese beiden Methoden gleich in die abstract class als abstract methoden und extends Comparable
    void nextStep(EntityContext entityContext);
    void updatePosition(XY pos);
}