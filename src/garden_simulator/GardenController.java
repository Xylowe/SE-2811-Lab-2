/*
 * Course: SE2811
 * Winter 2019-2020
 * Lab 2 - The Flowers and the Bees
 * Name: Trenton Bowser and Matt Aleck
 * Created 12/12/2019
 */
package garden_simulator;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
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
    private static final int TOTAL_FLOWERS = 80;
    private static final int TOTAL_BEES = 20;

    @FXML
    private Pane theGarden;                 // capture the pane we are drawing on from JavaFX
    @FXML
    private ImageView straightBeeImage;
    @FXML
    private ImageView gridBeeImage;
    @FXML
    private ImageView goodFlowerImage;
    @FXML
    private ImageView badFlowerImage;

    /**
     * Initializes the window
     */
    @FXML
    public void initialize() {              // executed after scene is loaded but before any methods

        // Initializes the key images
        straightBeeImage.setImage(new Image("file:garden_pngs/bee-1.png"));
        gridBeeImage.setImage(new Image("file:garden_pngs/bee-2.png"));
        goodFlowerImage.setImage(new Image("file:garden_pngs/aster.png"));
        badFlowerImage.setImage(new Image("file:garden_pngs/nightshade.png"));

        //Creates an array list of Abstract flowers and bees
        flowers = new ArrayList<>();
        bees = new ArrayList<>();

        //Adds TOTAL_FLOWERS number of flowers to the flowers list, randomly chosen as Good or Killer
        for(int i = 0; i < TOTAL_FLOWERS; i++) {
            if(zeroOrOne() == 0) {
                flowers.add(new GoodFlower(20, 100));
            } else {
                flowers.add(new KillerFlower(5, 20));
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

        //Displays each flower in flowers
        for (AbstractFlower flower : flowers) {
            flower.getFlowerImage().setPreserveRatio(true);
            flower.getFlowerImage().setFitWidth(50.0);
            theGarden.getChildren().add(flower.getFlowerImage());
            displayFlower(flower);
        }

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

        if(bee.isDead()) {
            bee.getBeeImage().setVisible(false);
        } else {
            bee.getBeeImage().setScaleX(bee.getEnergy() / 100);
            bee.getBeeImage().setScaleY(bee.getEnergy() / 100);
            bee.getBeeImage().setLayoutX(bee.getXLocation());
            bee.getBeeImage().setLayoutY(bee.getYLocation());
        }
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
                System.out.print("Initial Energy: " + bee.getEnergy() + "  \t\t\t");
                bee.timeProgressed();
                collision(bee);
                System.out.print("Energy After Tick: " + bee.getEnergy() + "\n");
                hitBorder();
                displayBee(bee);
            }
            System.out.println("------------------------------------------------------");
        }
    }

    /**
     * Keeps the bees inside of the garden
     */
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

    /**
     * This function determines if the given bee collides with a flower or another bee
     * Then the energies are updated
     *
     * @param bee a bee
     */
    private void collision(AbstractBee bee) {
        double collisionDistance = 16;
        double heightBuffer = 45.0;
        double widthBuffer = 50.0;
        double beeXLocationCenter = bee.getXLocation() + widthBuffer/2;
        double beeYLocationCenter = bee.getYLocation() - heightBuffer/2;

        for(AbstractFlower flower : flowers) {
            double flowerXLocationCenter = flower.getXLocation() + widthBuffer/2;
            double flowerYLocationCenter = flower.getYLocation() - heightBuffer/2;
            double distance = bee.getDistance(beeXLocationCenter, beeYLocationCenter,
                    flowerXLocationCenter, flowerYLocationCenter);
            if(distance < collisionDistance) {
                if(flower.getNectarPool() > 0 && flower.getNectarValue() > 0) {
                    bee.addEnergy(flower.getNectarValue());
                    flower.setNectarPool(flower.getNectarPool() - flower.getNectarValue());
                }else if(flower.getNectarPool() > 0 && flower.getNectarValue() < 0) {
                    bee.addEnergy(flower.getNectarValue());
                    flower.setNectarPool(flower.getNectarPool() + flower.getNectarValue());
                } else {
                    flower.setNectar(false);
                }
            }
        }

        for(AbstractBee b : bees) {
            double bXLocationCenter = b.getXLocation() + widthBuffer/2;
            double bYLocationCenter = b.getYLocation() + heightBuffer/2;
            double distance = b.getDistance(beeXLocationCenter, beeYLocationCenter,
                    bXLocationCenter, bYLocationCenter);
            double sameBeeDistance = b.getDistance(bee.getXLocation(), bee.getYLocation(),
                    b.getXLocation(), b.getYLocation());

            if(distance < collisionDistance && sameBeeDistance != 0.0 && !b.isDead()) {
                b.addEnergy(-2);
                bee.addEnergy(-2);
            }
        }
    }

    /**
     * Returns zero or one randomly
     *
     * @return - zero or one
     */
    private int zeroOrOne() {
        return (int) (Math.random() * 2);
    }
}
