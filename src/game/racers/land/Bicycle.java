package game.racers.land;

import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer;
import utilities.EnumContainer.BicycleType;

/**
 * This class represent Bicycle
 * @author Omer Cohen 208627299
 */

public class Bicycle extends Racer implements LandRacer {
    //fields
    private static final String CLASS_NAME = "Bicycle";
    private static final int DEFAULT_WHEELS = 2;
    private static final double DEFAULT_MAX_SPEED = 270;
    private static final double DEFAULT_ACCELERATION = 10;
    private static EnumContainer.Color DEFAULT_color = EnumContainer.Color.GREEN;
    private Wheeled wheeled;
    private static BicycleType type = BicycleType.MOUNTAIN;

    /**
     *default Constructor of the class, the constructor initialize the Bicycle
     */
    public Bicycle() {
        super("",DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,DEFAULT_color);
        setRacerName("Bicycle #"+getSerialNumber());
        wheeled = new Wheeled(DEFAULT_WHEELS);
    }

    /**
     *Constructor of the class, the constructor initialize Bicycle.
     * @param name (required).
     * @param maxSpeed (required).
     * @param acceleration (required).
     * @param color (required).
     * @param numOfWheels (required).
     */
    public Bicycle(String name, double maxSpeed, double acceleration, EnumContainer.Color color, int numOfWheels) {
        super(name, maxSpeed, acceleration, color);
        wheeled = new Wheeled(numOfWheels);
        setType(type);
    }

    /** Returns the className.  */
    public String className() {
        return "Bicycle";
    }

    /** Returns the Specific String of the class.  */
    public String describeSpecific() {
        return ", Number Of Wheels: " + wheeled.getNumOfWheels()+ ", Bicycle Type: "+getType();
    }

    /** Returns the type.  */
    public BicycleType getType() {
        return this.type;
    }

    /**
     * Checking set type is a legal
     * @param type (required).
     * @return <tt>true</tt> if the type is a legal else return false.
     */
    public boolean setType(BicycleType type) {
        this.type = type;
        return true;
    }
}
