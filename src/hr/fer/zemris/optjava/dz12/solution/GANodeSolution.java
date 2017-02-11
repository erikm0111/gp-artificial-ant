package hr.fer.zemris.optjava.dz12.solution;

/**
 * Created by ematosevic on 10.02.17..
 */
public class GANodeSolution implements Comparable<GANodeSolution>{
    private Node node;
    private double fitness;

    public GANodeSolution(Node node){
        this.node = node;
        this.fitness = 0;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }


    @Override
    public int compareTo(GANodeSolution gaNodeSolution) {
        return gaNodeSolution.getFitness() > this.getFitness() ? 1 : (gaNodeSolution.getFitness() < this.getFitness() ? -1 : 0 ) ;
    }
}
