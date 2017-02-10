package hr.fer.zemris.optjava.dz12;
import hr.fer.zemris.optjava.dz12.ga.crossover.ICrossover;
import hr.fer.zemris.optjava.dz12.ga.crossover.SubTreeReplacementCrossover;
import hr.fer.zemris.optjava.dz12.ga.mutation.GrowMutation;
import hr.fer.zemris.optjava.dz12.ga.mutation.IMutation;
import hr.fer.zemris.optjava.dz12.lang.Symbol;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.IfFoodAheadFunction;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.Prog2Function;
import hr.fer.zemris.optjava.dz12.solution.GANodeSolution;
import hr.fer.zemris.optjava.dz12.solution.Node;
import hr.fer.zemris.optjava.dz12.solution.TreeOperation;
import hr.fer.zemris.optjava.dz12.solution.Utils;

import java.io.IOException;

/**
 * Created by ematosevic on 06.02.17..
 */
public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
        Node first = tb.fullTree(3);
        System.out.println(tb.getTreeDepth(first));

        System.out.println();
        System.out.println();

    }
}