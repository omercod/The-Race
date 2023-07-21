package factory;

import game.arenas.Arena;
import game.racers.Racer;

import java.awt.*;
import java.util.ArrayList;

public class Race implements RacePlan{
    private Arena arena;
    private Image arenaImage;
    private ArrayList<Racer> racers;
    private Image[] racersImages;

    @Override
    public void setArena(Arena arena) {
        this.arena = arena;
    }
    @Override
    public void setArenaImage(Image arenaImage) {
        this.arenaImage = arenaImage;
    }
    @Override
    public void setRacers(ArrayList<Racer> racers) {
        this.racers = new ArrayList<>(racers);
    }
    public void setRacersImages(Image[] racersImages) {
        this.racersImages = racersImages;
    }

    public Arena getArena() {
        return arena;
    }

    public Image getArenaImage() {
        return arenaImage;
    }

    public ArrayList<Racer> getRacers() {
        return racers;
    }

    public Image[] getRacersImages() {
        return racersImages;
    }
}
