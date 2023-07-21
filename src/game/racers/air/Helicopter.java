package game.racers.air;

import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer;

/**
 * This class represent Helicopter.
 * @author Omer Cohen 208627299
 */

public class Helicopter extends Racer implements AerialRacer {
    //fields
    private static final String CLASS_NAME = "Helicopter";
    private static final double DEFAULT_MAX_SPEED = 400;
    private static final double DEFAULT_ACCELERATION = 50;
    private static EnumContainer.Color DEFAULT_color = EnumContainer.Color.BLUE;

    /**
     *default Constructor of the class, the constructor initialize the Helicopter
     */
    public Helicopter() {
        super("",DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,DEFAULT_color);
        setRacerName("Helicopter #"+getSerialNumber());
    }

    /**
     *Constructor of the class, the constructor initialize Helicopter.
     * @param name (required).
     * @param maxSpeed (required).
     * @param acceleration (required).
     * @param color (required).
     */
    public Helicopter(String name, double maxSpeed, double acceleration, EnumContainer.Color color) {
        super(name,maxSpeed,acceleration,color);
    }

    /** Returns the className.  */
    public String className() {
        return CLASS_NAME;
    }

    /** Returns the Specific String of the class.  */
    public String describeSpecific() {
        return "";
    }
}
