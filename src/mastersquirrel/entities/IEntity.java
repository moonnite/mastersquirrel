package mastersquirrel.entities;

import mastersquirrel.XY;

public interface IEntity extends Comparable{
    //warum nicht diese beiden Methoden gleich in die abstract class als abstract methoden und extends Comparable
    void nextStep();
    void updatePosition(XY pos);
}