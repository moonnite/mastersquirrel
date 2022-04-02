public final class XY {

    public static final XY UP = new XY(0,-1);
    public static final XY DOWN = new XY(0,1);
    public static final XY LEFT = new XY(-1,0);
    public static final XY RIGHT = new XY(1,0);

    private final int xPos;
    private final int yPos;

    RandomDirection random = RandomDirection.getInstance();

    public XY(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public XY(){
        this.xPos = random.randomInt();
        this.yPos = random.randomInt();
    }

    public int getYPos() {
        return yPos;
    }

    public int getXPos() {
        return xPos;
    }

    public static XY add(XY p1, XY p2){
        return new XY(p1.getXPos()+p2.getXPos(),p1.getYPos()+p2.getYPos());
    }

    public XY genNewDir(){
        return random.getRandom();
    }

    @Override
    public String toString() {
        return "X: "+xPos+" Y: " + yPos;
    }
}
