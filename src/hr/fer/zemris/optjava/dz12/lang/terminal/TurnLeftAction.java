package hr.fer.zemris.optjava.dz12.lang.terminal;

/**
 * Created by ematosevic on 06.02.17..
 */
public class TurnLeftAction extends TerminalSymbol {

    public TurnLeftAction(){
        super();
        numArgs = 0;
    }

    @Override
    public void execute() {

    }

    @Override
    public String toString(){
        return "TURN_LEFT";
    }
}
