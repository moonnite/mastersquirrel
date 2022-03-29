public class Position {

    private final int xPos;
    private final int yPos;

    RandomDirection random = RandomDirection.getInstance();

    public Position(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Position(){
        this.xPos = random.randomInt();
        this.yPos = random.randomInt();
    }

    public int getYPos() {
        return yPos;
    }

    public int getXPos() {
        return xPos;
    }

    private Position addPosition(Position p1, Position p2){
        return new Position(p1.getXPos()+p2.getXPos(),p1.getYPos()+p2.getYPos());
    }

    public Position genNewDir(){
        return random.getRandom();
    }
}
