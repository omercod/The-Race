package gui;

import factory.*;
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import utilities.EnumContainer;
import utilities.Observer;
import utilities.Point;

/**
 * This main Frame Class
 *
 * @author Omer Cohen 208627299
 * @author Shoham Huri 208014951
 */


public class RaceFrame extends JFrame implements ActionListener {
    // Static variables
    private static RaceBuilder builder = RaceBuilder.getInstance();
    private static ArrayList<Racer> racers;

    //Final string arrays for the Combo boxes.
    final static String[] ARENAS_NAMES = {"AerialArena", "LandArena", "NavalArena"};
    final static String[] ARENA_FACTORY_NAMES = {"Air", "Land", "Navy"};
    final static String[] RACERS_NAMES = {"Airplane", "Helicopter", "Car", "Bicycle", "Horse", "RowBoat", "SpeedBoat"};
    final static String[] COLORS = {"Black", "Blue", "Green", "Red", "Yellow"};
    final static String[] LAND_RACERS = {"1", "2", "3", "4", "5", "6", "7", "8"};
    //Attributes
    private int arenaLength = 1000;
    private int arenaHeight = 700;
    private int maxRacers = 8;
    private int x, y;
    private String arenaName = null;
    private Arena arena = null;
    private int racersNumber = 0;
    public static Image racersImages[] = null;
    private Image arenaImage = null;
    private boolean raceFinished = false;
    private boolean raceStarted = false;
    // GUI variables
    private final Image finishLineImg = new ImageIcon("icons/RaceFinishLine.jpg").getImage();
    private JTextField tfArenaLength, tfMaxRacers, tfRacerName, tfMaxSpeed, tfAcceleration, tfRacersNumber;
    private JComboBox<String> cmbArena, cmbRacer, cmbColor, cmbPrototype, cmbQuickColor, cmbArenaFactory, cmbLandRace;
    private JButton buildArenaBtn;
    private JLabel lblTHERACE, lblArenaLength, lblArenaMaxRacers;
    private JPanel controlsPanel, quickMenu;
    private static DisplayPanel displayPanel;
    private JFrame infoTable = null;
    private Object[][] data = null;
    private JTable table;
    //Assm3
    private int previousPrototypeIndex = 0;
    private RacerCache racerCache;

    public RaceFrame() {
        super("Race");
        // Center screen
        this.setSize(arenaLength + 322, arenaHeight);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        x = (screenSize.width - getWidth()) / 2;
        y = (screenSize.height - getHeight()) / 2;
        this.setLocationRelativeTo(null);
        this.setLocation(x, y);
        // Layout and ad the Main Panel.
        this.setLayout(new BorderLayout());
        getContentPane().add(getMainPanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static Image[] getRacersImages() {
        return racersImages;
    }

    public static DisplayPanel getInstanceDisplayPanel() {
        return displayPanel;
    }

    public JPanel getArenaPanel() {
        JPanel arenaPanel = new JPanel();
        JLabel lblChooseArena = new JLabel("Choose Arena:");
        JLabel lblCArenaLength = new JLabel("Arena Length:");
        JLabel lblMaxRacers = new JLabel("Max Racers Number:");
        cmbArena = new JComboBox<String>(ARENAS_NAMES);
        tfArenaLength = new JTextField("1000");
        tfMaxRacers = new JTextField("8");

        arenaPanel.setLayout(null);
        arenaPanel.setPreferredSize(new Dimension(150, 240));
        arenaPanel.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 2));
        //Choose Arena
        arenaPanel.add(lblChooseArena);
        lblChooseArena.setLocation(10, 20);
        lblChooseArena.setSize(100, 10);
        arenaPanel.add(cmbArena);
        cmbArena.setLocation(10, 40);
        cmbArena.setSize(100, 20);
        //Arena Length
        arenaPanel.add(lblCArenaLength);
        lblCArenaLength.setLocation(10, 75);
        lblCArenaLength.setSize(100, 15);
        arenaPanel.add(tfArenaLength);
        tfArenaLength.setLocation(10, 95);
        tfArenaLength.setSize(100, 25);
        //Max Racers
        arenaPanel.add(lblMaxRacers);
        lblMaxRacers.setLocation(10, 135);
        lblMaxRacers.setSize(150, 10);
        arenaPanel.add(tfMaxRacers);
        tfMaxRacers.setLocation(10, 155);
        tfMaxRacers.setSize(100, 25);
        //Build Arena Btn
        buildArenaBtn = new JButton("Build Arena");
        buildArenaBtn.setLocation(10, 195);
        buildArenaBtn.setSize(100, 30);
        buildArenaBtn.addActionListener(this);
        arenaPanel.add(buildArenaBtn);

        return arenaPanel;
    }

    public JPanel getRacerPanel() {
        JPanel racerPanel = new JPanel();
        JLabel lblChooseRacer = new JLabel("Choose Racer:");
        JLabel lblChooseColor = new JLabel("Choose Color:");
        JLabel lblRacerName = new JLabel("Racers Name:");
        JLabel lblMaxSpeed = new JLabel("Max Speed:");
        JLabel lblAcceleration = new JLabel("Acceleration:");
        tfRacerName = new JTextField("");
        tfMaxSpeed = new JTextField("");
        tfAcceleration = new JTextField("");
        cmbRacer = new JComboBox<String>(RACERS_NAMES);
        cmbColor = new JComboBox<String>(COLORS);

        racerPanel.setLayout(null);
        racerPanel.setPreferredSize(new Dimension(150, 240));
        racerPanel.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 2));
        //Choose Racer
        racerPanel.add(lblChooseRacer);
        lblChooseRacer.setLocation(10, 20);
        lblChooseRacer.setSize(100, 10);
        racerPanel.add(cmbRacer);
        cmbRacer.setLocation(10, 40);
        cmbRacer.setSize(100, 20);
        //Choose Color
        racerPanel.add(lblChooseColor);
        lblChooseColor.setLocation(10, 75);
        lblChooseColor.setSize(100, 15);
        racerPanel.add(cmbColor);
        cmbColor.setLocation(10, 95);
        cmbColor.setSize(100, 20);
        //Racer name
        racerPanel.add(lblRacerName);
        lblRacerName.setLocation(10, 130);
        lblRacerName.setSize(100, 15);
        racerPanel.add(tfRacerName);
        tfRacerName.setLocation(10, 150);
        tfRacerName.setSize(100, 25);
        //Max Racers
        racerPanel.add(lblMaxSpeed);
        lblMaxSpeed.setLocation(10, 180);
        lblMaxSpeed.setSize(100, 15);
        racerPanel.add(tfMaxSpeed);
        tfMaxSpeed.setLocation(10, 200);
        tfMaxSpeed.setSize(100, 25);
        //Acceleration
        racerPanel.add(lblAcceleration);
        lblAcceleration.setLocation(10, 230);
        lblAcceleration.setSize(100, 15);
        racerPanel.add(tfAcceleration);
        tfAcceleration.setLocation(10, 250);
        tfAcceleration.setSize(100, 25);
        //Add Racer Btn
        JButton addRacerBtn = new JButton("Add Racer");
        addRacerBtn.setLocation(10, 285);
        addRacerBtn.setSize(100, 30);
        addRacerBtn.addActionListener(this);
        racerPanel.add(addRacerBtn);

        return racerPanel;
    }

    public JPanel getRaceActionsPanel() {
        JPanel RaceActions = new JPanel();
        RaceActions.setLayout(null);
        RaceActions.setPreferredSize(new Dimension(150, 95));
        RaceActions.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 2));
        //Add Racer Btn
        JButton startRaceBtn = new JButton("Start Race");
        startRaceBtn.setLocation(10, 10);
        startRaceBtn.setSize(100, 30);
        startRaceBtn.addActionListener(this);
        RaceActions.add(startRaceBtn);
        //Show Info Btn
        JButton ShowInfoBtn = new JButton("Show Info");
        ShowInfoBtn.setLocation(10, 50);
        ShowInfoBtn.setSize(100, 30);
        ShowInfoBtn.addActionListener(this);
        RaceActions.add(ShowInfoBtn);
        return RaceActions;
    }

    public JPanel getControlsPanel() {
        controlsPanel = new JPanel();
        controlsPanel.setPreferredSize(new Dimension(140, 700));
        controlsPanel.setLayout(new BorderLayout());
        controlsPanel.add(getArenaPanel(), BorderLayout.NORTH);
        controlsPanel.add(getRacerPanel(), BorderLayout.CENTER);
        controlsPanel.add(getRaceActionsPanel(), BorderLayout.SOUTH);
        controlsPanel.setMaximumSize(controlsPanel.getPreferredSize());
        return controlsPanel;
    }

    // Assm 3
    public JPanel getQuickMenuPanel() {
        quickMenu = new JPanel();
        JLabel lblQuickMenu = new JLabel("Quick Menu");
        JLabel lblCarRace = new JLabel("Car Race");
        JLabel lblChooseColor = new JLabel("New Color:");
        JLabel lblChooseArena = new JLabel("Choose Arena:");
        lblArenaLength = new JLabel("Length:");
        lblArenaMaxRacers = new JLabel("Max Racers:");
        cmbPrototype = new JComboBox<>();
        cmbPrototype.addActionListener(this);
        cmbQuickColor = new JComboBox<String>(COLORS);
        cmbArenaFactory = new JComboBox<String>(ARENA_FACTORY_NAMES);
        updateRacerNames();
        quickMenu.setPreferredSize(new Dimension(110, 700));
        quickMenu.setLayout(null);
        quickMenu.setMaximumSize(quickMenu.getPreferredSize());

        //set the inputs and labels
        quickMenu.add(lblQuickMenu);
        lblQuickMenu.setLocation(10, 15);
        lblQuickMenu.setSize(100, 20);
        lblQuickMenu.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblQuickMenu.setForeground(new Color(99, 98, 108));
        // Add the black thin line
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBackground(Color.BLACK);
        separator.setForeground(Color.BLACK);
        separator.setLocation(0, 40);
        separator.setSize(110, 1);
        quickMenu.add(separator);
        //////Prototype//////
        // Racers
        JLabel lblRacers = new JLabel("Racers:");
        quickMenu.add(lblRacers);
        lblRacers.setLocation(5, 45);
        lblRacers.setSize(100, 20);
        quickMenu.add(cmbPrototype);
        cmbPrototype.setLocation(5, 65);
        cmbPrototype.setSize(100, 20);
        //color
        quickMenu.add(lblChooseColor);
        lblChooseColor.setLocation(5, 93);
        lblChooseColor.setSize(100, 15);
        quickMenu.add(cmbQuickColor);
        cmbQuickColor.setLocation(5, 110);
        cmbQuickColor.setSize(100, 20);
        // Prototype button
        JButton prototypeBtn = new JButton("Prototype");
        prototypeBtn.setLocation(10, 140);
        prototypeBtn.setSize(90, 30);
        prototypeBtn.addActionListener(this);
        quickMenu.add(prototypeBtn);
        // Add the light blue thin line
        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setBackground(new Color(173, 216, 230));
        separator2.setForeground(new Color(173, 216, 230));
        separator2.setLocation(0, 180);
        separator2.setSize(110, 3);
        Border lineBorder = BorderFactory.createLineBorder(new Color(173, 216, 230), 3);
        separator2.setBorder(lineBorder);
        quickMenu.add(separator2);
        //////Arena Factory//////
        // Arena cmb
        quickMenu.add(lblChooseArena);
        lblChooseArena.setLocation(10, 190);
        lblChooseArena.setSize(100, 20);
        quickMenu.add(cmbArenaFactory);
        cmbArenaFactory.setLocation(5, 215);
        cmbArenaFactory.setSize(100, 20);
        // Arena Factory button
        JButton arenaFactoryBtn = new JButton("Quick Arena");
        arenaFactoryBtn.setLocation(2, 245);
        arenaFactoryBtn.setSize(103, 30);
        arenaFactoryBtn.addActionListener(this);
        quickMenu.add(arenaFactoryBtn);
        quickMenu.add(lblArenaLength);
        lblArenaLength.setLocation(5, 278);
        lblArenaLength.setSize(100, 20);
        lblArenaLength.setForeground(new Color(145, 145, 145));
        quickMenu.add(lblArenaMaxRacers);
        lblArenaMaxRacers.setLocation(5, 295);
        lblArenaMaxRacers.setSize(100, 20);
        lblArenaMaxRacers.setForeground(new Color(145, 145, 145));
        // Add the light blue thin line
        JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
        separator3.setBackground(new Color(173, 216, 230));
        separator3.setForeground(new Color(173, 216, 230));
        separator3.setLocation(0, 320);
        separator3.setSize(110, 4);
        Border lineBorder2 = BorderFactory.createLineBorder(new Color(173, 216, 230), 2);
        separator3.setBorder(lineBorder2);
        quickMenu.add(separator3);
        //////Car Race Builder//////
        // Car Race label
        quickMenu.add(lblCarRace);
        lblCarRace.setLocation(25, 328);
        lblCarRace.setSize(100, 20);
        lblCarRace.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblCarRace.setForeground(new Color(99, 98, 108));
        //Racers label
        JLabel lblRacersNumber = new JLabel("Racers Number:");
        quickMenu.add(lblRacersNumber);
        lblRacersNumber.setLocation(5, 350);
        lblRacersNumber.setSize(100, 20);
        // Number Text field
        cmbLandRace = new JComboBox<String>(LAND_RACERS);
        quickMenu.add(cmbLandRace);
        cmbLandRace.setLocation(5, 370);
        cmbLandRace.setSize(100, 25);
        // Race Builder button
        JButton BuilderBtn = new JButton("Build Race");
        BuilderBtn.setLocation(2, 405);
        BuilderBtn.setSize(103, 30);
        BuilderBtn.addActionListener(this);
        quickMenu.add(BuilderBtn);

        quickMenu.setMaximumSize(quickMenu.getPreferredSize());
        return quickMenu;
    }

    public DisplayPanel getDisplayPanel() {
        lblTHERACE = new JLabel("THE RACE");
        displayPanel = new DisplayPanel();
        displayPanel.setLayout(null);
        displayPanel.setPreferredSize(new Dimension(1060, arenaHeight));
        // Add border around the display panel.
        displayPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        // Add "THE RACE" to the display panel.
        displayPanel.add(lblTHERACE);
        lblTHERACE.setFont(new Font("SansSerif", Font.BOLD, 80));
        lblTHERACE.setLocation(330, 170);
        lblTHERACE.setSize(500, 300);
        return displayPanel;
    }

    public JPanel getMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(getDisplayPanel(), BorderLayout.WEST);
        mainPanel.add(getQuickMenuPanel(), BorderLayout.CENTER);
        mainPanel.add(getControlsPanel(), BorderLayout.EAST);
        return mainPanel;
    }

    ///Assm 3.
    // Method to update the racer names in the cmbPrototype combo box
    private void updateRacerNames() {
        if (racers == null || racers.isEmpty()) {
            cmbPrototype.setModel(new DefaultComboBoxModel<>(new String[]{"No Racers."}));
        } else {
            cmbPrototype.setModel(new DefaultComboBoxModel<>(getRacerNames()));
        }
    }

    // Function to get the racer names dynamically
    private String[] getRacerNames() {
        String[] racerNames = new String[racers.size()];
        for (int i = 0; i < racers.size(); i++) {
            racerNames[i] = racers.get(i).getRacerName();
        }
        return racerNames;
    }

    private void changeCmbColor() {
        Racer selectedRacer = null;
        for (Racer racer : racers) {
            if (racer.getRacerName().equals(cmbPrototype.getSelectedItem().toString())) {
                selectedRacer = racer;
                break;
            }
        }
        if (selectedRacer != null) {
            String color = selectedRacer.getColor().toString();
            String formattedColor = Character.toUpperCase(color.charAt(0)) + color.substring(1).toLowerCase();
            cmbQuickColor.setSelectedItem(formattedColor);
        }
        previousPrototypeIndex = cmbPrototype.getSelectedIndex();
    }

    // Buttons clicked
    public void actionPerformed(ActionEvent e) {
        // Check if cmbPrototype selection changed
        if (e.getSource() == cmbPrototype && cmbPrototype.getSelectedIndex() != previousPrototypeIndex) {
            changeCmbColor();
        } else {
            // Message if clicked any button before build the Arena.
            if (arena == null && !e.getActionCommand().equals("Build Arena") && !e.getActionCommand().equals("Quick Arena")
                    && !e.getActionCommand().equals("Build Race")
                    || raceFinished && e.getActionCommand().equals("Start Race")
                    || raceFinished && e.getActionCommand().equals("Add Racer")) {
                JOptionPane.showMessageDialog(this, "Please build Arena first and add racers!");
                return;
            }
            switch (e.getActionCommand()) {
                // Button Build Arena
                case "Build Arena":
                    addArena();
                    break;
                // Button Add Racer
                case "Add Racer":
                    addRacer();
                    break;
                // Button Start Race
                case "Start Race":
                    startRace();
                    break;
                case "Show Info":
                    showInfo();
                    break;
                case "Prototype":
                    prototypeRacer();
                    break;
                case "Quick Arena":
                    quickArena();
                    break;
                case "Build Race":
                    carRaceBuilder();
                    break;
            }
        }

    }

    // Buttons functions.
    //Assm 3
    public void quickArena() {
        if(arena != null && arena.getActiveRacers().size() == 0) {
            raceStarted = false;
            raceFinished = true;
        }
        if (raceStarted && !raceFinished) {
            JOptionPane.showMessageDialog(this, "Race started! Please wait.");
            return;
        }
        String quickArena = (String) cmbArenaFactory.getSelectedItem();
        ArenaFactory arenaFactory = new ArenaFactory();
        switch (quickArena) {
            case "Air":
                arenaName = "AerialArena";
                arena = arenaFactory.getArena("Air");
                break;
            case "Land":
                arenaName = "LandArena";
                arena = arenaFactory.getArena("Land");
                break;
            case "Navy":
                arenaName = "NavalArena";
                arena = arenaFactory.getArena("Navy");
                break;
        }
        // Reset and initialize the Arena Parameters
        Racer.setSerial(1);
        arenaLength = (int) arena.getLength();
        maxRacers = arena.getMAX_RACERS();
        racersNumber = 0;
        raceStarted = raceFinished = false;
        racers = new ArrayList<>();
        racersImages = new Image[maxRacers];
        displayPanel.racerImages = racersImages;
        displayPanel.setRacerImages();
        //Set a new height
        if (maxRacers > 8) {
            this.arenaHeight = 700 + (maxRacers - 8) * 6;
        } else {
            this.arenaHeight = 700;
        }
        // Set Arena image
        arenaImage = new ImageIcon("icons/" + arenaName + ".jpg").getImage();
        int displayPanelWidth = Math.min(arenaLength + 60, 1290); // Limit width to 1300
        displayPanel.setPreferredSize(new Dimension(displayPanelWidth, arenaHeight));
        displayPanel.setBackgroundImage(arenaImage);
        int frameWidth = Math.min(arenaLength + 322, 1600); // Limit width to 1300
        this.setSize(frameWidth, arenaHeight);
        // Make the controlPanel not resizable with the frame.
        controlsPanel.setPreferredSize(new Dimension(140, arenaHeight));
        controlsPanel.setLayout(new BorderLayout());
        //center screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        x = (screenSize.width - getWidth()) / 2;
        y = (screenSize.height - getHeight()) / 2;
        this.setLocation(x, y);
        updateRacerNames();
        lblTHERACE.setText("");
        lblArenaLength.setText("Length: " + arenaLength);
        lblArenaMaxRacers.setText("Max Racers: " + maxRacers);
    }

    public void prototypeRacer() {
        if(arena != null && arena.getActiveRacers().size() == 0 && raceStarted == true) {
            raceStarted = false;
            raceFinished = true;
        }
        // Message if Race started OR race Finished OR no more place for adding racers.
        if (raceFinished) {
            JOptionPane.showMessageDialog(this, "Race finished! Please build a new arena.");
            return;
        }
        if (raceStarted) {
            JOptionPane.showMessageDialog(this, "Race started! No racers can be added.");
            return;
        }
        if (racersNumber == maxRacers) {
            JOptionPane.showMessageDialog(this, "No more racers can be added!");
            return;
        }
        if (racersNumber == 0) {// Cant press if there are no Racers in the Arena.
            JOptionPane.showMessageDialog(this, "Please build arena first and add racers!");
            return;
        }

        Racer selectedRacer = null;
        for (Racer racer : racers) {
            if (racer.getRacerName().equals(cmbPrototype.getSelectedItem().toString())) {
                selectedRacer = racer;
                break;
            }
        }
        int selectedRacerSN = selectedRacer.getSerialNumber();
        Racer clonedRacer = (Racer) racerCache.getRacer("" + selectedRacerSN);
        String col = (String) cmbQuickColor.getSelectedItem();
        EnumContainer.Color colEnum = EnumContainer.Color.valueOf(col.toUpperCase());
        clonedRacer.setColor(colEnum);
        clonedRacer.setSerialNumber(Racer.getSerial());
        String newName = selectedRacer.getRacerName() + clonedRacer.getSerialNumber();
        Racer.setSerial(Racer.getSerial() + 1);
        clonedRacer.setRacerName(newName);
        try {
            arena.addRacer(clonedRacer);
        } catch (RacerLimitException e) {
            throw new RuntimeException(e);
        } catch (RacerTypeException e) {
            throw new RuntimeException(e);
        }
        racers.add(clonedRacer);
        clonedRacer.register(displayPanel);
        clonedRacer.setImgIndex(racersNumber);
        racersImages[racersNumber] = new ImageIcon("icons/" + clonedRacer.className() + colEnum + ".png").getImage();

        // Resize the racersImages array if necessary
        if (racersNumber >= racersImages.length) {
            Image[] resizedArray = new Image[racersNumber + 1];
            System.arraycopy(racersImages, 0, resizedArray, 0, racersImages.length);
            racersImages = resizedArray;
        }
        arena.initRace();
        racersNumber++;
        displayPanel.racerImages = racersImages;
        displayPanel.setRacerImages();
        updateRacerNames();
        changeCmbColor();
        //add the new racer to the hash table.
        racerCache.getRacerMap().put("" + clonedRacer.getSerialNumber(), clonedRacer);
    }

    public void carRaceBuilder() {
        if(arena != null && arena.getActiveRacers().size() == 0) {
            raceStarted = false;
            raceFinished = true;
        }
        if (raceStarted && !raceFinished) {
            JOptionPane.showMessageDialog(this, "Race started! Please wait.");
            return;
        }
        racersNumber = cmbLandRace.getSelectedIndex() + 1;
        Racer.setSerial(1);
        QuickRaceBuilder carRaceBuilder = new LandRaceBuilder(racersNumber);
        Engineer engineer = new Engineer(carRaceBuilder);
        engineer.constructRace();
        Race race = engineer.getRace();
        //Build the arena
       arena = race.getArena();
       arenaName = "LandArena";
       //Reset and initialize the Arena Parameters
        arenaLength = (int) arena.getLength();
        maxRacers = arena.getMAX_RACERS();
        raceStarted = raceFinished = false;
        displayPanel.racerImages = racersImages;
        displayPanel.setRacerImages();
        //Set a new height
        if (maxRacers > 8) {
            this.arenaHeight = 700 + (maxRacers - 8) * 6;
        } else {
            this.arenaHeight = 700;
        }
        // Set Arena image
        arenaImage = race.getArenaImage();
        int displayPanelWidth = Math.min(arenaLength + 60, 1290); // Limit width to 1300
        displayPanel.setPreferredSize(new Dimension(displayPanelWidth, arenaHeight));
        displayPanel.setBackgroundImage(arenaImage);
        int frameWidth = Math.min(arenaLength + 322, 1600); // Limit width to 1300
        this.setSize(frameWidth, arenaHeight);
        // Make the controlPanel not resizable with the frame.
        controlsPanel.setPreferredSize(new Dimension(140, arenaHeight));
        controlsPanel.setLayout(new BorderLayout());
        //center screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        x = (screenSize.width - getWidth()) / 2;
        y = (screenSize.height - getHeight()) / 2;
        this.setLocation(x, y);
        updateRacerNames();
        lblTHERACE.setText("");

        // Build the Racers
       racers = race.getRacers();
       racersImages = race.getRacersImages();
        for (int i = 0; i < racersNumber; i++) {
            try {
                racers.get(i).register(displayPanel);
                racers.get(i).setImgIndex(i);
            } catch (Exception ex) {
                System.out.println(ex);
                return;
            }
        }
        displayPanel.racerImages = racersImages;
        displayPanel.setRacerImages();
        updateRacerNames();
        changeCmbColor();
    }

    public void addArena() {
        if(arena != null && arena.getActiveRacers().size() == 0 ) {
            raceStarted = false;
            raceFinished = true;
        }
        if (raceStarted && !raceFinished) {
            JOptionPane.showMessageDialog(this, "Race started! Please wait.");
            return;
        }
        try {
            arenaLength = Integer.parseInt(tfArenaLength.getText());
            maxRacers = Integer.parseInt(tfMaxRacers.getText());
            if (arenaLength < 100 || arenaLength > 3000 || maxRacers <= 0 || maxRacers > 20)
                throw new Exception();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input values! Please try again.");
            return;
        }
        lblTHERACE.setText("");
        // Set a new Arena
        arenaName = (String) cmbArena.getSelectedItem();
        try {
            switch (arenaName) {
                case "AerialArena":
                    arena = builder.buildArena("game.arenas.air.AerialArena", arenaLength, maxRacers);
                    break;
                case "LandArena":
                    arena = builder.buildArena("game.arenas.land.LandArena", arenaLength, maxRacers);
                    break;
                case "NavalArena":
                    arena = builder.buildArena("game.arenas.naval.NavalArena", arenaLength, maxRacers);
                    break;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        // Reset and initialize the Arena Parameters
        racersNumber = 0;
        Racer.setSerial(1);
        raceStarted = raceFinished = false;
        racers = new ArrayList<>();
        racersImages = new Image[maxRacers];
        displayPanel.racerImages = racersImages;
        displayPanel.setRacerImages();
        //Set a new height
        if (maxRacers > 8) {
            this.arenaHeight = 700 + (maxRacers - 8) * 6;
        } else {
            this.arenaHeight = 700;
        }
        // Set Arena image
        arenaImage = new ImageIcon("icons/" + arenaName + ".jpg").getImage();
        int displayPanelWidth = Math.min(arenaLength + 60, 1290); // Limit width to 1300
        displayPanel.setPreferredSize(new Dimension(displayPanelWidth, arenaHeight));
        displayPanel.setBackgroundImage(arenaImage);
        int frameWidth = Math.min(arenaLength + 322, 1600); // Limit width to 1300
        this.setSize(frameWidth, arenaHeight);
        // Make the controlPanel not resizable with the frame.
        controlsPanel.setPreferredSize(new Dimension(140, arenaHeight));
        controlsPanel.setLayout(new BorderLayout());
        //center screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        x = (screenSize.width - getWidth()) / 2;
        y = (screenSize.height - getHeight()) / 2;
        this.setLocation(x, y);
        updateRacerNames();
        cmbQuickColor.setSelectedIndex(0);
    }

    public void addRacer() {

        if(arena != null && arena.getActiveRacers().size() == 0) {
            raceStarted = false;
            raceFinished = true;
        }

        if(arena.getCompletedRacers().size() == 0){
            raceFinished = false;
        }

        // Message if Race started OR race Finished OR no more place for adding racers.
        if (raceFinished) {
            JOptionPane.showMessageDialog(this, "Race finished! Please build a new arena.");
            return;
        }
        if (raceStarted) {
            JOptionPane.showMessageDialog(this, "Race started! No racers can be added.");
            return;
        }
        if (racersNumber == maxRacers) {
            JOptionPane.showMessageDialog(this, "No more racers can be added!");
            return;
        }
        // Racer parameters.
        String racerName;
        double maxSpeed;
        double acceleration;
        int WheelsNum = 0;
        try {
            racerName = tfRacerName.getText();
            maxSpeed = Integer.parseInt(tfMaxSpeed.getText());
            acceleration = Integer.parseInt(tfAcceleration.getText());
            if (racerName.isEmpty() || maxSpeed <= 0 || acceleration <= 0)
                throw new Exception();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input values! Please try again.");
            return;
        }
        // Prepare the Racer class
        String racerType = (String) cmbRacer.getSelectedItem();
        String color = (String) cmbColor.getSelectedItem();
        String racerClass = null;
        EnumContainer.Color colEnum = null;
        // Racer Class
        switch (racerType) {
            case "Airplane":
                WheelsNum = 3;
                racerClass = "game.racers.air.Airplane";
                break;
            case "Helicopter":
                racerClass = "game.racers.air.Helicopter";
                break;
            case "Bicycle":
                WheelsNum = 2;
                racerClass = "game.racers.land.Bicycle";
                break;
            case "Car":
                WheelsNum = 4;
                racerClass = "game.racers.land.Car";
                break;
            case "Horse":
                racerClass = "game.racers.land.Horse";
                break;
            case "RowBoat":
                racerClass = "game.racers.naval.RowBoat";
                break;
            case "SpeedBoat":
                racerClass = "game.racers.naval.SpeedBoat";
                break;
        }
        // Racer Color
        colEnum = EnumContainer.Color.valueOf(color.toUpperCase());
        // Build the Racer class.
        Racer racer = null;
        try {
            // Check if racer has wheel and send it to the current builder.
            if (WheelsNum == 0)
                racer = builder.buildRacer(racerClass, racerName, maxSpeed, acceleration, colEnum);
            else
                racer = builder.buildWheeledRacer(racerClass, racerName, maxSpeed, acceleration, colEnum, WheelsNum);
            arena.addRacer(racer);
            racers.add(racer);
            racer.register(displayPanel);
            racer.setImgIndex(racersNumber);
        } catch (RacerTypeException ex) {
            Racer.setSerial(Racer.getSerial() - 1);
            JOptionPane.showMessageDialog(this, "Racer does not fit to arena! Choose another racer.");
            return;
        } catch (Exception ex) {
            System.out.println(ex);
            return;
        }
        racersImages[racersNumber] = new ImageIcon("icons/" + racerType + color + ".png").getImage();
        racersNumber++;
        arena.initRace();
        displayPanel.racerImages = racersImages;
        displayPanel.setRacerImages();
        tfRacerName.setText("");
        tfMaxSpeed.setText("");
        tfAcceleration.setText("");

        //Assm3
        //add the new racer to the hash table.
        racerCache.getRacerMap().put("" + racer.getSerialNumber(), racer);

        updateRacerNames();
        changeCmbColor();
    }

    public void startRace() {
        if (table != null && table.isVisible())
            showInfo();
        if (racersNumber == 0) {// Cant press if there are no Racers in the Arena.
            JOptionPane.showMessageDialog(this, "Please build arena first and add racers!");
            return;
        }// Cant press if Race finished.
        if (raceFinished) {
            JOptionPane.showMessageDialog(this, "Race finished! Please build a new arena and add racers.");
            return;
        }// Cant press if Race started.
        if (raceStarted) {
            JOptionPane.showMessageDialog(this, "Race already started!");
            return;
        }
        try {
            raceStarted = true;
            arena.playTurn();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        displayPanel.update();
        displayPanel.update();
    }

    public void showInfo() {
        if (arena == null || racersNumber == 0) {// Cant press if there are no Racers in the Arena.
            JOptionPane.showMessageDialog(this, "Please build arena first and add racers!");
            return;
        }
        String[] columnNames = {"Image", "Position", "Racer name", "Current speed", "Max speed",
                "Current X location", "Finished", "Time" , "Status"};
        DefaultTableModel model = new DefaultTableModel(new Object[racersNumber][9], columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return ImageIcon.class; // Set the column class to ImageIcon for displaying images
                } else {
                    return super.getColumnClass(columnIndex);
                }
            }
        };

        table = new JTable(model);
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        table.setPreferredScrollableViewportSize(new Dimension(numCols * 78, numRows * 30));
        for (int i = 1; i < numCols; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(85);
        }
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(50); // Set width for the image column
        table.getColumnModel().getColumn(1).setPreferredWidth(55); // Set width for the place column
        // Update the existing table model
        table.setModel(model);
        table.setDefaultRenderer(ImageIcon.class, new ImageRenderer()); // Set custom renderer for the image column
        table.setEnabled(false); // Disable table editing
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        if (infoTable != null) {
            infoTable.dispose();
        }
        infoTable = new JFrame("Racers Information");
        infoTable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        infoTable.setLayout(new BorderLayout());
        infoTable.add(panel, BorderLayout.CENTER);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - table.getPreferredSize().width) / 2 - 100;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - table.getPreferredSize().height) / 2;
        infoTable.setLocation(x, y);
        infoTable.setSize(table.getPreferredSize().width + 50, table.getPreferredSize().height + 80);
        infoTable.setVisible(true);

        // Update the display panel
        if (table != null)
            displayPanel.update();
        // Create a Timer to periodically update the table
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPanel.update(); // Update the table periodically
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    private class ImageRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            if (value instanceof ImageIcon) {
                label.setIcon((ImageIcon) value);
                label.setText(null); // Clear the text
            }
            return label;
        }
    }

    //Display claas that display the race.
    public class DisplayPanel extends JPanel implements Observer {
        private Image Arimage;
        private Image[] racerImages;

        //Arena Image change
        public void setBackgroundImage(Image image) {
            Arimage = image;
            repaint();
        }

        public void setRacerImages() {
            repaint();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponents(g);
            g.drawImage(Arimage, 0, 0, displayPanel.getWidth(), displayPanel.getHeight(), this);
            if (Arimage != null)
                g.drawImage(finishLineImg, displayPanel.getWidth() - 15, 0, 8, displayPanel.getHeight(), this);
            if (racerImages != null) {
                for (int i = 0; i < racersNumber; i++) {
                    if (racers.get(i).getCurrentLocation() != null)
                        g.drawImage(racerImages[i], (int) racers.get(i).getCurrentLocation().getX() + 10,
                                36 * i, 45, 45, this);
                }
            }
        }
        private double parseTotalTime(String time) {
            try {
                return Double.parseDouble(time.replace(" sec.", ""));
            } catch (NumberFormatException e) {
                return 0.0; // Or any appropriate default value
            }
        }

        @Override
        public void update() {
            if (table != null) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();

                ArrayList<Racer> sortedRacers = new ArrayList<>();
                ArrayList<Racer> completedRacers = arena.getCompletedRacers();
                ArrayList<Racer> activeRacers = new ArrayList<>(arena.getActiveRacers());
                ArrayList<Racer> invalidRacers = new ArrayList<>(arena.getInvalidRacers());
                // Add completed racers to the sorted list in their current order
                sortedRacers.addAll(completedRacers);
                // Add active racers to the sorted list
                sortedRacers.addAll(activeRacers);
                sortedRacers.addAll(invalidRacers);

                Collections.sort(sortedRacers, (racer1, racer2) -> {
                    // Check if racer1 is invalid
                    boolean isRacer1Invalid = invalidRacers.contains(racer1);
                    // Check if racer2 is invalid
                    boolean isRacer2Invalid = invalidRacers.contains(racer2);
                    if (isRacer1Invalid && isRacer2Invalid) {
                        // Both racers are invalid, maintain their order
                        return 0;
                    } else if (isRacer1Invalid) {
                        // Only racer1 is invalid, racer2 comes before
                        return 1;
                    } else if (isRacer2Invalid) {
                        // Only racer2 is invalid, racer1 comes before
                        return -1;
                    } else {
                        // Both racers are valid, compare their total times in ascending order
                        String time1 = racer1.getTotalTime();
                        String time2 = racer2.getTotalTime();
                        if (!time1.isEmpty() && !time2.isEmpty()) {
                            double totalTime1 = parseTotalTime(time1);
                            double totalTime2 = parseTotalTime(time2);
                            return Double.compare(totalTime1, totalTime2); // Compare in ascending order
                        } else if (time1.isEmpty() && time2.isEmpty()) {
                            return 0;
                        } else if (time1.isEmpty()) {
                            return -1; // racer1 comes before racer2
                        } else {
                            return 1; // racer1 comes after racer2
                        }
                    }
                });

                for (int i = 0; i < racersNumber; i++) {
                    Racer racer = sortedRacers.get(i);
                    int imgIndex = racer.getImgIndex();
                    int imageWidth = 30;
                    int originalWidth = racersImages[imgIndex].getWidth(null);
                    int originalHeight = racersImages[imgIndex].getHeight(null);
                    int newHeight = originalHeight * imageWidth / originalWidth;
                    BufferedImage resizedImage = new BufferedImage(imageWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2 = resizedImage.createGraphics();
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2.drawImage(racersImages[imgIndex], 0, 0, imageWidth, newHeight, null);
                    g2.dispose();

                    // Set the racer's place only if the state context is "Active" or "Completed"
                    String status = racer.getStateContext().alert();
                    String placeText = "-";
                    if ("Active".equals(status) || "Completed".equals(status)) {
                        placeText = arena.getPlace(racer.getRacerName());
                    }
                    model.setValueAt(placeText, i, 1); // Place
                    model.setValueAt(racer.getCurrentLocation().getX(), i, 5);
                    if (resizedImage != null)
                        model.setValueAt(new ImageIcon(resizedImage), i, 0); // Image

                    model.setValueAt(racer.getRacerName(), i, 2); // Racer name
                    model.setValueAt(racer.getCurrentSpeed(), i, 3); // Current speed
                    model.setValueAt(racer.getMaxSpeed(), i, 4); // Max speed
                    if (racer.getIsFinish() != null)
                        model.setValueAt(racer.getIsFinish(), i, 6); // Finished
                    // Set the "Status" cell text color based on the value
                    if ("Invalid".equals(status)) {
                        model.setValueAt("<html><font color='red'><b>" + status + "</b></font></html>", i, 8);
                    } else if ("Completed".equals(status)) {
                        model.setValueAt("<html><font color='green'><b>" + status + "</b></font></html>", i, 8);  }
                    else if ("Active".equals(status)) {
                        model.setValueAt("<html><font color='blue'><b>" + status + "</b></font></html>", i, 8);
                    }
                    else {
                        model.setValueAt("<html><font color='black'><b>" + status + "</b></font></html>", i, 8);
                    }
                    model.setValueAt(racer.getTotalTime(), i, 7); // Finish time
                }
                // Repaint the table to reflect the changes
                table.repaint();
            }
        }
    }
}
