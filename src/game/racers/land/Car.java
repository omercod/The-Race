package game.racers.land;

import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer;

/**
 * This class represent Car.
 * @author Omer Cohen 208627299
 */

public class Car extends Racer implements LandRacer {
    //fields
    private static final String CLASS_NAME = "Car";
    private static final int DEFAULT_WHEELS = 4;
    private static final double DEFAULT_MAX_SPEED = 400;
    private static final double DEFAULT_ACCELERATION = 20;
    private static EnumContainer.Color DEFAULT_color = EnumContainer.Color.RED;
    private Wheeled wheeled;
    private static EnumContainer.Engine engine = EnumContainer.Engine.FOURSTROKE;

    /**
     *default Constructor of the class, the constructor initialize the Car
     */
    public Car() {
        super("",DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION,DEFAULT_color);
        setRacerName("Car #"+getSerialNumber());
        wheeled = new Wheeled(DEFAULT_WHEELS);
    }

    /**
     *Constructor of the class, the constructor initialize Car.
     * @param name (required).
     * @param maxSpeed (required).
     * @param acceleration (required).
     * @param color (required).
     * @param numOfWheels (required).
     */
    public Car(String name, double maxSpeed, double acceleration, EnumContainer.Color color, int numOfWheels) {
        super(name, maxSpeed, acceleration, color);
        wheeled = new Wheeled(numOfWheels);
    }

    /** Returns the className.  */
    public String className() {
        return "Car";
    }

    /** Returns the Specific String of the class.  */
    public String describeSpecific() {
        return ", Number Of Wheels: " + this.wheeled.getNumOfWheels()+", Engine Type: "+getEngine();
    }

    /** Returns the engine.  */
    public EnumContainer.Engine getEngine() {
        return engine;
    }

    /**
     * Checking set engine is a legal
     * @param engine (required).
     * @return <tt>true</tt> if the engine is a legal else return false.
     */
    public boolean setEngine(EnumContainer.Engine engine) {
        this.engine = engine;
        return true;
    }
}
