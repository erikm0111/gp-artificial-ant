package hr.fer.zemris.optjava.dz12.solution;

import hr.fer.zemris.optjava.dz12.lang.Symbol;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.IfFoodAheadFunction;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.NonTerminalSymbol;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.Prog2Function;
import hr.fer.zemris.optjava.dz12.lang.nonterminal.Prog3Function;
import hr.fer.zemris.optjava.dz12.lang.terminal.MoveAction;
import hr.fer.zemris.optjava.dz12.lang.terminal.TerminalSymbol;
import hr.fer.zemris.optjava.dz12.lang.terminal.TurnLeftAction;
import hr.fer.zemris.optjava.dz12.lang.terminal.TurnRightAction;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by ematosevic on 06.02.17..
 */
public class TreeBuilder {
    private NonTerminalSymbol[] nonterminals = new NonTerminalSymbol[]{new IfFoodAheadFunction(), new Prog2Function(), new Prog3Function()};
    private TerminalSymbol[] terminals = new TerminalSymbol[]{new MoveAction(), new TurnLeftAction(), new TurnRightAction()};
    private Symbol[] allSymbols = new Symbol[]{new IfFoodAheadFunction(), new Prog2Function(), new Prog3Function(),
            new MoveAction(), new TurnLeftAction(), new TurnRightAction()};
    private Random rand;

    public TreeBuilder(){
        this.rand = new Random();
    }

    public Node createRandomTree(int maxDepth){
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(null, randomNonTerminalSymbol());
        queue.add(root);
        for (int i = 0; i < root.getNumChildren(); i++) {
            Node childNode = new Node(root, randomSymbol());
            root.addChildNode(childNode);
            queue.add(childNode);
        }

        while (true) {
            if (queue.isEmpty()){
                break;
            }
            Node current = queue.poll();
            if (current.getLevel() < maxDepth - 2){
                int numChildren = current.getNumChildren();
                for (int i = 0; i < numChildren; i++) {
                    Node childNode = new Node(current, randomSymbol());
                    current.addChildNode(childNode);
                    queue.add(childNode);
                }
            }else if (current.getLevel() == maxDepth - 2){
                int numChildren = current.getNumChildren();
                for (int i = 0; i < numChildren; i++) {
                    Node childNode = new Node(current, randomTerminalSymbol());
                    current.addChildNode(childNode);
                }
            }
        }
        return root;
    }

    public Symbol randomNonTerminalSymbol(){
        int bound = nonterminals.length;
        return nonterminals[rand.nextInt(bound)];
    }

    public Symbol randomTerminalSymbol(){
        int bound = terminals.length;
        return terminals[rand.nextInt(bound)];
    }

    public Symbol randomSymbol(){
        int bound = allSymbols.length;
        return allSymbols[rand.nextInt(bound)];
    }
}
