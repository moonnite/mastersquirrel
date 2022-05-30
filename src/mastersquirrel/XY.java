package mastersquirrel;

public final class XY {

    public static final XY UP = new XY(0,-1);
    public static final XY DOWN = new XY(0,1);
    public static final XY LEFT = new XY(-1,0);
    public static final XY RIGHT = new XY(1,0);
    public static final XY RIGHT_UP = new XY(1,-1);
    public static final XY RIGHT_DOWN = new XY(1,1);
    public static final XY LEFT_DOWN = new XY(-1,1);
    public static final XY LEFT_UP = new XY(-1,-1);
    public static final XY ZERO_ZERO = new XY(0,0);

    private final int xLen;
    private final int yLen;

    RandomDirection random = RandomDirection.getInstance();

    public XY(int xLen, int yLen) {
        this.xLen = xLen;
        this.yLen = yLen;
    }

    public int getY() {
        return yLen;
    }

    public int getX() {
        return xLen;
    }

    public XY plus(XY xy){
        return new XY(xLen+xy.getX(),yLen+xy.getY());
    }

    public XY minus(XY xy){
        return new XY(xLen-xy.getX(),yLen-xy.getY());
    }

    public XY times(int factor){
        return new XY(xLen*factor,yLen*factor);
    }

    public double length(){
        return (Math.sqrt(xLen*xLen+yLen*yLen));
    }

    public double distanceFrom(XY xy){
        return Math.sqrt((xLen-xy.xLen)*(xLen-xy.xLen)+(yLen-xy.yLen)*(yLen-xy.yLen));
    }

    public int hashCode(){
        return 0;
        //TODO
    }

    public boolean equals(Object obj){
        if(obj.getClass() == this.getClass()){
            return ((XY) obj).getX() == this.getX() && ((XY) obj).getY() == this.getY();
        }
        return false;
    }

    public static XY add(XY p1, XY p2){
        return new XY(p1.getX()+p2.getX(),p1.getY()+p2.getY());
    }

    public XY genNewDir(){
        return random.getRandom();
    }

    @Override
    public String toString() {
        return "X: "+ xLen +" Y: " + yLen;
    }
}