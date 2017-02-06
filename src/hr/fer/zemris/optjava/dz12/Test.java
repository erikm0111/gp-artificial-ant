package hr.fer.zemris.optjava.dz12;

import hr.fer.zemris.optjava.dz12.lang.nonterminal.NonTerminalSymbol;
import hr.fer.zemris.optjava.dz12.lang.Symbol;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.Prog2Function;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.Prog3Function;
import hr.fer.zemris.optjava.dz12.lang.terminal.MoveAction;
import hr.fer.zemris.optjava.dz12.solution.Node;

import java.util.Arrays;

/**
 * Created by ematosevic on 06.02.17..
 */
public class Test {
    public static void main(String[] args){
        Node root = new Node(null, randomSymbol());
        System.out.println("Parent node: " + root.getParentNode());
        System.out.println("Current node symbol: " + root.getSymbol());
        System.out.println("Current node level: " + root.getLevel());
        System.out.println("Children nodes: " + Arrays.toString(root.getChildrenNodes()));
    }

    private static Symbol randomSymbol() {
        Symbol symbol = new MoveAction();
        return symbol;
    }
}