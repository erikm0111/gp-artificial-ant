package hr.fer.zemris.optjava.dz12.ga.selection;

import hr.fer.zemris.optjava.dz12.solution.GANodeSolution;
import hr.fer.zemris.optjava.dz12.solution.Node;

import java.util.List;

/**
 * Created by ematosevic on 09.02.17..
 */
public interface ISelection {
    GANodeSolution selectParent(List<GANodeSolution> population);
}