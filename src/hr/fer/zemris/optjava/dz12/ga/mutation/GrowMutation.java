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
    private int maxNodes;

    public GrowMutation(TreeOperation tb, int maxDepth, int maxNodes){
        this.tb = tb;
        this.maxDepth = maxDepth;
        this.maxNodes = maxNodes;
    }

    @Override
    public boolean mutate(GANodeSolution solution) {
        Node randomNode = tb.pickRandomNode(solution.getNode());
        int id = randomNode.id;
        Node parent = randomNode.getParentNode();
        if (parent!=null) {
            Node[] children = parent.getChildrenNodes();
            for (int i = 0; i < children.length; i++) {
                if (id == children[i].id) {
                    parent.getChildrenNodes()[i] = tb.growForMutation(maxDepth);
                    break;
                }
            }
        }
//        if (tb.getTreeDepth(solution.getNode()) > maxDepth || tb.countNodesInTree(solution.getNode()) > maxNodes){
//            System.out.println("IPAK");
//            return false;
//        }
        return true;
    }
}
