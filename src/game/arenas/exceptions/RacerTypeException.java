package game.arenas.exceptions;
/**
 * This class defines the RacerTypeException
 * @author Omer Cohen 208627299
 */

public class RacerTypeException extends Exception{
    /**
     *Constructor of the class, the constructor initialize className and arenaName of the race Type Exception
     * @param className (required).
     * @param arenaName (required).
     */
    public RacerTypeException(String className,String arenaName)
    {
        super("Invalid Racer of type "+className +" for "+arenaName );

    }
}
