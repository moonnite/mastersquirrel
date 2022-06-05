package mastersquirrel;

import mastersquirrel.entities.AEntity;
import mastersquirrel.entities.EntityType;
import mastersquirrel.entities.bots.MasterSquirrelBot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class State {

    private final Board board;
    private static int highscore = 0;
    private static long remainingSteps = 0;
    private static boolean botState = false;
    private static final HashMap<String, ArrayList<Integer>> botScoreMap = new HashMap<String, ArrayList<Integer>>();

    public State(Board board){
        this.board = board;
    }

    public static HashMap<String, ArrayList<Integer>> getBotScoreMap() {
        return botScoreMap;
    }

    public void update() {

    }

    public static long getRemainingSteps() {
        return remainingSteps;
    }

    public static boolean getBotState(){
        return botState;
    }

    // returns true if 0 has been reached
    public static boolean decrementRemainingSteps(){
        if (remainingSteps - 1 >= 0) {
            remainingSteps--;
            return false;
        }
        AEntity[] aEntities = (AEntity[])EntitySet.getInstance().toArray();
        for(AEntity e : aEntities){
            if(e.getType() == EntityType.MASTERSQUIRREL){
                try{
                    MasterSquirrelBot masterSquirrelBot = (MasterSquirrelBot)e;
                    ArrayList<Integer> tempList;
                    if(botScoreMap.containsKey(masterSquirrelBot.getName())){
                        tempList = botScoreMap.get(masterSquirrelBot.getName());
                    }
                    else{
                        tempList = new ArrayList<Integer>();
                    }
                    tempList.add(masterSquirrelBot.getEnergy());
                    Collections.sort(tempList);
                    Collections.reverse(tempList);
                    botScoreMap.put(masterSquirrelBot.getName(),tempList);
                }
                catch(Exception exception){
                    System.out.println(exception);
                }
            }
        }
        Log.log(botScoreMap.toString());
        Log.log("---------------------------Round finished---------------------------");
        return true;
    }

    public static void setRemainingSteps(long steps){
        remainingSteps = steps;
    }

    public static void setBotState(boolean state){
        botState = state;
    }

    public Board getBoard() {
        return board;
    }

    public FlattenedBoard flattenBoard(){return board.flatten();}

    public int getHighscore() {
        return State.highscore;
    }

    public void setHighscore(int highscore) {
        State.highscore = highscore;
    }
}