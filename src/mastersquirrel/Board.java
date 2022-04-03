package mastersquirrel;

import mastersquirrel.entities.*;

public class Board {
    private BoardConfig boardConfig;
    private EntitySet entitySet;

    public Board(){
        setBoardConfig();
        setBoarderWallElements(boardConfig.size.getXLen(),boardConfig.size.getYLen());
        setEntities(boardConfig.size.getXLen(),boardConfig.size.getYLen());
    }

    public FlattenedBoard flatten(){
        FlattenedBoard flattenedBoard = new FlattenedBoard(boardConfig.size.getXLen(),boardConfig.size.getYLen());
        AEntity[] entities = entitySet.getAll();
        for (AEntity entity : entities) {
            int x = entity.getPosition().getXLen();
            int y = entity.getPosition().getYLen();
            flattenedBoard.boardArray[x][y] = entity;
        }
        return flattenedBoard;
    }

    //TODO Check for empty place in array (random)

    private void setEntities(int xLen, int yLen){

        AEntity initRef;

        for(int i = 0; i<boardConfig.wallCount; i++) {
            initRef = new Wall();
            initRef.updatePosition(RandomDirection.getInstance().getRandom(1, xLen - 2, 1, yLen - 2));
            entitySet.put(initRef);
        }

        for(int i = 0; i<boardConfig.badBeastCount; i++) {
            initRef = new BadBeast();
            initRef.updatePosition(RandomDirection.getInstance().getRandom(1, xLen - 2, 1, yLen - 2));
            entitySet.put(initRef);
        }

        for(int i = 0; i<boardConfig.goodBeastCount; i++) {
            initRef = new GoodBeast();
            initRef.updatePosition(RandomDirection.getInstance().getRandom(1, xLen - 2, 1, yLen - 2));
            entitySet.put(initRef);
        }

        for(int i = 0; i<boardConfig.badPlantCount; i++) {
            initRef = new BadPlant();
            initRef.updatePosition(RandomDirection.getInstance().getRandom(1, xLen - 2, 1, yLen - 2));
            entitySet.put(initRef);
        }

        for(int i = 0; i<boardConfig.goodPlantCount; i++) {
            initRef = new GoodPlant();
            initRef.updatePosition(RandomDirection.getInstance().getRandom(1, xLen - 2, 1, yLen - 2));
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
                    wallArr[count] = new Wall();
                    wallArr[count].updatePosition(new XY(i,j));
                    //add wall element to list
                    entitySet.put(wallArr[count]);
                    count++;
                }
            }
        }
    }

    public void setBoardConfig() {
        boardConfig = new BoardConfig(
                new XY(10, 10),
                5,
                2,
                2,
                2,
                2);
        entitySet = EntitySet.getInstance();
    }
}
