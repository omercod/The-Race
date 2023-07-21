package game.racers.land;

import game.racers.Racer;
import utilities.EnumContainer;
import utilities.EnumContainer.Breed;

/**
 * This class represent Horse
 * @author Omer Cohen 208627299
 */

public class Horse extends Racer implements LandRacer {
    //fields
    private static final String CLASS_NAME = "Horse";
    private static final double DEFAULT_MAX_SPEED = 50;
    private static final double DEFAULT_ACCELERATION = 3;
    private static EnumContainer.Color DEFAULT_color = EnumContainer.Color.BLACK;
    private static Breed breed = Breed.THOROUGHBRED;

    /**
     *default Constructor of the class, the constructor initialize the Horse
     */
    public Horse() {
        super("",DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,DEFAULT_color);
        setRacerName("Horse #"+getSerialNumber());
    }

    /**
     *Constructor of the class, the constructor initialize Horse.
     * @param name (required).
     * @param maxSpeed (required).
     * @param acceleration (required).
     * @param color (required).
     */
    public Horse(String name, double maxSpeed, double acceleration, EnumContainer.Color color) {
        super(name, maxSpeed, acceleration, color);
        setBreed(breed);
    }

    /** Returns the className.  */
    public String className() {
        return "Horse";
    }

    /** Returns the Specific String of the class.  */
    public String describeSpecific() {
        return ", Breed Type: "+getBreed();
    }

    /** Returns the breed.  */
    public Breed getBreed() {
        return this.breed;
    }

    /**
     * Checking set breed is a legal
     * @param breed (required).
     * @return <tt>true</tt> if the breed is a legal else return false.
     */
    public boolean setBreed(Breed breed) {
        this.breed = breed;
        return true;
    }

}
