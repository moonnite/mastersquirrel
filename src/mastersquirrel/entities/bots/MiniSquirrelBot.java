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

    static class ControllerContextImpl implements ControllerContext {

        FlattenedBoard flattenedBoard;
        MiniSquirrelBot miniSquirrelBot;

        public ControllerContextImpl(EntityContext entityContext, MiniSquirrelBot miniSquirrelBot){
            flattenedBoard = (FlattenedBoard) entityContext;
            this.miniSquirrelBot = miniSquirrelBot;
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
