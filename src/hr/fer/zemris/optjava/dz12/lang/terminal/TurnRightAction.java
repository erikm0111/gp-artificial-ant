package hr.fer.zemris.optjava.dz12.lang.terminal;

/**
 * Created by ematosevic on 06.02.17..
 */
public class TurnRightAction extends TerminalSymbol {

    public TurnRightAction(){
        super();
        numArgs = 0;
    }

    @Override
    public String toString(){
        return "turn_right";
    }

}
