package hr.fer.zemris.optjava.dz12.ga.mutation;

import hr.fer.zemris.optjava.dz12.solution.GANodeSolution;

/**
 * Created by ematosevic on 10.02.17..
 */
public interface IMutation {
    /**
     * Mutation method
     * @param solution solution that has to be mutated
     * @return true if mutation succeded, false otherwise
     */
    boolean mutate(GANodeSolution solution);
}
