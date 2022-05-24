package mastersquirrel.entities;

import mastersquirrel.*;
import mastersquirrel.entities.bots.MiniSquirrelBot;
import mastersquirrel.entities.bots.botapi.BotController;
import mastersquirrel.entities.bots.botapi.BotControllerFactory;
import mastersquirrel.entities.bots.botapi.ControllerContext;

public class HandOperatedMasterSquirrel extends MasterSquirrel{

    private XY dir;
    private boolean inputUpdated = false;
    private final int viewRadius = 100;

    private final BotController handOperatedMasterSquirrelController;

    public HandOperatedMasterSquirrel(XY pos) {
        super(pos);
        type = EntityType.HANDOPERATEDMASTERSQUIRREL;
        BotControllerFactory botControllerFactory = new BotControllerFactory(){};
        handOperatedMasterSquirrelController = new BotController() {
            @Override
            public void nextStep(ControllerContext controllerContext) {
                if(cooldown > 0){
                    cooldown--;
                    return;
                }

                if(inputUpdated) {
                    controllerContext.move(dir);
                    inputUpdated = false;
                }

                if(newMiniSquirrelSpawn){
                    controllerContext.spawnMiniBot(newMiniSquirrelDirection, newMiniSquirrelEnergy);
                    newMiniSquirrelSpawn = false;
                }
            }
        };
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        handOperatedMasterSquirrelController.nextStep(new ControllerContextImpl(entityContext, this));
    }
    
    public void setInput(XY dir){
        inputUpdated = true;
        this.dir = dir;
    }

    static class ControllerContextImpl implements ControllerContext {

        FlattenedBoard flattenedBoard;
        HandOperatedMasterSquirrel handOperatedMasterSquirrel;

        public ControllerContextImpl(EntityContext entityContext, HandOperatedMasterSquirrel handOperatedMasterSquirrel){
            flattenedBoard = (FlattenedBoard) entityContext;
            this.handOperatedMasterSquirrel = handOperatedMasterSquirrel;
        }

        @Override
        public XY getViewLowerLeft() {
            int yBound = flattenedBoard.getSize().getY();

            int xView = handOperatedMasterSquirrel.getPosition().getX()- handOperatedMasterSquirrel.viewRadius;
            int yView = handOperatedMasterSquirrel.getPosition().getY()+ handOperatedMasterSquirrel.viewRadius;

            if(xView < 0) xView = 0;
            if(yView > yBound) yView = yBound;

            return new XY(xView,yView);
        }

        @Override
        public XY getViewUpperRight() {
            int xBound = flattenedBoard.getSize().getX();

            int xView = handOperatedMasterSquirrel.getPosition().getX()+ handOperatedMasterSquirrel.viewRadius;
            int yView = handOperatedMasterSquirrel.getPosition().getY()- handOperatedMasterSquirrel.viewRadius;

            if(xView < xBound) xView = xBound;
            if(yView < 0) yView = 0;

            return new XY(xView,yView);
        }

        @Override
        public EntityType getEntityAt(XY xy) {
            return flattenedBoard.getBoardArray()[xy.getX()][xy.getY()].getType();
        }

        @Override
        public void move(XY direction) {
            flattenedBoard.move(handOperatedMasterSquirrel, direction);
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {
            XY movePos = XY.add(handOperatedMasterSquirrel.getPosition(), direction);

            //if position where minisquirrel should be spawned is already used, generate new pos (infinite loop, when mastersquirrel is surrounded by entities)
            for(int i = 0; i<=9 && flattenedBoard.getBoardArray()[movePos.getX()][movePos.getY()] != null; i++){
                if(i==9) return;
                movePos = XY.add(handOperatedMasterSquirrel.getPosition(),handOperatedMasterSquirrel.getPosition().genNewDir());
            }

            MiniSquirrelBot miniSquirrelBot = new MiniSquirrelBot(energy, movePos, handOperatedMasterSquirrel);
            EntitySet.getInstance().put(miniSquirrelBot);
        }

        @Override
        public int getEnergy() {
            return handOperatedMasterSquirrel.getEnergy();
        }

        @Override
        public void implode() {
            // not supported
        }

        @Override
        public XY getMasterDir() {
            // not supported
            return null;
        }
    }
}