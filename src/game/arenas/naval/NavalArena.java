package game.arenas.naval;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.naval.NavalRacer;
import utilities.EnumContainer.Water;
import utilities.EnumContainer.WaterSurface;
import utilities.EnumContainer.Body;

/**
 * This class defines the NavalArena
 * @author Omer Cohen 208627299
 */

public class NavalArena extends Arena {
    //Fields
    public final static double DEFAULT_FRICTION = 0.7;
    private static int DEFAULT_MAX_RACERS = 5;
    private final static int DEFAULT_LENGTH = 1000;

    //Enum Fields
    private Water water = Water.SWEET;
    private WaterSurface surface = WaterSurface.FLAT;
    private Body body = Body.LAKE;

    /**
     *default Constructor of the class, the constructor initialize the NavalArena.
     */
    public NavalArena(){
        super(DEFAULT_LENGTH,DEFAULT_MAX_RACERS, DEFAULT_FRICTION);
    }

    /**
     *Constructor of the class, the constructor initialize NavalArena.
     * @param length (required).
     * @param maxRacers (required).
     */
    public NavalArena(double length, int maxRacers){
        super(length,maxRacers,DEFAULT_FRICTION);
    }

    /**
     * Function to add a racer, the function checks if the Racer has MaxRacers of AerialRacer,if not adds the racer.
     * @param newRacer (newRacer)
     * @throws RacerTypeException have wrong racer type.
     * @throws RacerLimitException have racer over limit.
     */
    public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException
    {
        if(!(newRacer instanceof NavalRacer)){
            throw new RacerTypeException(newRacer.className(),getClass().toString());
        }
        if(getActiveRacers().size() >= getMAX_RACERS())
            throw new RacerLimitException(getMAX_RACERS(),newRacer.getSerialNumber());
        getActiveRacers().add(newRacer);
    }

    /** Returns the water.  */
    public Water getWater() {
        return water;
    }

    /**
     * Checking set water is a legal
     * @param water (required).
     * @return <tt>true</tt> if the water is a legal else return false.
     */
    public boolean setWater(Water water) {
        this.water = water;
        return true;
    }

    /** Returns the surface.  */
    public WaterSurface getSurface() {
        return surface;
    }

    /**
     * Checking set surface is a legal
     * @param surface (required).
     * @return <tt>true</tt> if the surface is a legal else return false.
     */
    public boolean setSurface(WaterSurface surface) {
        this.surface = surface;
        return true;
    }

    /** @return body.  */
    public Body getBody() {
        return body;
    }

    /**
     * Checking set body is a legal
     * @param body (required).
     * @return <tt>true</tt> if the body is a legal else return false.
     */
    public boolean setBody(Body body) {
        this.body = body;
        return true;
    }
}
