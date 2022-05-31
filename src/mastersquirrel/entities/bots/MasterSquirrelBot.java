package mastersquirrel.entities.bots;

import mastersquirrel.*;
import mastersquirrel.entities.AEntity;
import mastersquirrel.entities.EntityType;
import mastersquirrel.entities.MasterSquirrel;
import mastersquirrel.entities.MiniSquirrel;
import mastersquirrel.entities.bots.botapi.*;

public class MasterSquirrelBot extends MasterSquirrel{

    private final BotController masterBotController;
    private final int viewRadius = 4;

    public MasterSquirrelBot(XY pos, BotControllerFactory bot) {
        super(pos);
        type = EntityType.MASTERSQUIRREL;

        BotControllerFactory botControllerFactory = new BotControllerFactoryImpl(){};
        masterBotController = botControllerFactory.createMasterBotController();
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        masterBotController.nextStep(new ControllerContextImpl(entityContext, this));
    }

    static class ControllerContextImpl implements ControllerContext {

        public FlattenedBoard flattenedBoard;
        MasterSquirrelBot masterSquirrelBot;

        public ControllerContextImpl(EntityContext entityContext, MasterSquirrelBot masterSquirrelBot){
             flattenedBoard = (FlattenedBoard) entityContext;
             this.masterSquirrelBot = masterSquirrelBot;
        }

        public AEntity[][] getView(){
            AEntity[][] flattenedBoardArray =  flattenedBoard.getBoardArray();
            AEntity[][] viewArray = new AEntity[getViewUpperRight().getX()-getViewLowerLeft().getX()+1][getViewLowerLeft().getY()-getViewUpperRight().getY()+1];
            for(int i = getViewLowerLeft().getX(), l = 0; i < getViewUpperRight().getX()+1; i++, l++){
                for(int j = getViewUpperRight().getY(), k = 0; j < getViewLowerLeft().getY()+1; j++,k++){
                    viewArray[l][k] = flattenedBoardArray[i][j];
                }
            }
            return viewArray;
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

            if(xView > xBound) xView = xBound;
            if(yView < 0) yView = 0;

            return new XY(xView,yView);
        }

        @Override
        public XY locate() {
            return masterSquirrelBot.getPosition();
        }

        @Override
        public EntityType getEntityAt(XY xy) {
            if (xy.getX()<getViewLowerLeft().getX()
                    || xy.getX()>getViewUpperRight().getX()
                    || xy.getY()>getViewLowerLeft().getY()
                    || xy.getY()<getViewUpperRight().getY()){
                throw new OutOfViewException();
            }
            return flattenedBoard.getBoardArray()[xy.getX()][xy.getY()].getType();
        }

        @Override
        public boolean isMine(XY xy) {
            if(getEntityAt(xy) == EntityType.MINISQUIRREL){
                return ((MiniSquirrel) flattenedBoard.getBoardArray()[xy.getX()][xy.getY()]).getParent() == masterSquirrelBot;
            }
            return false;
        }

        @Override
        public void move(XY direction) {
            if(direction == XY.ZERO_ZERO || Math.abs(direction.getX()) > 1 || Math.abs(direction.getY()) > 1){
                return;
            }
            flattenedBoard.move(masterSquirrelBot, direction);
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {

            if(energy < 100) throw new SpawnException();

            if(energy > masterSquirrelBot.getEnergy()){
                System.out.println("Not enough energy.");
                return;
            }

            XY movePos = XY.add(masterSquirrelBot.getPosition(), direction);

            //if position where minisquirrel should be spawned is already used, generate new pos (infinite loop, when mastersquirrel is surrounded by entities)
            for(int i = 0; i<=90 && flattenedBoard.getBoardArray()[movePos.getX()][movePos.getY()] != null; i++){
                if(i==90) return;
                movePos = XY.add(masterSquirrelBot.getPosition(),masterSquirrelBot.getPosition().genNewDir());
            }

            masterSquirrelBot.updateEnergy(-energy);
            MiniSquirrelBot miniSquirrelBot = new MiniSquirrelBot(energy, movePos, masterSquirrelBot, masterSquirrelBot.getBot());
            EntitySet.getInstance().put(miniSquirrelBot);
        }

        @Override
        public int getEnergy() {
            return masterSquirrelBot.getEnergy();
        }

        @Override
        public void implode(int impactRadius) {
            // not supported
        }

        @Override
        public XY directionOfMaster() {
            // not supported
            return null;
        }

        @Override
        public long getRemainingSteps() {
            return State.getRemainingSteps();
        }
    }
}