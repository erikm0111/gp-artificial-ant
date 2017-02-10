package hr.fer.zemris.optjava.dz12.ga.mutation;

import hr.fer.zemris.optjava.dz12.solution.GANodeSolution;

/**
 * Created by ematosevic on 10.02.17..
 */
public interface IMutation {
    GANodeSolution mutate(GANodeSolution solution);
}
