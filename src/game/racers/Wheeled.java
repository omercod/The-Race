package game.racers;

/**
 * This class represent the wheeled of the vehicles
 * @author Omer Cohen 208627299
 */

public class Wheeled {
    //fields
    private int numOfWheels;

    /**
     *default Constructor of the class, the constructor initialize the Wheeled
     */
    public Wheeled() {
        setNumOfWheels(3);
    }

    /**
     *Constructor of the class, the constructor initialize numOfWheels.
     * @param numOfWheels (required).
     */
    public Wheeled(int numOfWheels) {
        setNumOfWheels(numOfWheels);
    }

    /** Returns the numOfWheels.  */
    public int getNumOfWheels() {
        return numOfWheels;
    }

    /**
     * Checking set numOfWheels is a legal
     * @param numOfWheels (required).
     * @return <tt>true</tt> if the numOfWheels is a legal else return false.
     */
    public boolean setNumOfWheels(int numOfWheels) {
        this.numOfWheels = numOfWheels;
        return true;
    }

}
