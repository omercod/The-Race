package game.arenas.air;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.air.AerialRacer;
import utilities.EnumContainer;
import utilities.EnumContainer.Vision;
import utilities.EnumContainer.Weather;
import utilities.EnumContainer.Height;
import utilities.EnumContainer.Wind;


/**
 * This class defines the AerialArena
 * @author Omer Cohen 208627299
 */

public class AerialArena extends Arena {
    //Fields
    public final static double DEFAULT_FRICTION = 0.4;
    private static int DEFAULT_MAX_RACERS = 6;
    private final static int DEFAULT_LENGTH = 1500;

    //Enum Fields
    private Vision vision = Vision.SUNNY;
    private Weather weather = Weather.DRY;
    private Height hight = Height.HIGH;
    private Wind wind = Wind.HIGH;

    /**
     *default Constructor of the class, the constructor initialize the AerialArena
     */
    public AerialArena(){
        super(DEFAULT_LENGTH,DEFAULT_MAX_RACERS, DEFAULT_FRICTION);
    }

    /**
     *Constructor of the class, the constructor initialize AerialArena
     * @param length (required).
     * @param maxRacers (required).
     */
    public AerialArena(double length, int maxRacers){
        super(length,maxRacers,DEFAULT_FRICTION);
    }

    /**
     * Function to add a racer, the function checks if the Racer has MaxRacers of AerialRacer,if not adds the racer.
     * @param newRacer (newRacer)
     * @throws RacerTypeException if have racer type  problem
     * @throws RacerLimitException if have racer limit  problem
     */
    public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException
    {
        if(!(newRacer instanceof AerialRacer)){
            throw new RacerTypeException(newRacer.className(),getClass().toString());
        }
        if(getActiveRacers().size() >= getMAX_RACERS())
            throw new RacerLimitException(getMAX_RACERS(),newRacer.getSerialNumber());

        getActiveRacers().add(newRacer);
    }

    //Methods.Getters_Setters
    //Wind
    /** Returns the wind.  */
    public EnumContainer.Wind getWind(){ return wind;}

    /**
     * Checking set wind is a legal
     * @param wind (required).
     * @return <tt>true</tt> if the wind is a legal else return false.
     */
    public boolean SetWind(EnumContainer.Wind wind){
        this.wind = wind;
        return true;
    }
    //Vision
    /** Returns the vision.  */
    public EnumContainer.Vision getVision(){ return vision;}
    /**
     * Checking set vision is a legal
     * @param vision (required).
     * @return <tt>true</tt> if the vision is a legal else return false.
     */
    public boolean SetVision(EnumContainer.Vision vision){
        this.vision = vision;
        return true;
    }

    //Height
    /** Returns the hight.  */
    public EnumContainer.Height getHeight(){ return hight;}

    /**
     * Checking set hight is a legal
     * @param hight (required).
     * @return <tt>true</tt> if the hight is a legal else return false.
     */
    public boolean SetHeight(EnumContainer.Height hight){
        this.hight = hight;
        return true;
    }

    //Weather
    /** Returns the weather.  */
    public EnumContainer.Weather getWeather(){ return weather;}

    /**
     * Checking set weather is a legal
     * @param weather (required).
     * @return <tt>true</tt> if the weather is a legal else return false.
     */
    public boolean SetWeather(EnumContainer.Weather weather){
        this.weather = weather;
        return true;
    }
}
