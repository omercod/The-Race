package factory;

import game.arenas.Arena;
import game.arenas.air.AerialArena;
import game.arenas.land.LandArena;
import game.arenas.naval.NavalArena;

public class ArenaFactory {
    public Arena getArena(String arenaType){
        if (arenaType == null) return null;
        if (arenaType.equalsIgnoreCase("Air"))
            return new AerialArena();
        else if (arenaType.equalsIgnoreCase("Land"))
            return new LandArena();
        else if (arenaType.equalsIgnoreCase("Navy"))
            return new NavalArena();
        return null;
    }
}