package mastersquirrel.entities;
import mastersquirrel.EntityContext;
import mastersquirrel.nanaastar.Pathfinding;
import mastersquirrel.XY;

public abstract class Squirrel extends Movable{
    protected Squirrel(int e, XY pos) {
        super(e, pos);
    }

    public void onWallCollision(int energyDamage){
        updateEnergy(energyDamage);
        cooldown = 3;
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        if(cooldown > 0){
            cooldown--;
            return;
        }
        super.nextStep(entityContext);
    }

}