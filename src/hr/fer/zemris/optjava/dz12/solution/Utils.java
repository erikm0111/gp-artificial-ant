package hr.fer.zemris.optjava.dz12.solution;

import java.io.*;

/**
 * Created by ematosevic on 10.02.17..
 */
public class Utils {
    public Utils() throws IOException {

    }

    public Node copy(Node orig){
        Node copy = new Node(null, orig.getSymbol(), orig.id);
        copyTree(copy, orig);
        return copy;
    }

    private void copyTree(Node copyNode, Node origNode) {
        Node[] childrenOrig = origNode.getChildrenNodes();
        for (int i = 0; i < childrenOrig.length; i++) {
            copyNode.addChildNode(new Node(copyNode, origNode.getChildrenNodes()[i].getSymbol(), origNode.getChildrenNodes()[i].id));
            copyTree(copyNode.getChildrenNodes()[i], origNode.getChildrenNodes()[i]);
        }
    }
}
