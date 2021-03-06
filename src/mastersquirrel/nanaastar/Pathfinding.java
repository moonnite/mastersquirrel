package mastersquirrel.nanaastar;

import mastersquirrel.FlattenedBoard;
import mastersquirrel.XY;
import mastersquirrel.entities.Squirrel;
import java.util.ArrayList;

public class Pathfinding {

    private static final ArrayList<Squirrel> squirrelArrayList = new ArrayList<>();
    private static int shortestLength;
    private static XY nextPos;

    //returns all squirrels in range of given radius
    public static ArrayList<Squirrel> squirrelsInRange(XY startPos, int radius){
        ArrayList<Squirrel> squirrelsInRange = new ArrayList<>();
        for (Squirrel s : squirrelArrayList) {
            int xDif = Math.abs(startPos.getX()-s.getPosition().getX());
            int yDif = Math.abs(startPos.getY()-s.getPosition().getY());
            //check if differences within radius and not same pos as startPos (squirrel shouldn't find itself)
            if (xDif <= radius && yDif <= radius && (xDif + yDif != 0)){
                squirrelsInRange.add(s);
            }
        }
        return squirrelsInRange;
    }

    public static void addToSquirrelList(Squirrel squirrel){
        squirrelArrayList.add(squirrel);
    }

    public static void removeFromSquirrelList(Squirrel squirrel){
        squirrelArrayList.remove(squirrel);
    }

    //find Path and check if in radius return direction else return null
    public static XY findPath(XY startPos, int radius, FlattenedBoard flattenedBoard){
        //get all relevant (within radius) squirrels for pathfinding

        ArrayList<Squirrel> squirrelsInRange = squirrelsInRange(startPos, radius);
        shortestLength = Integer.MAX_VALUE;
        nextPos = null;

        AStar aStar = new AStar();
        //do A* pathfinding algorithm for all squirrels
        for(Squirrel s : squirrelsInRange){
            int tempLen = shortestLength;
            XY tempPos = nextPos;
            //Sets shortestLength and nextPos
            aStar.run(flattenedBoard, startPos, s.getPosition());
            if(tempLen<shortestLength){
                shortestLength = tempLen;
                nextPos = tempPos;
            }
        }

        //once all pathfindings are done, check if shortest Path is within radius (moves)
        //true --> return new Direction facing towards target position
        if(shortestLength < radius){
            if(nextPos != null) {
                //System.out.println(nextPos);
                //System.out.println(startPos);
                return new XY(nextPos.getX() - startPos.getX(), nextPos.getY() - startPos.getY());
            }
        }
        return null;
    }

    public static void setShortestLength(int length){
        shortestLength = length;
    }

    public static int getShortestLength(){
        return shortestLength;
    }

    public static void setNextPos(XY pos){
        nextPos = pos;
    }

    public static XY getNextPos(){
        return nextPos;
    }

    public static ArrayList<Squirrel> getSquirrelArrayList() {
        return squirrelArrayList;
    }

    public static void clearSquirrelList() {
        squirrelArrayList.clear();
    }
}