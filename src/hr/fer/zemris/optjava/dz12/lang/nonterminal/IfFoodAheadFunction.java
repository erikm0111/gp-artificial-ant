package hr.fer.zemris.optjava.dz12.lang.nonterminal;

/**
 * Created by ematosevic on 06.02.17..
 */
public class IfFoodAheadFunction extends NonTerminalSymbol {

    public IfFoodAheadFunction(){
        super();
        numArgs = 2;
    }

    @Override
    public void execute() {

    }

    @Override
    public String toString(){
        return "IF_FOOD_AHEAD";
    }
}
