package hr.fer.zemris.optjava.dz12.solution;

import hr.fer.zemris.optjava.dz12.lang.Symbol;


/**
 * Created by ematosevic on 06.02.17..
 */
public class Node{
    public int id;
    private int level;
    private Symbol symbol;
    private Node parentNode;
    private Node[] childrenNodes;
    private int numChildren;

    public Node(Node parent, Symbol symbol, int id){
        this.id = id;
        this.symbol = symbol;
        this.numChildren = symbol.numArgs();
        this.childrenNodes = new Node[numChildren];
        this.parentNode = parent;
        if (parent == null){
            this.level = 0;
        }
        else{
            this.level = parent.getLevel() + 1;
        }
    }

    public int getNumChildren(){
        return numChildren;
    }

    public Symbol getSymbol(){
        return symbol;
    }

    public Node getParentNode(){
        return parentNode;
    }

    public void setParentNode(Node parent){
        this.parentNode = parent;
    }

    public Node[] getChildrenNodes(){
        return childrenNodes;
    }

    public int getLevel(){
        return this.level;
    }

    public void addChildNode(Node node){
        for (int i = 0; i < numChildren; i++) {
            if (childrenNodes[i] == null){
                childrenNodes[i] = node;
                break;
            }
        }
    }

    @Override
    public String toString(){
        return symbol.toString();
    }

}
