package mastersquirrel.entities.bots;

import mastersquirrel.*;
import mastersquirrel.entities.EntityType;
import mastersquirrel.entities.MasterSquirrel;
import mastersquirrel.entities.bots.botapi.BotController;
import mastersquirrel.entities.bots.botapi.BotControllerFactory;
import mastersquirrel.entities.bots.botapi.ControllerContext;
import mastersquirrel.nanaastar.Pathfinding;

public class MasterSquirrelBot extends MasterSquirrel{

    private final BotController masterBotController;

    private final int viewRadius = 15;

    public MasterSquirrelBot(XY pos) {
        super(pos);
        type = EntityType.MASTERSQUIRREL;

        BotControllerFactory botControllerFactory = new BotControllerFactory(){};
        masterBotController = botControllerFactory.createMasterBotController();
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        masterBotController.nextStep(new ControllerContextImpl(entityContext, this));
    }

    static class ControllerContextImpl implements ControllerContext {

        FlattenedBoard flattenedBoard;
        MasterSquirrelBot masterSquirrelBot;

        public ControllerContextImpl(EntityContext entityContext, MasterSquirrelBot masterSquirrelBot){
             flattenedBoard = (FlattenedBoard) entityContext;
             this.masterSquirrelBot = masterSquirrelBot;
        }

        @Override
        public XY getViewLowerLeft() {
            int yBound = flattenedBoard.getSize().getY();

            int xView = masterSquirrelBot.getPosition().getX()-masterSquirrelBot.viewRadius;
            int yView = masterSquirrelBot.getPosition().getY()+masterSquirrelBot.viewRadius;

            if(xView < 0) xView = 0;
            if(yView > yBound) yView = yBound;

            return new XY(xView,yView);
        }

        @Override
        public XY getViewUpperRight() {
            int xBound = flattenedBoard.getSize().getX();

            int xView = masterSquirrelBot.getPosition().getX()+masterSquirrelBot.viewRadius;
            int yView = masterSquirrelBot.getPosition().getY()-masterSquirrelBot.viewRadius;

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
            flattenedBoard.move(masterSquirrelBot, direction);
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {
            XY movePos = XY.add(masterSquirrelBot.getPosition(), direction);

            //if position where minisquirrel should be spawned is already used, generate new pos (infinite loop, when mastersquirrel is surrounded by entities)
            for(int i = 0; i<=9 && flattenedBoard.getBoardArray()[movePos.getX()][movePos.getY()] != null; i++){
                if(i==9) return;
                movePos = XY.add(masterSquirrelBot.getPosition(),masterSquirrelBot.getPosition().genNewDir());
            }

            MiniSquirrelBot miniSquirrelBot = new MiniSquirrelBot(energy, movePos, masterSquirrelBot);
            EntitySet.getInstance().put(miniSquirrelBot);
        }

        @Override
        public int getEnergy() {
            return masterSquirrelBot.getEnergy();
        }
    }
}