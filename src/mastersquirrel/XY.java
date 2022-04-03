package mastersquirrel;

public final class XY {

    public static final XY UP = new XY(0,-1);
    public static final XY DOWN = new XY(0,1);
    public static final XY LEFT = new XY(-1,0);
    public static final XY RIGHT = new XY(1,0);

    private final int xLen;
    private final int yLen;

    RandomDirection random = RandomDirection.getInstance();

    public XY(int xLen, int yLen) {
        this.xLen = xLen;
        this.yLen = yLen;
    }

    public XY(){
        this.xLen = random.randomInt();
        this.yLen = random.randomInt();
    }

    public int getYLen() {
        return yLen;
    }

    public int getXLen() {
        return xLen;
    }

    public static XY add(XY p1, XY p2){
        return new XY(p1.getXLen()+p2.getXLen(),p1.getYLen()+p2.getYLen());
    }

    public XY genNewDir(){
        return random.getRandom();
    }

    @Override
    public String toString() {
        return "X: "+ xLen +" Y: " + yLen;
    }
}