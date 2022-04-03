package mastersquirrel;

import mastersquirrel.entities.AEntity;
import mastersquirrel.entities.EntityType;

public class FlattenedBoard implements EntityContext, BoardView{

    AEntity[][] boardArray;

    public FlattenedBoard(int x, int y){
        boardArray = new AEntity[x][y];
    }

    @Override
    public EntityType getEntityType(int x, int y) {
        if(boardArray[x][y] != null){
            return boardArray[x][y].getType();
        }
        return null;
    }

    @Override
    public XY getSize() {
        return new XY(boardArray[0].length,boardArray[1].length);
    }

    @Override
    public AEntity[][] getBoardArray() {
        return boardArray;
    }

    @Override
    public void move(AEntity entity, XY moveDirection) {

    }
}
