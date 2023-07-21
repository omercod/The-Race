package game.arenas.land;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.air.AerialRacer;
import game.racers.land.LandRacer;
import utilities.EnumContainer.Coverage;
import utilities.EnumContainer.LandSurface;

/**
 * This class defines the LandArena
 * @author Omer Cohen 208627299
 */

public class LandArena extends Arena {
    //Fields
    public final static double DEFAULT_FRICTION = 0.5;
    private static int DEFAULT_MAX_RACERS = 8;
    private final static int DEFAULT_LENGTH = 800;

    //Enum Fields
    private Coverage coverage = Coverage.GRASS;
    private LandSurface surface = LandSurface.FLAT;

    /**
     *default Constructor of the class, the constructor initialize the LandArena
     */
    public LandArena(){
        super(DEFAULT_LENGTH,DEFAULT_MAX_RACERS, DEFAULT_FRICTION);
    }

    /**
     *Constructor of the class, the constructor initialize LandArena
     * @param length (required).
     * @param maxRacers (required).
     */
    public LandArena(double length, int maxRacers){
        super(length,maxRacers,DEFAULT_FRICTION);
    }

    /**
     * Function to add a racer, the function checks if the Racer has MaxRacers of LandArena,if not adds the racer.
     * @param newRacer (newRacer)
     * @throws RacerTypeException if have racer type  problem
     * @throws RacerLimitException if have racer limit  problem
     */
    public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException
    {
        if(!(newRacer instanceof LandRacer)){
            throw new RacerTypeException(newRacer.className(),getClass().toString());
        }
        if(getActiveRacers().size() >= getMAX_RACERS())
            throw new RacerLimitException(getMAX_RACERS(),newRacer.getSerialNumber());
        getActiveRacers().add(newRacer);
    }

    //Methods.Getters_Setters
    //Wind
    /** Returns the Coverage.  */
    public Coverage getCoverage(){ return coverage;}

    /**
     * Checking set coverage is a legal
     * @param coverage (required).
     * @return <tt>true</tt> if the coverage is a legal else return false.
     */
    public boolean SetWind(Coverage coverage){
        this.coverage = coverage;
        return true;
    }
    //Vision
    /** Returns the surface.  */
    public LandSurface getSurface(){ return surface;}
    /**
     * Checking set surface is a legal
     * @param surface (required).
     * @return <tt>true</tt> if the surface is a legal else return false.
     */
    public boolean SetSurface(LandSurface surface){
        this.surface = surface;
        return true;
    }
}
