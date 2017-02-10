package hr.fer.zemris.optjava.dz12.ga.evaluation;

import hr.fer.zemris.optjava.dz12.solution.ArtificialAnt;
import hr.fer.zemris.optjava.dz12.solution.Node;
import hr.fer.zemris.optjava.dz12.solution.TreeExecutor;

/**
 * Created by ematosevic on 10.02.17..
 */
public class Evaluator {
    private ArtificialAnt ant;
    private int width, height;
    private TreeExecutor executor;

    public Evaluator(ArtificialAnt ant, int[][] map, int width, int height){
        this.ant = ant;
        this.width = width;
        this.height = height;
        this.executor = new TreeExecutor(width, height);
    }

//    public int evaluate(Node root){
//
//    }
}
