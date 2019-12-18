package garden_simulator;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GardenController {

    private ArrayList<AbstractBee> bees;
    protected static ArrayList<AbstractFlower> flowers;

    @FXML
    private Pane theGarden;                 // capture the pane we are drawing on from JavaFX

    @FXML
    public void initialize() {              // executed after scene is loaded but before any methods
        // for fun, set up a gradient background; could probably do in SceneBuilder as well
        // note the label has a Z index of 2 so it is drawn above the panel, otherwise it may be displayed "under" the panel and not be visible
        theGarden.setStyle("-fx-background-color: linear-gradient(to bottom right, derive(forestgreen, 20%), derive(forestgreen, -40%));");
        // load image from a file; the file needs to be in the top folder of the project
        theGarden.setPrefWidth(900);
        theGarden.setPrefHeight(700);

        //Creates an array list of Abstract flowers and bees
        flowers = new ArrayList<>();
        bees = new ArrayList<>();

        //Adds a GoodFlower to flowers
        flowers.add(new GoodFlower(10, true));

        //Displays each flower in flowers
        for(AbstractFlower flower : flowers) {
            flower.getFlowerImage().setPreserveRatio(true);
            flower.getFlowerImage().setFitWidth(50.0);
            theGarden.getChildren().add(flower.getFlowerImage());
            displayFlower(flower);
        }

        //Adds bees to the bee list
        bees.add(new StraightToFlowerBee(100, 100));
        bees.add(new SearchGridBee(200, 200));

        // Sets up and displays all bees
        for(AbstractBee bee : bees) {
            bee.getBeeImage().setPreserveRatio(true);   // ensure ratio preserved when scaling the bee
            bee.getBeeImage().setFitWidth(50.0);        // scale bee to be a reasonable size
            theGarden.getChildren().add(bee.getBeeImage()); // place bee on the panel
            displayBee(bee);
        }

        theGarden.setFocusTraversable(true); // ensure garden pane will receive keypresses
    }

    // display the bee at the (beeXLocation, beeYLocation), ensuring the bee does not leave the garden
    private void displayBee(AbstractBee bee) {
        /*if ( beeXLocation < 0 )
            beeXLocation = 0;
        else if (theGarden.getWidth() > 0 && beeXLocation > theGarden.getWidth() - 10)
            // note: getWidth() is 0 when first load the scene, so don't relocate the bee in that case
            beeXLocation = theGarden.getWidth() - 10;
        if (beeYLocation < 0)
            beeYLocation = 0;
        else if (theGarden.getHeight() > 0 && beeYLocation > theGarden.getHeight() - 10)
            beeYLocation = theGarden.getHeight() - 10;
         */
        bee.getBeeImage().setLayoutX(bee.getXLocation());
        bee.getBeeImage().setLayoutY(bee.getYLocation());
    }

    private void displayFlower(AbstractFlower flower) {
        flower.getFlowerImage().setLayoutX(flower.getXLocation());
        flower.getFlowerImage().setLayoutY(flower.getYLocation());
    }

    //When Right arrow is pushed time advances i.e. the bees move
    //So every bee in bees invokes move() or it's equivalent

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            for (AbstractBee bee: bees) {
                bee.timeProgressed();
                displayBee(bee);
            }
        }
    }
}
