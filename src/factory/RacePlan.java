package factory;

import game.arenas.Arena;
import game.racers.Racer;

import java.awt.*;
import java.util.ArrayList;

public interface RacePlan {
    public void setArena(Arena arena);
    public void setArenaImage(Image arenaImage);
    public void setRacers(ArrayList<Racer> racers);
    public void setRacersImages(Image[] racersImages);
}
