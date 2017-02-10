package hr.fer.zemris.optjava.dz12.ga.crossover;

import hr.fer.zemris.optjava.dz12.solution.GANodeSolution;
import hr.fer.zemris.optjava.dz12.solution.Node;
import hr.fer.zemris.optjava.dz12.solution.TreeOperation;
import hr.fer.zemris.optjava.dz12.solution.Utils;

import java.io.IOException;

/**
 * Created by ematosevic on 10.02.17..
 */
public class SubTreeReplacementCrossover implements ICrossover{
    private TreeOperation tb;
    private int maxDepth;
    private int maxNodes;
    private Utils utils;

    public SubTreeReplacementCrossover(TreeOperation tb, int maxDepth, int maxNodes, Utils utils){
        this.tb = tb;
        this.maxDepth = maxDepth;
        this.maxNodes = maxNodes;
        this.utils = utils;
    }

    @Override
    public boolean crossover(GANodeSolution first, GANodeSolution second) throws IOException, ClassNotFoundException {

        Node firstRandom = tb.pickRandomNode(first.getNode());
        int firstId = firstRandom.id;
        Node secondRandom = tb.pickRandomNode(second.getNode());
        int secondId = secondRandom.id;

        Node firstTemp = utils.copy(firstRandom);
        Node secondTemp = utils.copy(secondRandom);

        Node firstParent = firstRandom.getParentNode();
        if (firstParent != null) {
            Node[] children1 = firstParent.getChildrenNodes();
            for (int i = 0; i < children1.length; i++) {
                if (firstId == children1[i].id) {
                    firstParent.getChildrenNodes()[i] = secondTemp;
                    break;
                }
            }
        }
        if (tb.getTreeDepth(first.getNode()) > maxDepth || tb.countNodesInTree(first.getNode()) > maxNodes){
            return false;
        }


        Node secondParent  = secondRandom.getParentNode();
        if (secondParent != null) {
            Node[] children2 = secondParent.getChildrenNodes();
            for (int i = 0; i < children2.length; i++) {
                if (secondId == children2[i].id) {
                    secondParent.getChildrenNodes()[i] = firstTemp;
                    break;
                }
            }
        }
        if (tb.getTreeDepth(second.getNode()) > maxDepth || tb.countNodesInTree(second.getNode()) > maxNodes){
            return false;
        }

        return true;
    }
}
