package bee_simulator;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GardenController {

    private ImageView beeImage;             // image to draw on the panel; not a good domain class name!
    private double beeXLocation, beeYLocation;  // drawn location of bee; this should be in a domain class

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
        beeImage = new ImageView(new Image("file:bee-1.jpg"));
        beeImage = new ImageView(new Image("file:garden_jpgs/bee-1.jpg"));
        beeImage.setPreserveRatio(true);    // ensure ratio preserved when scaling the bee
        beeImage.setFitWidth(50.0);         // scale bee to be a reasonable size
        beeXLocation = beeYLocation = 400;                  // initial location of bee; for your solution, capture this in an object
        theGarden.getChildren().add(beeImage); // place bee on the panel
        displayBee();

        //Creates an array list of Abstract flowers
        ArrayList<AbstractFlower> flowers = new ArrayList<>();

        //Adds a GoodFlower to flowers
        flowers.add(new GoodFlower(10, true));

        //Displays each flower in flowers
        for(AbstractFlower flower : flowers) {
            flower.getFlowerImage().setPreserveRatio(true);
            flower.getFlowerImage().setFitWidth(50.0);
            theGarden.getChildren().add(flower.getFlowerImage());
            displayFlower(flower);
        }

        theGarden.setFocusTraversable(true); // ensure garden pane will receive keypresses
    }

    // display the bee at the (beeXLocation, beeYLocation), ensuring the bee does not leave the garden
    private void displayBee() {
        if ( beeXLocation < 0 )
            beeXLocation = 0;
        else if (theGarden.getWidth() > 0 && beeXLocation > theGarden.getWidth() - 10)
            // note: getWidth() is 0 when first load the scene, so don't relocate the bee in that case
            beeXLocation = theGarden.getWidth() - 10;
        if (beeYLocation < 0)
            beeYLocation = 0;
        else if (theGarden.getHeight() > 0 && beeYLocation > theGarden.getHeight() - 10)
            beeYLocation = theGarden.getHeight() - 10;
        beeImage.setLayoutX(beeXLocation);
        beeImage.setLayoutY(beeYLocation);
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
            beeXLocation += 10.0;
        } else if (keyEvent.getCode() == KeyCode.LEFT) {
            beeXLocation -= 10.0;
        } else if (keyEvent.getCode() == KeyCode.DOWN) {
            beeYLocation += 10.0;
        } else if (keyEvent.getCode() == KeyCode.UP) {
            beeYLocation -= 10.0;
        }
        displayBee();
    }
}
