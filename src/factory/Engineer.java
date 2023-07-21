package factory;

import game.arenas.Arena;

public class Engineer {
    private QuickRaceBuilder quickRaceBuilder;
    public Engineer(QuickRaceBuilder quickRaceBuilder){
        this.quickRaceBuilder = quickRaceBuilder;
    }
    public Race getRace(){
        return this.quickRaceBuilder.getRace();
    }
    public void constructRace(){
        this.quickRaceBuilder.buildArena();
        this.quickRaceBuilder.buildImageArena();
        this.quickRaceBuilder.buildRacers();
        this.quickRaceBuilder.buildRacersImages();
        this.quickRaceBuilder.addRacersToArena();
    }

}
