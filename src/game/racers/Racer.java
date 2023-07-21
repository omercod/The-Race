package game.racers;

import game.arenas.Arena;
import gui.RaceFrame;
import utilities.*;

import java.util.Random;

/**
 * This class represent racer.
 * @author Omer Cohen 208627299
 * @author Shoham Huri 208014951
 */

public abstract class Racer extends Obserable implements Runnable, Cloneable{
    // static serial index
    private static int serial = 1;

    //fields
    private int serialNumber;
    private String name;
    private Point currentLocation;
    private Point finish;
    private Arena arena;
    private double maxSpeed;
    private double acceleration;
    private double currentSpeed;
    private double failureProbability;
    private EnumContainer.Color color;
    private Mishap mishap;
    private String isFinish = "No";

    //gui variables
    private long startTime;
    private long finishTime;
    private int imgIndex;
    //state Ass3
    private AlertStateContext stateContext;
    private double distanceToInvalid;
    private boolean invalidRacer;
    private long BrokenTime;
    private static Random rand = new Random(477734503);
    private boolean racerBroke = false;

    /**
     *Constructor of the class, the constructor initialize Racer.
     * @param name (required).
     * @param maxSpeed (required).
     * @param acceleration (required).
     * @param color (required).
     */
    public Racer(String name, double maxSpeed, double acceleration, EnumContainer.Color color){
        setRacerName(name);
        setMaxSpeed(maxSpeed);
        setAcceleration(acceleration);
        setColor(color);
        setSerialNumber(serial);
        setArena(arena);
        this.serial++;
    }

    public void setBrokenTime(long brokenTime) {
        BrokenTime = brokenTime;
    }

    public long getBrokenTime() {
        return BrokenTime;
    }
    public String getTotalBrokenTime() {
        if(racerBroke && stateContext.alert() == "Completed")
            return ""+(((double)getBrokenTime() - (double)getStartTime()) / 1000.0) + " sec.";
        else
            return "            -";
    }

    public AlertStateContext getStateContext() {
        return stateContext;
    }

    public void setStateContext(RacerAlertState stateContext) {
        this.stateContext.setState(stateContext);
        notifyObserves();
    }

    public boolean isRacerBroke() {
        return racerBroke;
    }

    public void setRacerBroke(boolean racerBroke) {
        this.racerBroke = racerBroke;
    }

    /**
     * Function that clone the racer.
     */
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    /**
     * Function build the arena, start and finish
     * @param arena (Arena).
     * @param start (Point).
     * @param finish (Point).
     */
    public void initRace(Arena arena, Point start, Point finish){
        setCurrentLocation(start);
        setArena(arena);
        setFinish(finish);
        setInvalidRacer(rand.nextInt(10) > 7);
        if (invalidRacer)
            setDistanceToInvalid(rand.nextDouble() * arena.getLength());
        notifyObserves();
    }

    @Override
    public void run(){
        setStartTime(System.currentTimeMillis());
        while (raceInPrograss()) {
            if (Thread.interrupted())
                return;
            move(arena.getFRICTION());
            RaceFrame.getInstanceDisplayPanel().repaint();
            // sleep 30 milliseconds each iteration.
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(getStateContext().alert() == "Invalid")
                Thread.currentThread().interrupt();
        }
        synchronized (arena.getCompletedRacers()) {
            setFinishTime(System.currentTimeMillis());
            arena.crossFinishLine(this);
        }
    }

    public void move(double friction){
        if(getCurrentSpeed() < getMaxSpeed()){
            double x = getCurrentSpeed()+getAcceleration()*friction;
            setCurrentSpeed(Math.min(x,maxSpeed));
        }
        setCurrentLocation(new Point(getCurrentLocation().getX() + getCurrentSpeed(), getCurrentLocation().getY()));
        if(hasMishap()){
            if (mishap.getFixable() && mishap.getTurnsToFix()==0) {
                setMishap(null);
                setStateContext(new Active());
            }
            else if (mishap.getFixable()){
                mishap.nextTurn();
                System.out.println(getRacerName() + " has a mishap! " + mishap.toString());
            }
        }
        else {
            if (Fate.breakDown()) {
                setBrokenTime(System.currentTimeMillis());
                setMishap(Fate.generateMishap());
                setAcceleration(getAcceleration() * mishap.getReductionFactor());
                System.out.println(getRacerName() + " has a new mishap! " +
                        mishap.toString() +", Time: " + (((double)getBrokenTime() - (double)getStartTime()) / 1000.0) + " sec.");
                setStateContext(new Broken());
                setRacerBroke(true);
            }
        }
        if(invalidRacer && getCurrentLocation().getX() >= distanceToInvalid){
            setStateContext(new Invalid());
            arena.getActiveRacers().remove(this);
            arena.getInvalidRacers().add(this);
            setCurrentLocation(new Point(distanceToInvalid, getCurrentLocation().getY()));
            setCurrentSpeed(0);
            System.out.println(getRacerName() + " Invalid! ");

        }
        if (!raceInPrograss()) {
            setCurrentLocation(new Point(finish.getX(), getCurrentLocation().getY()));
            setIsFinish("Yes");
            setStateContext(new Completed());
        }
        // sleep 100 milliseconds each iteration.
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinis) {

        this.isFinish = isFinis;
    }

    public boolean raceInPrograss() {
        if (this.currentLocation != null && this.currentLocation.getX() > 0)
            return this.currentLocation.getX() < this.finish.getX();
        else
            return true;
    }

    public String getTotalTime(){
        if (stateContext.alert() == "Invalid")
            return "           -";
        if (raceInPrograss() && stateContext.alert() != "Invalid")
            return "";
        else {
            double finishTime = getFinishTime();
            double startTime = getStartTime();
            double totalTime = (finishTime - startTime) / 1000.0;
            return String.format("%.3f sec.", totalTime);
        }
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
        notifyObserves();
    }


    public void setStartTime(long startTime) {
        notifyObserves();
        this.startTime = startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public int getImgIndex() {
        return imgIndex;
    }

    public void setImgIndex(int imgIndex) {
        this.imgIndex = imgIndex;
        notifyObserves();
    }

    public abstract String className();
    public abstract String describeSpecific();

    /**
     *  function to Returns from matches that represent the competitor, and contains all the basic details of a competitor
     * @return name + SerialNumber+ maxSpeed + acceleration + color
     */
    public String describeRacer(){
        //name: Car #1, SerialNumber: 1, maxSpeed: 400.0, acceleration: 20.0, color: RED, Number of Wheels: 4, Engine Type: MOUNTAIN
               return "name: " + getRacerName() + ", SerialNumber: " + getSerialNumber() + ", maxSpeed: "  + getMaxSpeed() + ", acceleration: "
                       + getAcceleration() + ", color: " + getColor() + describeSpecific();

    }

    /**
     *  function to print the competitor's type and details.
     * @return className + describeRacer + describeSpecific
     */
    public void introduce()
    {
        System.out.println("["+className()+"] "+describeRacer());
    }

    /** Returns the Serial.  */
    public static int getSerial() {
        return serial;
    }

    /** Returns the name.  */
    public String getRacerName()
    {
        return name;
    }
    /** Returns the getSerialNumber.  */
    public int getSerialNumber() {
        return serialNumber;
    }

    public void setDistanceToInvalid(double distanceToInvalid) {
        this.distanceToInvalid = distanceToInvalid;
        notifyObserves();
    }

    public void setInvalidRacer(boolean invalidRacer) {
        this.invalidRacer = invalidRacer;
        notifyObserves();
    }

    /**
     * Checking set serialNumber is a legal
     * @param serialNumber (required).
     * @return <tt>true</tt> if the serialNumber is a legal else return false.
     */

    public boolean setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
        stateContext = new AlertStateContext();
        notifyObserves();
        return true;
    }
    /**
     * Checking set name is a legal
     * @param name (required).
     * @return <tt>true</tt> if the name is a legal else return false.
     */
    public boolean setRacerName(String name)
    {
        this.name = name;
        notifyObserves();
        return true;
    }

    /** Returns the maxSpeed.  */
    public double getMaxSpeed()
    {
        return this.maxSpeed;
    }
    /**
     * Checking set maxSpeed is a legal
     * @param maxSpeed (required).
     * @return <tt>true</tt> if the name is a legal else return false.
     */
    public boolean setMaxSpeed(double maxSpeed)
    {
        this.maxSpeed = maxSpeed;
        notifyObserves();
        return true;
    }

    /** Returns the acceleration.  */
    public double getAcceleration()
    {
        return this.acceleration;
    }
    /**
     * Checking set acceleration is a legal
     * @param acceleration (required).
     * @return <tt>true</tt> if the name is a legal else return false.
     */
    public boolean setAcceleration(double acceleration)
    {
        this.acceleration = acceleration;
        return true;
    }

    /** Returns the color.  */
    public EnumContainer.Color getColor()
    {
        return this.color;
    }
    /**
     * Checking set acceleration is a legal
     * @param color (required).
     * @return <tt>true</tt> if the name is a legal else return false.
     */
    public boolean setColor(EnumContainer.Color color)
    {
        this.color = color;
        return true;
    }

    /** Returns the currentLocation.  */
    public Point getCurrentLocation() {
        if(currentLocation != null)
            return currentLocation;
        return new Point(0,0);
    }

    /** Returns the finish.  */
    public Point getFinish() {
        return finish;
    }

    /** Returns the arena.  */
    public Arena getArena() {
        return arena;
    }

    /**
     * Checking set currentSpeed is a legal
     * @param currentSpeed (required).
     * @return <tt>true</tt> if the currentSpeed is a legal else return false.
     */
    public boolean setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
        notifyObserves();
        return true;
    }

    /**
     * Checking set CurrentLocation is a legal
     * @param currentLocation (required).
     * @return <tt>true</tt> if the CurrentLocation is a legal else return false.
     */
    public boolean setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
        notifyObserves();
        return true;
    }

    /**
     * Checking set finish is a legal
     * @param finish (required).
     * @return <tt>true</tt> if the finish is a legal else return false.
     */
    public boolean setFinish(Point finish) {
        this.finish = finish;
        notifyObserves();
        return true;
    }

    /**
     * Checking set Arena is a legal
     * @param arena (required).
     * @return <tt>true</tt> if the Arena is a legal else return false.
     */
    public boolean setArena(Arena arena) {
        this.arena = arena;
        return true;
    }

    /** Returns true if mishap is not null.  */
    public boolean hasMishap(){
        if(getMishap() != null){
            return true;
        }
        else return false;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getFailureProbability() {
        return failureProbability;
    }

    public Mishap getMishap() {
        return mishap;
    }

    public static void setSerial(int serial) {
        Racer.serial = serial;
    }

    public void setFailureProbability(double failureProbability) {
        this.failureProbability = failureProbability;
    }

    public void setMishap(Mishap mishap) {
        this.mishap = mishap;
        notifyObserves();
    }

}
