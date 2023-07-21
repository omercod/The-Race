package game.racers.air;

import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer;

/**
 * This class represent Airplane.
 * @author Omer Cohen 208627299
 */

public class Airplane extends Racer implements AerialRacer {
    //fields
    private static final String CLASS_NAME = "Airplane";
    private static final int DEFAULT_WHEELS = 3;
    private static final double DEFAULT_MAX_SPEED = 885;
    private static final double DEFAULT_ACCELERATION = 100;
    private static EnumContainer.Color DEFAULT_color = EnumContainer.Color.BLACK;
    private Wheeled wheeled;

    /**
     *default Constructor of the class, the constructor initialize the Airplane
     */
    public Airplane() {
        super("",DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION,DEFAULT_color);
        setRacerName("Airplane #"+getSerialNumber());
        wheeled = new Wheeled(DEFAULT_WHEELS);
    }


    /**
     *Constructor of the class, the constructor initialize Airplane.
     * @param name (required).
     * @param maxSpeed (required).
     * @param acceleration (required).
     * @param color (required).
     * @param numOfWheels (required).
     */
    public Airplane(String name, double maxSpeed, double acceleration, EnumContainer.Color color, int numOfWheels) {
        super(name, maxSpeed, acceleration, color);
        wheeled = new Wheeled(numOfWheels);
    }

    /** Returns the className.  */
    public String className() {
        return "Airplane";
    }

    /** Returns the Specific String of the class.  */
    public String describeSpecific() {
        return ", Number Of Wheels: " + this.wheeled.getNumOfWheels();
    }
}
