package factory;

import game.arenas.Arena;
import game.racers.Racer;

import java.awt.*;
import java.util.ArrayList;

public interface QuickRaceBuilder {
    public void buildArena();
    public void buildImageArena();
    public void buildRacers();
    public void buildRacersImages();
    public void addRacersToArena();
    public Race getRace();

}
