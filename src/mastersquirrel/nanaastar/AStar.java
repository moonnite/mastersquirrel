package mastersquirrel.nanaastar;

import mastersquirrel.FlattenedBoard;
import mastersquirrel.XY;
import mastersquirrel.entities.EntityType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar {
    private static final int DIAGONAL_COST = 14; //sqrt of 2 * 10
    private static final int STRAIGHT_COST = 10; //1 * 10

    //cells of positions
    private Cell[][] grid;

    private ArrayList<Cell> cameFrom;

    //start and end cells
    private XY startPos;
    private XY endPos;

    public void run(FlattenedBoard flattenedBoard, XY startPos, XY endPos){
        grid = new Cell[flattenedBoard.getSize().getXLen()][flattenedBoard.getSize().getYLen()];

        //init cell grid
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length;j++){
                grid[i][j] = new Cell(new XY(i,j),getWalkable(flattenedBoard.getEntityType(i,j)));
            }
        }

        //create PriorityQueue (Heap) that is ordered based on the fCost
        PriorityQueue<Cell> openCells = new PriorityQueue<Cell>(Comparator.comparingInt((Cell c) -> c.fCost));

        //add starting Cell (to start the algorithm's while loop)
        grid[startPos.getXLen()][startPos.getYLen()].gCost = 0;
        grid[startPos.getXLen()][startPos.getYLen()].fCost = h(startPos,endPos);

        openCells.add(grid[startPos.getXLen()][startPos.getYLen()]);

        while(!openCells.isEmpty()){
            Cell current = openCells.remove();

            if(current.position.getXLen() == endPos.getXLen() && current.position.getYLen() == endPos.getYLen()){
                setPathfindingValues(current);
                return;
            }

            for (Cell neighbor:getNeighbors(current)) {
                int tempGCost;
                //Diagonal cost
                if(current.position.getXLen() != neighbor.position.getXLen() && current.position.getYLen() != neighbor.position.getYLen()){
                    tempGCost = current.gCost + DIAGONAL_COST;
                }
                //Straight cost
                else {
                    tempGCost = current.gCost + STRAIGHT_COST;
                }
                if(tempGCost < neighbor.gCost){
                    neighbor.cameFrom = current;
                    neighbor.gCost = tempGCost;
                    neighbor.fCost = tempGCost + h(neighbor.position,endPos);
                    if(!openCells.contains(neighbor)){
                        openCells.add(neighbor);
                    }
                }
            }
        }
    }

    private void setPathfindingValues(Cell current) {
        int count = 0;
        while(current.cameFrom.cameFrom != null){
            count++;
            current = current.cameFrom;
        }
        Pathfinding.setNextPos(current.position);
        Pathfinding.setShortestLength(count);
    }

    private ArrayList<Cell> getNeighbors(Cell current) {

        ArrayList<Cell> neighbors = new ArrayList<Cell>();

        int currentXPos = current.position.getXLen();
        int currentYPos = current.position.getYLen();

        //From Left-Up clockwise all neighbors
        if(grid[currentXPos-1][currentYPos-1].walkable) neighbors.add(grid[currentXPos-1][currentYPos-1]);
        if(grid[currentXPos][currentYPos-1].walkable) neighbors.add(grid[currentXPos][currentYPos-1]);
        if(grid[currentXPos+1][currentYPos-1].walkable) neighbors.add(grid[currentXPos+1][currentYPos-1]);
        if(grid[currentXPos+1][currentYPos].walkable) neighbors.add(grid[currentXPos+1][currentYPos]);
        if(grid[currentXPos+1][currentYPos+1].walkable) neighbors.add(grid[currentXPos+1][currentYPos+1]);
        if(grid[currentXPos][currentYPos+1].walkable) neighbors.add(grid[currentXPos][currentYPos+1]);
        if(grid[currentXPos-1][currentYPos+1].walkable) neighbors.add(grid[currentXPos-1][currentYPos+1]);
        if(grid[currentXPos-1][currentYPos].walkable) neighbors.add(grid[currentXPos-1][currentYPos]);

        return neighbors;
    }

    private boolean getWalkable(EntityType entityType) {
        if(entityType == null) return true;

        switch (entityType){
            case WALL,BADBEAST,BADPLANT,GOODPLANT,GOODBEAST -> {
                return false;
            }
            default -> {
                return true;
            }
        }
    }

    public int h(XY startPos, XY endPos){
        int xDif = Math.abs(startPos.getXLen()-endPos.getXLen());
        int yDif = Math.abs(startPos.getYLen()-endPos.getYLen());

        //return largest difference
        return Math.max(xDif, yDif);
    }

    private class Cell {

        private final XY position;

        private final boolean walkable;

        private Cell cameFrom;

        //final cost = gCost + hCost
        private int fCost = Integer.MAX_VALUE;
        //gCost is cost from start node to a node
        private int gCost = Integer.MAX_VALUE;

        private Cell(XY position, boolean walkable){
            this.position = position;
            this.walkable = walkable;
        }

        @Override
        public String toString() {
            return "[" + position.getXLen() + ", " + position.getYLen() + "]";
        }
    }
}
