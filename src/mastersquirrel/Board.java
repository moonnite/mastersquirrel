package mastersquirrel;

import mastersquirrel.entities.*;

public class Board {
    private BoardConfig boardConfig;
    private EntitySet entitySet;
    private final int[][] alreadyUsed;
    private final RandomDirection rD = RandomDirection.getInstance();


    public Board(){
        setBoardConfig();
        setBoarderWallElements(boardConfig.size.getXLen(),boardConfig.size.getYLen());
        alreadyUsed = new int[boardConfig.size.getXLen()][boardConfig.size.getYLen()];
        setEntities(boardConfig.size.getXLen(),boardConfig.size.getYLen());
    }

    public FlattenedBoard flatten(){
        FlattenedBoard flattenedBoard = new FlattenedBoard(boardConfig.size.getXLen(),boardConfig.size.getYLen());
        AEntity[] entities = entitySet.getAll();
        AEntity initRef;
        for (int i = 0; i < entities.length; i++) {
            initRef = entities[i];
            int x = initRef.getPosition().getXLen();
            int y = initRef.getPosition().getYLen();
            flattenedBoard.boardArray[x][y] = entities[i];
        }
        return flattenedBoard;
    }

    private XY getRandomPos(){
        int x = rD.randomInt(1,boardConfig.size.getXLen() - 1);
        int y = rD.randomInt(1,boardConfig.size.getYLen() - 1);
        while(alreadyUsed[x][y] == 1){
            x = rD.randomInt(1,boardConfig.size.getXLen() - 1);
            y = rD.randomInt(1,boardConfig.size.getYLen() - 1);
        }
        alreadyUsed[x][y] = 1;
        return new XY(x,y);
    }

    private void setEntities(int xLen, int yLen){

        AEntity initRef;

        for(int i = 0; i<boardConfig.wallCount; i++) {
            XY randomPos = getRandomPos();
            initRef = new Wall(randomPos);
            entitySet.put(initRef);
        }

        for(int i = 0; i<boardConfig.badBeastCount; i++) {
            XY randomPos = getRandomPos();
            initRef = new BadBeast(randomPos);
            entitySet.put(initRef);
        }

        for(int i = 0; i<boardConfig.goodBeastCount; i++) {
            XY randomPos = getRandomPos();
            initRef = new GoodBeast(randomPos);
            entitySet.put(initRef);
        }

        for(int i = 0; i<boardConfig.badPlantCount; i++) {
            XY randomPos = getRandomPos();
            initRef = new BadPlant(randomPos);
            entitySet.put(initRef);
        }
        for(int i = 0; i<boardConfig.goodPlantCount; i++) {
            XY randomPos = getRandomPos();
            initRef = new GoodPlant(randomPos);
            entitySet.put(initRef);
        }
    }

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

    public void setBoardConfig() {
        boardConfig = new BoardConfig(
                new XY(20, 10),
                20,
                4,
                5,
                3,
                4);
        entitySet = EntitySet.getInstance();
    }
}
