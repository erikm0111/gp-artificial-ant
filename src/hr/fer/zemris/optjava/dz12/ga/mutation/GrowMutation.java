package hr.fer.zemris.optjava.dz12.ga.mutation;

import hr.fer.zemris.optjava.dz12.solution.GANodeSolution;
import hr.fer.zemris.optjava.dz12.solution.Node;
import hr.fer.zemris.optjava.dz12.solution.TreeOperation;

/**
 * Created by ematosevic on 10.02.17..
 */
public class GrowMutation implements IMutation{
    private TreeOperation tb;
    private int maxDepth;

    public GrowMutation(TreeOperation tb, int maxDepth){
        this.tb = tb;
        this.maxDepth = maxDepth;
    }

    @Override
    public GANodeSolution mutate(GANodeSolution solution) {
        Node randomNode = tb.pickRandomNode(solution.getNode());
        randomNode = tb.growTree(maxDepth);
        return solution;
    }
}
