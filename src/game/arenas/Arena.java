package game.arenas;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.Point;

import gui.RaceFrame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class defines the Arena
 * @author Omer Cohen 208627299
 * @author Shoham Huri 208014951
 */

public abstract class Arena {
    //Fiels
    private ArrayList<Racer> activeRacers;
    private ArrayList<Racer> completedRacers;
    private final double FRICTION;
    private final int MAX_RACERS;
    private static final int MIN_Y_GAP = 10; //default 10
    private double length;
    private ArrayList<Racer> InvalidRacers;

    public Arena(){
        this.activeRacers = null;
        this.completedRacers = null;
        this.FRICTION = 0;
        this.MAX_RACERS = 0;
        this.length = 0;
    }


    /**
     *Constructor of the class, the constructor initialize length and maxRacers of the race and setter
     * @param length (required).
     * @param maxRacers (required).
     * @param friction (required).
     */
    public Arena(double length, int maxRacers, double friction){
        setLength(length);
        this.FRICTION = friction;
        this.MAX_RACERS = maxRacers;
        setActiveRacers(new ArrayList<Racer>());
        setCompletedRacers(new ArrayList<Racer>());
        setInvalidRacers(new ArrayList<Racer>());
    }

    public void setInvalidRacers(ArrayList<Racer> invalidRacers) {
        InvalidRacers = invalidRacers;
    }

    public ArrayList<Racer> getInvalidRacers() {
        return InvalidRacers;
    }

    public abstract void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException;

    /** initialize each racer location. */
    public void initRace(){
        int i = 0;
        for (Racer active : this.getActiveRacers()){
            active.initRace(this, new Point(0,i), new Point(this.getLength(),i));
            i += MIN_Y_GAP;
        }
    }

    private double parseTotalTime(String totalTime) {
        // Remove the " sec." suffix and convert the remaining part to a double
        String numericPart = totalTime.replace(" sec.", "");
        return Double.parseDouble(numericPart);
    }

    public String getPlace(String name) {
        ArrayList<Racer> allRacers = new ArrayList<>();
        allRacers.addAll(getCompletedRacers());
        allRacers.addAll(getActiveRacers());

        // Sort all racers by location (descending order)
        allRacers.sort(Comparator.comparingDouble((Racer racer) -> racer.getCurrentLocation().getX()).reversed());
        // If all racers have completed the race, sort them by total time (ascending order)
        if (allRacers.size() == getCompletedRacers().size()) {
            allRacers.sort(Comparator.comparingDouble(racer -> parseTotalTime(racer.getTotalTime())));
        }

        int place = 1;
        for (Racer racer : allRacers) {
            if (racer.getRacerName().equals(name)) {
                return "#" + place;
            }
            place++;
        }
        return "-";
    }

    /** @return if more active racers left. */
    public boolean hasActiveRacers() {
        return !activeRacers.isEmpty();
    }

    //GUI runnable
    /** play the turn for each Racer.
     * */
    public void playTurn(){
        for (int i = 0; i < getActiveRacers().size(); i++) {
            Thread racer = new Thread(getActiveRacers().get(i));
            racer.start();
        }
    }

    /**
     * Add racers who cross the finish line to CompletedRacers ArrayList.
     * @param racer (required).
     */
    public void crossFinishLine(Racer racer){
        getActiveRacers().remove(racer);
        getCompletedRacers().add(racer);
    }

    /** print the completed racers by this format */
    //#0 -> name: Frank, SerialNumber: 10, maxSpeed: 180.0, acceleration: 15.0, color: BLUE, Number of Wheels: 3
    public void showResults(){
        for (int i = 0; i < getCompletedRacers().size(); i++) {
            System.out.println("#" +i+ " -> " +getCompletedRacers().get(i).describeRacer());
        }
    }

    /** Returns the MAX_RACERS.  */
    public int getMAX_RACERS() {
        return MAX_RACERS;
    }
    /** Returns the activeRacers.  */
    public ArrayList<Racer> getActiveRacers() {
        return activeRacers;
    }
    /**
     * Checking set activeRacers is a legal
     * @param activeRacers (required).
     * @return <tt>true</tt> if the activeRacers is a legal else return false.
     */
    public boolean setActiveRacers(ArrayList<Racer> activeRacers) {
        this.activeRacers = activeRacers;
        return true;
    }


    /** Returns the completedRacers.  */
    public ArrayList<Racer> getCompletedRacers() {
        return completedRacers;
    }
    /**
     * Checking set completedRacers is a legal
     * @param completedRacers (required).
     * @return <tt>true</tt> if the completedRacers is a legal else return false.
     */
    public boolean setCompletedRacers(ArrayList<Racer> completedRacers) {
        this.completedRacers = completedRacers;
        return true;
    }
    /** Returns the FRICTION.  */
    public double getFRICTION() {
        return FRICTION;
    }
    /** Returns the length.  */
    public double getLength() {
        return length;
    }
    /**
     * Checking set length is a legal
     * @param length (required).
     * @return <tt>true</tt> if the length is a legal else return false.
     */
    public boolean setLength(double length) {
        this.length = length;
        return true;
    }
}
