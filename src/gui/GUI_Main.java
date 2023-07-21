package gui;
import gui.RaceFrame;
import utilities.Fate;
/**
 * This main that activate the main frame.
 * @author Omer Cohen 208627299
 * @author Shoham Huri 208014951
 */
public class GUI_Main {
    public static void main(String[] args) {
        Fate.setSeed(477734503); // to get same "random" results every run;
        new RaceFrame();
    }
}
