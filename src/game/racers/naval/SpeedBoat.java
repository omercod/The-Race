package game.racers.naval;

import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer;
import utilities.EnumContainer.BoatType;
import utilities.EnumContainer.Team;

/**
 * This class represent SpeedBoat
 * @author Omer Cohen 208627299
 */

public class SpeedBoat extends Racer implements NavalRacer {
    //fields
    private static final String CLASS_NAME = "SpeedBoat";
    private static final double DEFAULT_MAX_SPEED = 170;
    private static final double DEFAULT_ACCELERATION = 5;
    private static EnumContainer.Color DEFAULT_color = EnumContainer.Color.RED;
    private static BoatType type = BoatType.SKULLING;
    private static Team team = Team.DOUBLE;

    /**
     *Constructor of the class, the constructor initialize SpeedBoat.
     * @param name (required).
     * @param maxSpeed (required).
     * @param acceleration (required).
     * @param color (required).
     */
    public SpeedBoat(String name, double maxSpeed, double acceleration, EnumContainer.Color color) {
        super(name, maxSpeed, acceleration, color);
        setTeam(team);
        setType(type);
    }

    /**
     *default Constructor of the class, the constructor initialize the SpeedBoat
     */
    public SpeedBoat() {
        super("",DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,DEFAULT_color);
        setRacerName("SpeedBoat #"+getSerialNumber());
    }

    /** Returns the className.  */
    public String className() {
        return CLASS_NAME;
    }

    /** Returns the Specific String of the class.  */
    public String describeSpecific() {
        return ", Type: "+getType()+ ", Team: " + getTeam();
    }

    /** Returns the Specific String of the class.  */
    public static BoatType getType() {
        return type;
    }

    /**
     * Checking set breed is a legal
     * @param type (required).
     * @return <tt>true</tt> if the type is a legal else return false.
     */
    public boolean setType(EnumContainer.BoatType type) {
        this.type = type;
        return true;
    }

    /** Returns the Specific String of the class.  */
    public static Team getTeam() {
        return team;
    }

    /**
     * Checking set team is a legal
     * @param team (required).
     * @return <tt>true</tt> if the team is a legal else return false.
     */
    public boolean setTeam(Team team) {
        this.team = team;
        return true;
    }
}
