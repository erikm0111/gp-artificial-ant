package hr.fer.zemris.optjava.dz12.solution;

import hr.fer.zemris.optjava.dz12.lang.Symbol;

/**
 * Created by ematosevic on 06.02.17..
 */
public class Node {
    private int level;
    private Symbol symbol;
    private Node parentNode;
    private Node[] childrenNodes;

    public Node(Node parent, Symbol symbol){
        this.symbol = symbol;
        this.childrenNodes = new Node[symbol.numArgs()];
        if (parent == null){
            this.level = 0;
        }
        else{
            this.level = parent.getLevel() + 1;
        }
    }

    public Symbol getSymbol(){
        return symbol;
    }

    public Node getParentNode(){
        return parentNode;
    }

    public Node[] getChildrenNodes(){
        return childrenNodes;
    }

    public int getLevel(){
        return this.level;
    }

    public void addChildNode(Node node){
        for (int i = 0; i < childrenNodes.length; i++) {
            if (childrenNodes[i] == null){
                childrenNodes[i] = node;
            }
        }
    }

    @Override
    public String toString(){
        return symbol.toString();
    }

}
