package hr.fer.zemris.optjava.dz12.lang.terminal;

/**
 * Created by ematosevic on 06.02.17..
 */
public class MoveAction extends TerminalSymbol {

    public MoveAction(){
        super();
        numArgs = 0;
    }

    @Override
    public void execute() {

    }

    @Override
    public String toString(){
        return "MOVE";
    }
}
