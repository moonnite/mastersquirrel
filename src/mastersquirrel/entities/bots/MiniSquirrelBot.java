package mastersquirrel.entities.bots;

import mastersquirrel.EntityContext;
import mastersquirrel.FlattenedBoard;
import mastersquirrel.XY;
import mastersquirrel.entities.AEntity;
import mastersquirrel.entities.EntityType;
import mastersquirrel.entities.MiniSquirrel;
import mastersquirrel.entities.bots.botapi.BotController;
import mastersquirrel.entities.bots.botapi.BotControllerFactory;
import mastersquirrel.entities.bots.botapi.ControllerContext;

public class MiniSquirrelBot extends MiniSquirrel{

    private final BotController miniBotController;

    private final int viewRadius = 10;
    private int impactRadius = 5;

    public MiniSquirrelBot(int energy, XY pos, AEntity parent) {
        super(energy, pos, parent);
        type = EntityType.MINISQUIRREL;

        BotControllerFactory botControllerFactory = new BotControllerFactory(){};
        miniBotController = botControllerFactory.createMiniBotController();
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        miniBotController.nextStep(new ControllerContextImpl(entityContext, this));
    }

    public void setImpactRadius(int impactRadius){
        // clamp impact radius input between 2 and 10
        this.impactRadius = (impactRadius > 10) ? 10 : (Math.max(impactRadius, 2));
    }

    static class ControllerContextImpl implements ControllerContext {

        public FlattenedBoard flattenedBoard;
        MiniSquirrelBot miniSquirrelBot;

        public ControllerContextImpl(EntityContext entityContext, MiniSquirrelBot miniSquirrelBot){
            flattenedBoard = (FlattenedBoard) entityContext;
            this.miniSquirrelBot = miniSquirrelBot;
        }

        public void implode(){

            double impactArea = miniSquirrelBot.impactRadius * miniSquirrelBot.impactRadius * Math.PI;

            for(AEntity[] column : getView()){
                for(AEntity e : column){
                    if(e == null) continue;

                    // check for entities that should not be affected
                    EntityType eType = e.getType();
                    //ignore
                    if(eType == EntityType.WALL || e == miniSquirrelBot.getParent() ||
                            eType == EntityType.MINISQUIRREL && (((MiniSquirrel)e).getParent() == miniSquirrelBot.getParent()))
                        continue;

                    // calculate distance
                    int diffX = Math.abs(e.getPosition().getX() - miniSquirrelBot.getPosition().getX());
                    int diffY = Math.abs(e.getPosition().getY() - miniSquirrelBot.getPosition().getY());
                    int distance = Math.max(diffX,diffY);
                    // calculate damage based on distance
                    int energyLoss = (int)(200*(getEnergy()/impactArea) * (1 - distance/miniSquirrelBot.impactRadius));

                    System.out.println(energyLoss);
                    // implode
                    switch (e.getType()){
                        case MASTERSQUIRREL, MINISQUIRREL, GOODBEAST, GOODPLANT -> {
                            int energy = (e.getEnergy()-energyLoss < 0) ? e.getEnergy() : energyLoss;
                            e.updateEnergy(-energy);
                            miniSquirrelBot.getParent().updateEnergy(energy);
                        }
                        case BADPLANT, BADBEAST -> {
                            int energy = (e.getEnergy()+energyLoss > 0) ? e.getEnergy() : energyLoss;
                            e.updateEnergy(energy);
                        }
                    }
                }
            }
            miniSquirrelBot.kill();
        }

        @Override
        public XY getMasterDir(){
            XY parentPos = miniSquirrelBot.getParent().getPosition();
            XY miniPos = miniSquirrelBot.getPosition();

            int x = (parentPos.getX() > miniPos.getX()) ? 1 : 0;
            int y = (parentPos.getY() > miniPos.getY()) ? 1 : 0;

            return new XY(x,y);
        }

        public AEntity[][] getView(){
            AEntity[][] flattenedBoardArray =  flattenedBoard.getBoardArray();
            AEntity[][] viewArray = new AEntity[getViewUpperRight().getX()-getViewLowerLeft().getX()+1][getViewLowerLeft().getY()-getViewUpperRight().getY()+1];
            for(int i = getViewLowerLeft().getX(), l = 0; i < getViewUpperRight().getX(); i++, l++){
                for(int j = getViewUpperRight().getY(), k = 0; j < getViewLowerLeft().getY(); j++,k++){
                    viewArray[l][k] = flattenedBoardArray[i][j];
                }
            }
            return viewArray;
        }

        @Override
        public XY getViewLowerLeft() {
            int yBound = flattenedBoard.getSize().getY();

            int xView = miniSquirrelBot.getPosition().getX()- miniSquirrelBot.viewRadius;
            int yView = miniSquirrelBot.getPosition().getY()+ miniSquirrelBot.viewRadius;

            if(xView < 0) xView = 0;
            if(yView > yBound) yView = yBound;

            return new XY(xView,yView);
        }

        @Override
        public XY getViewUpperRight() {
            int xBound = flattenedBoard.getSize().getX();

            int xView = miniSquirrelBot.getPosition().getX()+ miniSquirrelBot.viewRadius;
            int yView = miniSquirrelBot.getPosition().getY()- miniSquirrelBot.viewRadius;

            if(xView > xBound) xView = xBound;
            if(yView < 0) yView = 0;

            return new XY(xView,yView);
        }

        @Override
        public EntityType getEntityAt(XY xy) {
            return flattenedBoard.getBoardArray()[xy.getX()][xy.getY()].getType();
        }

        @Override
        public void move(XY direction) {
            flattenedBoard.move(miniSquirrelBot, direction);
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {
            // not supported
        }

        @Override
        public int getEnergy() {
            return miniSquirrelBot.getEnergy();
        }
    }

}
