package mastersquirrel;

import mastersquirrel.entities.*;
import mastersquirrel.entities.bots.MasterSquirrelBot;
import mastersquirrel.entities.bots.botapi.BotControllerFactory;
import mastersquirrel.nanaastar.Pathfinding;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Board {
    private final BoardConfig boardConfig;
    private final int xBoardSize;
    private final int yBoardSize;
    private final EntitySet entitySet = EntitySet.getInstance();
    private int[][] alreadyUsed;
    private final RandomDirection rD = RandomDirection.getInstance();

    public Board(BoardConfig boardConfig){
        this.boardConfig = boardConfig;
        xBoardSize = boardConfig.BOARD_SIZE.getX();
        yBoardSize = boardConfig.BOARD_SIZE.getY();
        alreadyUsed = new int[xBoardSize][yBoardSize];
        if(boardConfig.botMode) setBots();
        setBoarderWallElements(boardConfig.BOARD_SIZE.getX(),yBoardSize);
        setEntities(boardConfig.BOARD_SIZE.getX(),yBoardSize);
    }

    public void reset(){
        if(boardConfig.botMode) {
            State.setRemainingSteps(boardConfig.botSteps);
            AEntity[] aEntities = EntitySet.getInstance().getAll();
            ArrayList<MasterSquirrelBot> masterSquirrelBots = new ArrayList<MasterSquirrelBot>();
            for (AEntity entity : aEntities) {
                try {
                    masterSquirrelBots.add((MasterSquirrelBot) entity);
                    entity.resetEnergy();
                } catch (Exception e) {

                }
            }
            EntitySet.getInstance().clear();
            alreadyUsed = new int[xBoardSize][yBoardSize];
            setBoarderWallElements(boardConfig.BOARD_SIZE.getX(), yBoardSize);
            setEntities(boardConfig.BOARD_SIZE.getX(), yBoardSize);
            for (MasterSquirrelBot m : masterSquirrelBots) {
                entitySet.put(m);
            }
        }
    }

    //creates a 2D Array representing a board with all entities from the entitySet
    public FlattenedBoard flatten(){
        return new FlattenedBoard(xBoardSize,yBoardSize);
    }

    //returns a random unused position on the current board
    private XY getRandomPos(){
        int x = rD.randomInt(1,xBoardSize - 1);
        int y = rD.randomInt(1,yBoardSize - 1);
        while(alreadyUsed[x][y] == 1){
            x = rD.randomInt(1,xBoardSize - 1);
            y = rD.randomInt(1,yBoardSize - 1);
        }
        alreadyUsed[x][y] = 1;
        return new XY(x,y);
    }

    private void setBots(){
        for (String s:boardConfig.bots) {
            try {
                BotControllerFactory botFactory = (BotControllerFactory) Class.forName("mastersquirrel.botimpls21."+s).getConstructor().newInstance();
                entitySet.put(new MasterSquirrelBot(getRandomPos(), botFactory));
            } catch (InstantiationException | IllegalAccessException
                    | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //creates and places entities
    private void setEntities(int xLen, int yLen) {
        AEntity initRef;

        for (int i = 0; i < boardConfig.WALL_COUNT; i++) {
            XY randomPos = getRandomPos();
            initRef = new Wall(randomPos);
            entitySet.put(initRef);
        }

        for (int i = 0; i < boardConfig.BAD_BEAST_COUNT; i++) {
            XY randomPos = getRandomPos();
            initRef = new BadBeast(randomPos);
            entitySet.put(initRef);
        }

        for (int i = 0; i < boardConfig.GOOD_BEAST_COUNT; i++) {
            XY randomPos = getRandomPos();
            initRef = new GoodBeast(randomPos);
            entitySet.put(initRef);
        }

        for (int i = 0; i < boardConfig.BAD_PLANT_COUNT; i++) {
            XY randomPos = getRandomPos();
            initRef = new BadPlant(randomPos);
            entitySet.put(initRef);
        }

        for (int i = 0; i < boardConfig.GOOD_PLANT_COUNT; i++) {
            XY randomPos = getRandomPos();
            initRef = new GoodPlant(randomPos);
            entitySet.put(initRef);
        }

        for (int i = 0; i < boardConfig.MINI_SQUIRREL_COUNT; i++) {
            XY randomPos = getRandomPos();
            initRef = new MiniSquirrel(12,randomPos,null);
            entitySet.put(initRef);
        }

        for (int i = 0; i < boardConfig.MASTER_SQUIRREL_COUNT; i++) {
            XY randomPos = getRandomPos();
            initRef = new MasterSquirrel(randomPos);
            entitySet.put(initRef);
        }
    }

    //creates border elements surrounding the board
    private void setBoarderWallElements(int xLen, int yLen) {
        //Top and bottom border
        for (int i = 0; i<xLen; i++){
            //add wall element to list
            entitySet.put(new Wall(new XY(i,0)));
            entitySet.put(new Wall(new XY(i,yLen-1)));
        }
        //Left and right border
        for (int i = 1; i<yLen-1; i++){
            //add wall element to list
            entitySet.put(new Wall(new XY(0,i)));
            entitySet.put(new Wall(new XY(xLen-1,i)));
        }
    }
}