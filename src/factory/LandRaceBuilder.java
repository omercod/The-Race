package factory;

import game.arenas.Arena;
import game.racers.Racer;
import game.racers.land.Car;
import utilities.EnumContainer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LandRaceBuilder implements QuickRaceBuilder {
    private Race race;
    private int racersNumber;
    private RacerCache racerCache;

    public LandRaceBuilder(int n) {
        this.race = new Race();
        this.racersNumber = n;
    }

    public void buildArena() {
        ArenaFactory arenaFactory = new ArenaFactory();
        race.setArena(arenaFactory.getArena("Land"));
    }

    @Override
    public void buildImageArena() {
        Image arenaImage = new ImageIcon("icons/LandArena.jpg").getImage();
        race.setArenaImage(arenaImage);
    }

    public void buildRacers() {
        ArrayList<Racer> racers = new ArrayList<>(racersNumber);
        RaceBuilder builder = RaceBuilder.getInstance();
        int selectedRacerSN;
        //Create a default racer
        try {
            racers.add(builder.buildWheeledRacer("game.racers.land.Car", "Shoham",
                    18, 6, EnumContainer.Color.BLUE, 4));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        racerCache.getRacerMap().put("" + racers.get(0).getSerialNumber(), racers.get(0));
        //cloned all the other racers
        for (int i = 1; i < racersNumber; i++) {
            selectedRacerSN = racers.get(0).getSerialNumber();
            Racer clonedRacer = (Racer) racerCache.getRacer("" + selectedRacerSN);
            clonedRacer.setSerialNumber(Racer.getSerial());
            String newName = racers.get(0).getRacerName() + clonedRacer.getSerialNumber();
            Racer.setSerial(Racer.getSerial() + 1);
            clonedRacer.setRacerName(newName);
            racerCache.getRacerMap().put("" + clonedRacer.getSerialNumber(), clonedRacer);
            racers.add(clonedRacer);
        }
        race.setRacers(racers);
    }

    public void buildRacersImages() {
        Image image = new ImageIcon("icons/CarBlue.png").getImage();
        Image[] images = new Image[race.getArena().getMAX_RACERS()];
        for (int i = 0; i < racersNumber; i++) {
            images[i] = image;
        }
        race.setRacersImages(images);
    }

    @Override
    public void addRacersToArena() {
        for (int i = 0; i < racersNumber; i++) {
            try {
                race.getArena().addRacer(race.getRacers().get(i));
                race.getArena().initRace();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Override
    public Race getRace() {
        return race;
    }
}
