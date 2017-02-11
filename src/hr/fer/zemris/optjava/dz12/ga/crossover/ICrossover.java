package hr.fer.zemris.optjava.dz12.ga.crossover;

import hr.fer.zemris.optjava.dz12.solution.GANodeSolution;

import java.io.IOException;

/**
 * Created by ematosevic on 10.02.17..
 */
public interface ICrossover {
    /**
     * Crossover method that takes copies of two parents and creates children
     * @param first first parent
     * @param second second parent
     * @return true if crossover succeded, false otherwise
     * @throws IOException
     * @throws ClassNotFoundException
     */
    boolean crossover(GANodeSolution first, GANodeSolution second) throws IOException, ClassNotFoundException;
}
