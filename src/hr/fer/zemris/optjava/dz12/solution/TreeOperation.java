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
public class TreeOperation {
    private NonTerminalSymbol[] nonterminals = new NonTerminalSymbol[]{new IfFoodAheadFunction(), new Prog2Function(), new Prog3Function()};
    private TerminalSymbol[] terminals = new TerminalSymbol[]{new MoveAction(), new TurnLeftAction(), new TurnRightAction()};
    private Symbol[] allSymbols = new Symbol[]{new IfFoodAheadFunction(), new Prog2Function(), new Prog3Function(),
            new MoveAction(), new TurnLeftAction(), new TurnRightAction()};
    private Random rand;

    public TreeOperation(){
        this.rand = new Random();
    }

    public int countNodesInTree(Node root){
        Node[] children = root.getChildrenNodes();
        int c = 1;
        for (int i = 0; i < children.length; i++) {
            if (children[i] != null) c += countNodesInTree(children[i]);
        }
        return c;
    }

    public int getTreeDepth(Node root){
        int deepest = 0;
        Node[] children = root.getChildrenNodes();
        for (int i=0; i < children.length; ++i){
            deepest = Math.max(deepest, getTreeDepth(children[i]));
        }
        return deepest + 1;
    }

    public Node pickRandomNode(Node root){
        int numNodes = countNodesInTree(root);
        int nodeId = rand.nextInt(numNodes - 1);
        Node node = getNode(root, nodeId);
        return node;
    }

    private Node getNode(Node root, int nodeId) {
        int i = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(i == nodeId){
                return node;
            }
            ++i;
            Node[] children = node.getChildrenNodes();
            for (int j = 0; j < children.length; j++) {
                queue.add(children[j]);
            }
        }
        return null;
    }

    public Node growForMutation(int maxDepth){
        int id = 0;
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(null, randomSymbol(), id);
        queue.add(root);
        ++id;
        for (int i = 0; i < root.getNumChildren(); i++) {
            Node childNode = new Node(root, (maxDepth - 2 == 0) ? randomTerminalSymbol() : randomSymbol(), id);
            root.addChildNode(childNode);
            queue.add(childNode);
            ++id;
        }

        loopGrowTree(queue, id, maxDepth);

        return root;
    }

    /**
     * Method that creates tree with inner nodes consisting of terminal and non-terminal symbols.
     * @param maxDepth maximum depth of tree
     * @return root node of tree
     */
    public Node growTree(int maxDepth){
        int id = 0;
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(null, randomNonTerminalSymbol(), id);
        queue.add(root);
        ++id;
        for (int i = 0; i < root.getNumChildren(); i++) {
            Node childNode = new Node(root, (maxDepth - 2 == 0) ? randomTerminalSymbol() : randomSymbol(), id);
            root.addChildNode(childNode);
            queue.add(childNode);
            ++id;
        }

        loopGrowTree(queue, id, maxDepth);

        return root;
    }

    private void loopGrowTree(Queue<Node> queue, int id, int maxDepth) {
        while (true) {
            if (queue.isEmpty()) {
                break;
            }
            Node current = queue.poll();
            if (current.getLevel() < maxDepth - 2) {
                int numChildren = current.getNumChildren();
                for (int i = 0; i < numChildren; i++) {
                    Node childNode = new Node(current, randomSymbol(), id);
                    current.addChildNode(childNode);
                    queue.add(childNode);
                    ++id;
                }
            } else if (current.getLevel() == maxDepth - 2) {
                int numChildren = current.getNumChildren();
                for (int i = 0; i < numChildren; i++) {
                    Node childNode = new Node(current, randomTerminalSymbol(), id);
                    current.addChildNode(childNode);
                    ++id;
                }
            }
        }
    }

    /**
     * Method that creates tree with inner nodes consisting only of non-terminal symbols.
     * @param maxDepth maximum depth of tree
     * @return root node of tree
     */
    public Node fullTree(int maxDepth){
        int id = 0;
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(null, randomNonTerminalSymbol(), id);
        queue.add(root);
        ++id;
        for (int i = 0; i < root.getNumChildren(); i++) {
            Node childNode = new Node(root, (maxDepth - 2 == 0) ? randomTerminalSymbol() : randomNonTerminalSymbol(), id);
            root.addChildNode(childNode);
            queue.add(childNode);
            ++id;
        }

        while (true) {
            if (queue.isEmpty()){
                break;
            }
            Node current = queue.poll();
            if (current.getLevel() < maxDepth - 2){
                int numChildren = current.getNumChildren();
                for (int i = 0; i < numChildren; i++) {
                    Node childNode = new Node(current, randomNonTerminalSymbol(), id);
                    current.addChildNode(childNode);
                    queue.add(childNode);
                    ++id;
                }
            }else if (current.getLevel() == maxDepth - 2){
                int numChildren = current.getNumChildren();
                for (int i = 0; i < numChildren; i++) {
                    Node childNode = new Node(current, randomTerminalSymbol(), id);
                    current.addChildNode(childNode);
                    ++id;
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
