package utilities;

import java.text.DecimalFormat;

/**
 * This class represent expetion
 * @author Omer Cohen 208627299
 */

public class Mishap {
    private boolean fixable;
    private double reductionFactor;
    private int turnsToFix;


    /**
     *Constructor of the class, the constructor initialize point x and point y of the Point.
     * @param fixable (required).
     * @param turnsToFix (required).
     * @param reductionFactor (required).
     */
    public Mishap(boolean fixable, int turnsToFix, double reductionFactor){
        setFixable(fixable);
        setTurnsToFix(turnsToFix);
        setReductionFactor(reductionFactor);
    }

    /**
     *Function that turn the mishap parameters into a string.
     * Return string with all the mishap parameters.
     */
    public String toString(){
        return "("+getFixable()+","+getTurnsToFix()+","+new DecimalFormat("0.00").format(getReductionFactor())+")";
    }

    /**
     * Checking set Fixable is a legal
     * @param fixable (required).
     * @return <tt>true</tt> if the Fixable is a legal else return false.
     */
    public boolean setFixable(boolean fixable) {
        this.fixable = fixable;
        return true;
    }

    /**
     * Checking set reductionFactor is a legal
     * @param reductionFactor (required)
     * @return <tt>true</tt> if the reductionFactor is a legal else return false.
     */
    public boolean setReductionFactor(double reductionFactor) {
        this.reductionFactor = reductionFactor;
        return true;
    }

    /**
     * Checking set setTurnsToFix is a legal
     * @param turnsToFix (required)
     * @return <tt>true</tt> if the setTurnsToFix is a legal else return false.
     */
    public boolean setTurnsToFix(int turnsToFix) {
        this.turnsToFix = turnsToFix;
        return true;
    }
    /** @return fixable */
    public boolean getFixable()
    {
        return fixable;
    }

    /** @return reductionFactor */
    public double getReductionFactor() {
        return reductionFactor;
    }

    /** @return turnsToFix */
    public int getTurnsToFix() {
        return turnsToFix;
    }

    /** decrease turnsToFix by 1 if possible to fix */
    public void nextTurn(){
        if(getFixable() && getTurnsToFix() > 0){
            setTurnsToFix(getTurnsToFix()-1);
        }
    }


}
