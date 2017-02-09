package hr.fer.zemris.optjava.dz12.solution;

import hr.fer.zemris.optjava.dz12.lang.Symbol;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.IfFoodAheadFunction;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.Prog2Function;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.Prog3Function;
import hr.fer.zemris.optjava.dz12.lang.terminal.MoveAction;
import hr.fer.zemris.optjava.dz12.lang.terminal.TurnLeftAction;
import hr.fer.zemris.optjava.dz12.lang.terminal.TurnRightAction;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Arrays;

/**
 * Created by ematosevic on 07.02.17..
 */
public class Executor {
    private ArtificialAnt ant;
    private int[][] map;
    private int width, height;

    public Executor(ArtificialAnt ant, int[][] map, int width, int height){
        this.ant = ant;
        this.map = map;
        this.width = width;
        this.height = height;
    }

    public void executeTree(Node root){
        Symbol current = root.getSymbol();
        if (current instanceof IfFoodAheadFunction){
            boolean foodAhead = checkIfFoodAhead(ant, map);
            if (foodAhead){
                Node left = root.getChildrenNodes()[0];
                executeTree(left);
            }
            else{
                Node right = root.getChildrenNodes()[1];
                executeTree(right);
            }
        }
        else if(current instanceof Prog2Function){
            Node first = root.getChildrenNodes()[0];
            Node second = root.getChildrenNodes()[1];
            executeTree(first);
            executeTree(second);
        }
        else if(current instanceof Prog3Function){
            Node first = root.getChildrenNodes()[0];
            Node second = root.getChildrenNodes()[1];
            Node third = root.getChildrenNodes()[2];
            executeTree(first);
            executeTree(second);
            executeTree(third);
        }
        else if (current instanceof MoveAction){
            move(ant, map);
            System.out.println("MOV");
        }
        else if (current instanceof TurnLeftAction){
            turnLeft(ant, map);
            System.out.println("LEF");
        }
        else if (current instanceof TurnRightAction){
            turnRight(ant, map);
            System.out.println("RIG");
        }
    }

    private void turnRight(ArtificialAnt ant, int[][] map) {
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


    private void turnLeft(ArtificialAnt ant, int[][] map) {
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
