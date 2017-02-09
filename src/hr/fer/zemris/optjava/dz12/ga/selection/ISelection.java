package hr.fer.zemris.optjava.dz12.ga.selection;

import hr.fer.zemris.optjava.dz12.solution.Node;

import java.util.List;

/**
 * Created by ematosevic on 09.02.17..
 */
public interface ISelection {
    Node[] selectParents(List<Node> population);
}