package game.arenas.exceptions;

/**
 * This class defines the RacerTypeException
 * @author Omer Cohen 208627299
 */

public class RacerLimitException extends Exception{
    /**
     *Constructor of the class, the constructor initialize className and arenaName of the race limit Exception
     * @param serial (required).
     * @param maxRacers (required).
     */
    public RacerLimitException(int maxRacers, int serial)
    {
        super("Arena is full! ("+maxRacers +" active racers exist). racer #"+serial+ " was not added\r");

    }
}
