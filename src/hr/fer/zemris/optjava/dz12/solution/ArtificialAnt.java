package hr.fer.zemris.optjava.dz12.solution;

/**
 * Created by ematosevic on 07.02.17..
 */
public class ArtificialAnt {
    private int x,y;
    private Direction direction;

    public ArtificialAnt(int x, int y, Direction direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public ArtificialAnt(ArtificialAnt ant) {
        this.x = ant.getX();
        this.y = ant.getY();
        this.direction = ant.getDirection();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString(){
        return "[" + x + ", " + y + "], " + direction;
    }
}
