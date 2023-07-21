package factory;
import game.racers.Racer;
import java.util.Hashtable;

public class RacerCache {
    private static Hashtable<String, Racer> RacerMap = new Hashtable<String, Racer>();
    public static Racer getRacer (String racerId) {
        Racer cachedRacer = RacerMap.get(racerId);
        return (Racer) cachedRacer.clone();
    }
    public static Hashtable<String, Racer> getRacerMap() {
        return RacerMap;
    }
}
