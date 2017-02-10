package hr.fer.zemris.optjava.dz12;
import hr.fer.zemris.optjava.dz12.lang.Symbol;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.IfFoodAheadFunction;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.Prog2Function;
import hr.fer.zemris.optjava.dz12.solution.Node;
import hr.fer.zemris.optjava.dz12.solution.TreeOperation;

/**
 * Created by ematosevic on 06.02.17..
 */
public class Test {
    public static void main(String[] args) {
//        Node root = new Node(null, randomSymbol());
//        System.out.println("Parent node: " + root.getParentNode());
//        System.out.println("Current node symbol: " + root.getSymbol());
//        System.out.println("Current node level: " + root.getLevel());
//        System.out.println("Children nodes: " + Arrays.toString(root.getChildrenNodes()));
//
//        System.out.println();
//        System.out.println("After adding children");
//        root.addChildNode(new Node(root, reallyRandomSymbol()));
//        root.addChildNode(new Node(root, reallyRandomSymbol()));
//        root.addChildNode(new Node(root, reallyRandomSymbol()));
//
//        System.out.println("Children nodes: " + Arrays.toString(root.getChildrenNodes()));
//
//        System.out.println("Level of children: " + root.getChildrenNodes()[0].getLevel());

        TreeOperation tb = new TreeOperation();
        Node full = tb.fullTree(3);
        Node randomNode = tb.pickRandomNode(full);
        System.out.println();
        System.out.println();

    }
}