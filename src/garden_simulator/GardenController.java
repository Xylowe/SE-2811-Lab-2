/*
 * Course: SE2811
 * Winter 2019-2020
 * Lab 2 - The Flowers and the Bees
 * Name: Trenton Bowser and Matt Aleck
 * Created 12/12/2019
 */
package garden_simulator;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * The controller for the FXML window
 */
public class GardenController {


    //private ImageView beeImage;             // image to draw on the panel; not a good domain class name!
    private double beeXLocation, beeYLocation;  // drawn location of bee; this should be in a domain class
    private ArrayList<AbstractBee> bees;
    protected static ArrayList<AbstractFlower> flowers;
    protected static final int width = 900;
    protected static final int height = 700;
    private static final int TOTAL_FLOWERS = 10;
    private static final int TOTAL_BEES = 4;

    @FXML
    private Pane theGarden;                 // capture the pane we are drawing on from JavaFX

    /**
     * Initializes the window
     */
    @FXML
    public void initialize() {              // executed after scene is loaded but before any methods
        // for fun, set up a gradient background; could probably do in SceneBuilder as well
        // note the label has a Z index of 2 so it is drawn above the panel, otherwise it may be displayed "under" the panel and not be visible
        theGarden.setStyle("-fx-background-color: linear-gradient(to bottom right, derive(forestgreen, 20%), derive(forestgreen, -40%));");
        // load image from a file; the file needs to be in the top folder of the project
        theGarden.setPrefWidth(width);
        theGarden.setPrefHeight(height);

        //Creates an array list of Abstract flowers and bees
        flowers = new ArrayList<>();
        bees = new ArrayList<>();

        //Adds TOTAL_FLOWERS number of flowers to the flowers list, randomly chosen as Good or Killer
        for(int i = 0; i < TOTAL_FLOWERS; i++) {
            if(zeroOrOne() == 0) {
                flowers.add(new GoodFlower(10, 60));
            } else {
                flowers.add(new KillerFlower(10, 30));
            }
        }

        //Adds TOTAL_BEEs number of bees to the bees list, randomly chosen as StraightToFlower or SearchGrid
        for(int i = 0; i < TOTAL_BEES; i++) {
            if(zeroOrOne() == 0) {
                bees.add(new StraightToFlowerBee());
            } else {
                bees.add(new SearchGridBee());
            }
        }

        //Adds a GoodFlower to flowers
        //flowers.add(new GoodFlower(10, 60));

        //Adds a KillerFlower to flowers
        //flowers.add(new KillerFlower(10, 30));

        //Displays each flower in flowers
        for (AbstractFlower flower : flowers) {
            flower.getFlowerImage().setPreserveRatio(true);
            flower.getFlowerImage().setFitWidth(50.0);
            theGarden.getChildren().add(flower.getFlowerImage());
            displayFlower(flower);
        }

        //Adds bees to the bee list
        //bees.add(new StraightToFlowerBee());
        //bees.add(new SearchGridBee());

        // Sets up and displays all bees
        for (AbstractBee bee : bees) {
            bee.getBeeImage().setPreserveRatio(true);   // ensure ratio preserved when scaling the bee
            bee.getBeeImage().setFitWidth(50.0);        // scale bee to be a reasonable size
            theGarden.getChildren().add(bee.getBeeImage()); // place bee on the panel
            displayBee(bee);
        }

        theGarden.setFocusTraversable(true); // ensure garden pane will receive keypresses
    }

    /**
     * Displays the given bee
     *
     * @param bee - bee to display
     */
    private void displayBee(AbstractBee bee) {
        if ( beeXLocation < 0 )
            beeXLocation = 0;
        else if (theGarden.getWidth() > 0 && beeXLocation > theGarden.getWidth() - 10)
            // note: getWidth() is 0 when first load the scene, so don't relocate the bee in that case
            beeXLocation = theGarden.getWidth() - 10;
        if (beeYLocation < 0)
            beeYLocation = 0;
        else if (theGarden.getHeight() > 0 && beeYLocation > theGarden.getHeight() - 10)
            beeYLocation = theGarden.getHeight() - 10;

        bee.getBeeImage().setLayoutX(bee.getXLocation());
        bee.getBeeImage().setLayoutY(bee.getYLocation());
    }

    /**
     * Displays the given flower
     *
     * @param flower - flower to display
     */
    private void displayFlower(AbstractFlower flower) {
        flower.getFlowerImage().setLayoutX(flower.getXLocation());
        flower.getFlowerImage().setLayoutY(flower.getYLocation());
    }

    /**
     * When Right arrow is pushed time advances i.e. the bees move
     *
     * @param keyEvent - the key pressed
     */
    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            for (AbstractBee bee : bees) {
                //bee.timeProgressed();
                bee.xLocation += 10.0;
                collision(bee);
                hitBorder();
                displayBee(bee);
            }
        } else if (keyEvent.getCode() == KeyCode.LEFT) {
            for(AbstractBee bee : bees) {
                bee.xLocation -= 10.0;
                collision(bee);
                hitBorder();
                displayBee(bee);
            }
        } else if (keyEvent.getCode() == KeyCode.DOWN) {
            for(AbstractBee bee : bees) {
                bee.yLocation += 10.0;
                collision(bee);
                hitBorder();
                displayBee(bee);
            }
        } else if (keyEvent.getCode() == KeyCode.UP) {
            for (AbstractBee bee : bees) {
                bee.yLocation -= 10.0;
                collision(bee);
                hitBorder();
                displayBee(bee);
            }
        }
    }

    private void hitBorder() {
        int yMax = 655; //Bee images are 45 high
        int xMax = 850; //Bee images are 50 wide
        int min = 0;
        for(AbstractBee bee : bees) {
            if(bee.getXLocation() >= xMax) {
                bee.xLocation = xMax;
            } else if(bee.getXLocation() <= min) {
                bee.xLocation = min;
            }
            if(bee.getYLocation() >= yMax) {
                bee.yLocation = yMax;
            } else if(bee.getYLocation() <= min) {
                bee.yLocation = min;
            }
        }
    }

    //Bee collides with something
    private void collision(AbstractBee bee) {
        double collisionDistance = 16;
        double heightBuffer = 45.0;
        double widthBuffer = 50.0;
        double beeXLocationCenter = bee.getXLocation() + widthBuffer/2;
        double beeYLocationCenter = bee.getYLocation() - heightBuffer/2;

        for(AbstractFlower flower : flowers) {
            double flowerXLocationCenter = flower.getXLocation() + widthBuffer/2;
            double flowerYLocationCenter = flower.getYLocation() - heightBuffer/2;
            double distance = Math.sqrt(Math.pow((beeXLocationCenter - flowerXLocationCenter), 2) +
                    Math.pow((beeYLocationCenter - flowerYLocationCenter), 2));

            if(distance < collisionDistance) {
                if(flower.getNectarPool() > 0) {
                    bee.addEnergy(flower.getNectarValue());
                    flower.setNectarPool(flower.getNectarPool() - flower.getNectarValue());
                } else {
                    flower.setNectar(false);
                }
            }
        }

        for(AbstractBee b : bees) {
            double bXLocationCenter = b.getXLocation() + widthBuffer/2;
            double bYLocationCenter = b.getYLocation() + heightBuffer/2;
            double distance = Math.sqrt(Math.pow(beeXLocationCenter - bXLocationCenter, 2) +
                    Math.pow(beeYLocationCenter - bYLocationCenter, 2));
            double sameBeeDistance = Math.sqrt(Math.pow(bee.getXLocation() - b.getXLocation(), 2) +
                    Math.pow(bee.getYLocation() - b.getYLocation(), 2));

            System.out.println("Before: " + bee.getEnergy());
            if(distance < collisionDistance && sameBeeDistance != 0.0) {
                b.addEnergy(-2);
                bee.addEnergy(-2);
            }
            System.out.println("After: " + bee.getEnergy() + "\n");
        }
    }

    private int zeroOrOne() {
        return (int) (Math.random() * 2);
    }
}
