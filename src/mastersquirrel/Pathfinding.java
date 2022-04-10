package mastersquirrel;

import mastersquirrel.entities.Squirrel;
import java.util.ArrayList;

public class Pathfinding {

    public static ArrayList<Squirrel> squirrelArrayList = new ArrayList<>();

    //returns all squirrels in range of given radius
    public static ArrayList<Squirrel> squirrelsInRange(XY startPos, int radius){
        ArrayList<Squirrel> squirrelsInRange = new ArrayList<>();
        for (Squirrel s : squirrelArrayList) {
            int xDif = Math.abs(startPos.getXLen()-s.getPosition().getXLen());
            int yDif = Math.abs(startPos.getYLen()-s.getPosition().getYLen());
            //check if differences within radius and not same pos as startPos (squirrel shouldn't find itself)
            if (xDif <= radius && yDif <= radius && xDif + yDif != 0){
                squirrelsInRange.add(s);
            }
        }
        return squirrelsInRange;
    }

    public static void addToSquirrelList(Squirrel squirrel){
        squirrelArrayList.add(squirrel);
        System.out.println(squirrelArrayList.toString());
    }

    public static void removeFromSquirrelList(Squirrel squirrel){
        squirrelArrayList.remove(squirrel);
        System.out.println(squirrelArrayList.toString());
    }

    //find Path and check if in radius else return null
    public static XY findPath(XY startPos, int radius){
        ArrayList<Squirrel> squirrelsInRange = squirrelsInRange(startPos, radius);
        return null;
    }
}