public interface IEntity{

    void nextStep();
    void updateEnergy(int energyDelta);
    void updatePosition(Position pos);

}