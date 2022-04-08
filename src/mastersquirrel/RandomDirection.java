package mastersquirrel;

import java.util.Random;

public class RandomDirection {

    private static final RandomDirection randomDir;
    private static final XY[] dirArray = new XY[8];
    private final Random random = new Random();

    //private constructor -> can't be initialized from other classes
    private RandomDirection(){
        dirArray[0] = new XY(0,1);
        dirArray[1] = new XY(0,-1);
        dirArray[2] = new XY(1,0);
        dirArray[3] = new XY(-1,0);
        dirArray[4] = new XY(1,1);
        dirArray[5] = new XY(-1,-1);
        dirArray[6] = new XY(1,-1);
        dirArray[7] = new XY(-1,1);
    }

    //static initializer constructs the class instance at loading time
    static{
        randomDir = new RandomDirection();
    }

    public static RandomDirection getInstance(){
        return randomDir;
    }

    public XY getRandom(){
        return dirArray[random.nextInt(0,8)];
    }

    public XY getRandom(int xMin, int xMax, int yMin, int yMax){
        return new XY(random.nextInt(xMin,xMax),random.nextInt(yMin,yMax));
    }

    public int randomInt(){
        return random.nextInt(0,10);
    }

    public int randomInt(int origin, int bound){
        return random.nextInt(origin,bound);
    }
}