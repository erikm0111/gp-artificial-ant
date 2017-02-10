package hr.fer.zemris.optjava.dz12.lang;

/**
 * Created by ematosevic on 06.02.17..
 */
public abstract class Symbol{
    protected int numArgs;

    public abstract void execute();


    public int numArgs() {
        return numArgs;
    }
}
