package factory;
import com.sun.jdi.ClassObjectReference;
import game.arenas.Arena;
import game.racers.Racer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import utilities.EnumContainer;

/**
 * This class build the RaceBuilder.
 * @author Omer Cohen 208627299
 * @author Shoham Huri 208014951
 */

public class RaceBuilder {



    /**
     * Private constructor to prevent other instances from being created.
     */
    private RaceBuilder() {}

    /**
     *Constructor of the class, the constructor initialize the single object.
     */
    public static RaceBuilder getInstance(){
        if(instance==null)
            instance=new RaceBuilder();
        return instance;
    }

    /**
     *Function that build arena using java reflection.
     * @param arenaType (required).
     * @param length (required).
     * @param maxRacers (required).
     */
    //fields
    private static RaceBuilder instance;
    private ClassLoader classLoader;
    private Class<?> classObject;
    private Constructor<?> constructor;
    public Arena buildArena (String arenaType, double length, int maxRacers)throws ClassNotFoundException,
            NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException
    {
        classLoader = ClassLoader.getSystemClassLoader();
        classObject = classLoader.loadClass(arenaType);
        constructor = classObject.getConstructor(double.class, int.class);
        return (Arena)constructor.newInstance(length,maxRacers);
    }

    /**
     *Function that build Racer using java reflection.
     * @param racerType (required).
     * @param name (required).
     * @param maxSpeed (required).
     * @param color (required).
     */
    public Racer buildRacer(String racerType, String name, double maxSpeed, double acceleration,
                            EnumContainer.Color color)throws ClassNotFoundException,
            NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException
    {
        classLoader = ClassLoader.getSystemClassLoader();
        classObject = classLoader.loadClass(racerType);
        constructor = classObject.getConstructor(String.class, double.class, double.class, EnumContainer.Color.class);
        return (Racer) constructor.newInstance(name, maxSpeed, acceleration,color);
    }

    /**
     *Function that build WheeledRacer using java reflection.
     * @param racerType (required).
     * @param name (required).
     * @param maxSpeed (required).
     * @param color (required).
     */
    public Racer buildWheeledRacer(String racerType, String name, double maxSpeed, double acceleration,
                                   EnumContainer.Color color, int numOfWheels)throws ClassNotFoundException,
            NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException
    {
        classLoader = ClassLoader.getSystemClassLoader();
        classObject = classLoader.loadClass(racerType);
        constructor = classObject.getConstructor(String.class, double.class, double.class, EnumContainer.Color.class, int.class);
        return (Racer) constructor.newInstance(name, maxSpeed, acceleration,color, numOfWheels);
    }

}
