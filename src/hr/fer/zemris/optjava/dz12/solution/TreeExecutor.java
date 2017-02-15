package hr.fer.zemris.optjava.dz12.solution;

import hr.fer.zemris.optjava.dz12.lang.Symbol;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.IfFoodAheadFunction;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.Prog2Function;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.Prog3Function;
import hr.fer.zemris.optjava.dz12.lang.terminal.MoveAction;
import hr.fer.zemris.optjava.dz12.lang.terminal.TurnLeftAction;
import hr.fer.zemris.optjava.dz12.lang.terminal.TurnRightAction;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ematosevic on 07.02.17..
 */
public class TreeExecutor {
    private static final int MAX_ACTIONS = 600;
    private Queue<String> actionsToExecute = new LinkedList<>();
    private int width, height;

    public TreeExecutor(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int evaluate(Node root, int[][] map, ArtificialAnt ant){
        actionsToExecute = new LinkedList<>();
        int numActions = 0;
        while (numActions < MAX_ACTIONS) {
            if (isThereMoreActions()) {
                nextAction(ant, map);
                numActions++;
            } else {
                executeTree(root, map, ant);
                nextAction(ant, map);
                numActions++;
            }
        }
        return ant.getScore();
    }

    public void executeTree(Node root, int[][] mapOrig, ArtificialAnt antOrig){
        ArtificialAnt ant = new ArtificialAnt(antOrig);
        int[][] map = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = mapOrig[i][j];
            }
        }

        Symbol current = root.getSymbol();
        if (current instanceof IfFoodAheadFunction){
            boolean foodAhead = checkIfFoodAhead(ant, map);
            if (foodAhead){
                Node left = root.getChildrenNodes()[0];
                executeTree(left, map, ant);
            }
            else{
                Node right = root.getChildrenNodes()[1];
                executeTree(right, map ,ant);
            }
        }
        else if(current instanceof Prog2Function){
            Node first = root.getChildrenNodes()[0];
            Node second = root.getChildrenNodes()[1];
            executeTree(first, map, ant);
            executeTree(second, map, ant);
        }
        else if(current instanceof Prog3Function){
            Node first = root.getChildrenNodes()[0];
            Node second = root.getChildrenNodes()[1];
            Node third = root.getChildrenNodes()[2];
            executeTree(first, map, ant);
            executeTree(second, map, ant);
            executeTree(third, map, ant);
        }
        else if (current instanceof MoveAction){
            move(ant, map);
            actionsToExecute.add("MOV");
            //System.out.println("MOV");
        }
        else if (current instanceof TurnLeftAction){
            turnLeft(ant);
            actionsToExecute.add("LEF");
            //System.out.println("LEF");
        }
        else if (current instanceof TurnRightAction){
            turnRight(ant);
            actionsToExecute.add("RIG");
            //System.out.println("RIG");
        }
    }

    public boolean isThereMoreActions(){
        return !actionsToExecute.isEmpty();
    }

    public void nextAction(ArtificialAnt ant, int[][] map){
        if (isThereMoreActions()) {
            String action = actionsToExecute.poll();
            switch (action) {
                case "MOV":
                    move(ant, map);
                    break;
                case "LEF":
                    turnLeft(ant);
                    break;
                case "RIG":
                    turnRight(ant);
                    break;
            }
        }
    }

    private void turnRight(ArtificialAnt ant) {
        Direction antDirection = ant.getDirection();
        switch (antDirection){
            case NORTH:
                ant.setDirection(Direction.EAST);
                break;
            case WEST:
                ant.setDirection(Direction.NORTH);
                break;
            case SOUTH:
                ant.setDirection(Direction.WEST);
                break;
            case EAST:
                ant.setDirection(Direction.SOUTH);
                break;
        }
    }


    private void turnLeft(ArtificialAnt ant) {
        Direction antDirection = ant.getDirection();
        switch (antDirection){
            case NORTH:
                ant.setDirection(Direction.WEST);
                break;
            case WEST:
                ant.setDirection(Direction.SOUTH);
                break;
            case SOUTH:
                ant.setDirection(Direction.EAST);
                break;
            case EAST:
                ant.setDirection(Direction.NORTH);
                break;
        }
    }

    private void move(ArtificialAnt ant, int[][] map) {
        int x = ant.getX();
        int y = ant.getY();
        Direction antDirection = ant.getDirection();
        switch (antDirection){
            case NORTH:
                if (x-1 < 0){
                    ant.setX(height-1);
                }
                else {
                    ant.setX(x-1);
                }
                break;
            case EAST:
                if (y+1 > width -1){
                    ant.setY(0);
                }
                else {
                   ant.setY(y+1);
                }
                break;
            case SOUTH:
                if (x+1 > height - 1){
                    ant.setX(0);
                }
                else{
                    ant.setX(x+1);
                }
                break;
            case WEST:
                if (y-1 < 0){
                    ant.setY(width-1);
                }
                else{
                    ant.setY(y-1);
                }
                break;
        }
        int val = map[ant.getX()][ant.getY()];
        if (val == 1){
            ant.incrementScore();
        }
        map[ant.getX()][ant.getY()] = -1;
    }

    private boolean checkIfFoodAhead(ArtificialAnt ant, int[][] map) {
        int x = ant.getX();
        int y = ant.getY();
        Direction direction = ant.getDirection();
        switch (direction){
            case NORTH:
                if (x-1 < 0){
                    return map[height-1][y] == 1;
                }
                else {
                    return map[x-1][y] == 1;
                }
            case EAST:
                if (y+1 > width-1){
                    return map[x][0] == 1;
                }
                else {
                    return map[x][y + 1] == 1;
                }
            case SOUTH:
                if (x+1 > height - 1){
                    return map[0][y] == 1;
                }
                else {
                    return map[x + 1][y] == 1;
                }
            case WEST:
                if (y-1 < 0){
                    return map[x][width - 1] == 1;
                }
                else {
                    return map[x][y - 1] == 1;
                }
        }
        return false;
    }
}
