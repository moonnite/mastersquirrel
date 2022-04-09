package mastersquirrel;

import mastersquirrel.entities.*;

public class FlattenedBoard implements EntityContext, BoardView{

    AEntity[][] boardArray;
    RandomDirection rD = RandomDirection.getInstance();

    public FlattenedBoard(int x, int y){
        boardArray = new AEntity[x][y];

        AEntity[] entities = EntitySet.getInstance().getAll();
        AEntity initRef;
        for (AEntity entity : entities) {
            initRef = entity;
            boardArray[initRef.getPosition().getXLen()]
                      [initRef.getPosition().getYLen()] = entity;
        }
    }

    //returns a random unused position on the current board
    private XY genRandomPos(){
        int x = rD.randomInt(1,boardArray.length - 1);
        int y = rD.randomInt(1,boardArray[0].length - 1);
        while(boardArray[x][y] != null){
            x = rD.randomInt(1,boardArray.length - 1);
            y = rD.randomInt(1,boardArray[0].length - 1);
        }
        return new XY(x,y);
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
        //später hier kollisionsregeln
        XY newPos = XY.add(entity.getPosition(),moveDirection);
        AEntity entityOnNewPos = boardArray[newPos.getXLen()][newPos.getYLen()];

        //Move on empty + handle Collisions in checkCollisions
        if(!(checkCollisions(entity, entityOnNewPos))){
            entity.updatePosition(newPos);
        }
    }

    public boolean checkCollisions(AEntity entity, AEntity entityOnNewPos){
        if(entityOnNewPos == null) return false;
        switch (entity.getType()){
            case HANDOPERATEDMASTERSQUIRREL -> {
                switch(entityOnNewPos.getType()){
                    case WALL -> {
                        ((HandOperatedMasterSquirrel)entity).onWallCollision(entityOnNewPos.getEnergy());
                        return true;
                    }
                    case GOODPLANT, BADPLANT, GOODBEAST -> {
                        entity.updateEnergy(entityOnNewPos.getEnergy());
                        entityOnNewPos.updatePosition(genRandomPos());
                        return true;
                    }
                    case BADBEAST -> {
                        entity.updateEnergy(entityOnNewPos.getEnergy());
                        if(((BadBeast)entityOnNewPos).onBite()){
                            entityOnNewPos.updatePosition(genRandomPos());
                        }
                        return true;
                    }
                    case MINISQUIRREL -> {
                        if(((MiniSquirrel)entityOnNewPos).getParent() == entity){
                            entity.updateEnergy(entityOnNewPos.getEnergy());
                            entityOnNewPos.updateEnergy(-entityOnNewPos.getEnergy());
                        }
                        else{
                            ((MiniSquirrel)entityOnNewPos).kill();
                        }
                        return true;
                    }
                }
            }
            case MASTERSQUIRREL -> {
                switch(entityOnNewPos.getType()){
                    case WALL -> {
                        ((MasterSquirrel)entity).onWallCollision(entityOnNewPos.getEnergy());
                        return true;
                    }
                    case GOODPLANT, BADPLANT, GOODBEAST -> {
                        entity.updateEnergy(entityOnNewPos.getEnergy());
                        entityOnNewPos.updatePosition(genRandomPos());
                        return true;
                    }
                    case BADBEAST -> {
                        entity.updateEnergy(entityOnNewPos.getEnergy());
                        if(((BadBeast)entityOnNewPos).onBite()){
                            entityOnNewPos.updatePosition(genRandomPos());
                        }
                        return true;
                    }
                    case MINISQUIRREL -> {
                        if(((MiniSquirrel)entityOnNewPos).getParent() == entity){
                            entity.updateEnergy(entityOnNewPos.getEnergy());
                            entityOnNewPos.updateEnergy(-entityOnNewPos.getEnergy());
                        }
                        else{
                            ((MiniSquirrel)entityOnNewPos).kill();
                        }
                    }
                }
            }
            case MINISQUIRREL -> {
                switch(entityOnNewPos.getType()){
                    case WALL -> {
                        ((MiniSquirrel)entity).onWallCollision(entityOnNewPos.getEnergy());
                        return true;
                    }
                    case GOODPLANT, BADPLANT, GOODBEAST -> {
                        entity.updateEnergy(entityOnNewPos.getEnergy());
                        entityOnNewPos.updatePosition(genRandomPos());
                        return true;
                    }
                    case BADBEAST -> {
                        entity.updateEnergy(entityOnNewPos.getEnergy());
                        if(((BadBeast)entityOnNewPos).onBite()){
                            entityOnNewPos.updatePosition(genRandomPos());
                        }
                        return true;
                    }
                    case HANDOPERATEDMASTERSQUIRREL, MASTERSQUIRREL ->{
                        if(((MiniSquirrel)entity).getParent() == entityOnNewPos){
                            entityOnNewPos.updateEnergy(entity.getEnergy());
                            entity.updateEnergy(-entity.getEnergy());
                        }
                        else{
                            ((MiniSquirrel) entity).kill();
                        }
                        return true;
                    }
                    case MINISQUIRREL -> {
                        if(((MiniSquirrel)entityOnNewPos).getParent() != ((MiniSquirrel)entity).getParent()){
                            ((MiniSquirrel) entity).kill();
                            ((MiniSquirrel) entityOnNewPos).kill();
                        }
                        return true;
                    }
                }
            }
            case BADBEAST -> {
                switch(entityOnNewPos.getType()){
                    case WALL, GOODBEAST, BADBEAST, GOODPLANT, BADPLANT-> {
                        return true;
                    }
                    case HANDOPERATEDMASTERSQUIRREL,  MASTERSQUIRREL, MINISQUIRREL -> {
                        entityOnNewPos.updateEnergy(entity.getEnergy());
                        if(((BadBeast)entity).onBite()){
                            entity.updatePosition(genRandomPos());
                        }
                        return true;
                    }
                }
            }
            case GOODBEAST -> {
                switch(entityOnNewPos.getType()){
                    case WALL, GOODBEAST, BADBEAST, GOODPLANT, BADPLANT-> {
                        return true;
                    }
                    case HANDOPERATEDMASTERSQUIRREL,  MASTERSQUIRREL, MINISQUIRREL -> {
                        entityOnNewPos.updateEnergy(entity.getEnergy());
                        entity.updatePosition(genRandomPos());
                        return true;
                    }
                }
            }
        }
        return false;
    }
}