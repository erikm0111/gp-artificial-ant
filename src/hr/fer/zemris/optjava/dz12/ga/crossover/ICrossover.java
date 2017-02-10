package hr.fer.zemris.optjava.dz12.ga.crossover;

import hr.fer.zemris.optjava.dz12.solution.GANodeSolution;

import java.io.IOException;

/**
 * Created by ematosevic on 10.02.17..
 */
public interface ICrossover {
    boolean crossover(GANodeSolution first, GANodeSolution second) throws IOException, ClassNotFoundException;
}
