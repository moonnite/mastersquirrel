package mastersquirrel;

import mastersquirrel.entities.*;

public class Board {
    private final BoardConfig boardConfig;
    private final EntitySet entitySet = EntitySet.getInstance();
    private final int[][] alreadyUsed;
    private final RandomDirection rD = RandomDirection.getInstance();


    public Board(BoardConfig boardConfig){
        this.boardConfig = boardConfig;
        alreadyUsed = new int[boardConfig.BOARD_SIZE.getXLen()][boardConfig.BOARD_SIZE.getYLen()];

        setBoarderWallElements(boardConfig.BOARD_SIZE.getXLen(),boardConfig.BOARD_SIZE.getYLen());
        setEntities(boardConfig.BOARD_SIZE.getXLen(),boardConfig.BOARD_SIZE.getYLen());
    }

    //creates a 2D Array representing a board with all entities from the entitySet
    public FlattenedBoard flatten(){
        return new FlattenedBoard(boardConfig.BOARD_SIZE.getXLen(),boardConfig.BOARD_SIZE.getYLen());
    }

    //returns a random unused position on the current board
    private XY getRandomPos(){
        int x = rD.randomInt(1,boardConfig.BOARD_SIZE.getXLen() - 1);
        int y = rD.randomInt(1,boardConfig.BOARD_SIZE.getYLen() - 1);
        while(alreadyUsed[x][y] == 1){
            x = rD.randomInt(1,boardConfig.BOARD_SIZE.getXLen() - 1);
            y = rD.randomInt(1,boardConfig.BOARD_SIZE.getYLen() - 1);
        }
        alreadyUsed[x][y] = 1;
        return new XY(x,y);
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
    }

    //creates border elements surrounding the board
    private void setBoarderWallElements(int xLen, int yLen) {
        Wall[] wallArr = new Wall[2 * xLen + 2 * yLen - 4];

        int count = 0;

        //iterate over whole array
        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                //only place wall element when current element is part of boarder
                if (i == 0 || j == 0 || i == xLen - 1 || j == yLen - 1) {
                    wallArr[count] = new Wall(new XY(i,j));
                    //add wall element to list
                    entitySet.put(wallArr[count]);
                    count++;
                }
            }
        }
    }
}
