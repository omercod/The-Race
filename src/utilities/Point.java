package utilities;

/**
 * This class defines the location at two-dimensional axis
 * @author Omer Cohen 208627299
 */

public class Point {
    //fields
    private  static final int MAX_X = 1000000;
    private  static final int MIN_X = 0;
    private  static final int MAX_Y = 800;
    private  static final int MIN_Y = 0;
    private double x;
    private  double y;

    /**
     *default Constructor of the class, the constructor initialize the Point 0 0
     */
    public Point(){
        this.x = 0;
        this.y = 0;
    }

    /**
     *Constructor of the class, the constructor initialize point x and point y of the Point.
     * @param x (required).
     * @param y (required).
     */
    public Point(double x, double y){
        if(setX(x)){
            this.x = x;
        }
        if(setY(y)){
            this.y = y;
        }
    }

    /**
     *Constructor of the class, the constructor initialize point other of the Point.
     * @param other (required).
     */
    public Point(Point other) {
        if (other == null) {
            other = new Point(0, 0);
        }
        this.setX(other.x);
        this.setY(other.y);
    }

    /**
     *boolean x Setter.
     * @param x (required).
     */
    public boolean setX(double x){
        if(x > MIN_X && x < MAX_X){
            return true;
        }
        this.x = 0;
        return false;
    }
    /**
     *boolean y Setter.
     * @param y (required).
     * @return true or false.
     */
    public boolean setY(double y){
        if(y > MIN_Y && y < MAX_Y){
            return true;
        }
        this.y = 0;
        return false;
    }

    /** @return  x */
    public double getX(){return this.x;}

    /** @return  y*/
    public double getY(){return this.y;}

    /** @return the string of the class */
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
