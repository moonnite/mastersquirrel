import java.util.Random;

public class RandomDirection {

    private static final RandomDirection randomDir;
    private static final Position[] dirArray = new Position[8];
    private Random random;

    //private constructor -> can't be initialized from other classes
    private RandomDirection(){
        dirArray[0] = new Position(0,1);
        dirArray[1] = new Position(0,-1);
        dirArray[2] = new Position(1,0);
        dirArray[3] = new Position(-1,0);
        dirArray[4] = new Position(1,1);
        dirArray[5] = new Position(-1,-1);
        dirArray[6] = new Position(1,-1);
        dirArray[7] = new Position(-1,1);
    }

    //static initializer constructs the class instance at loading time
    static{
        randomDir = new RandomDirection();
    }

    public static RandomDirection getInstance(){
        return randomDir;
    }

    public Position getRandom(){
        return dirArray[random.nextInt(0,7)];
    }

}