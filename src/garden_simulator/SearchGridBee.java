/*
 * Course: SE2811
 * Winter 2019-2020
 * Lab 2 - The Flowers and the Bees
 * Name: Trenton Bowser and Matt Aleck
 * Created 12/12/2019
 */
package garden_simulator;

import javafx.scene.image.Image;

/**
 * Bee that goes back and forth searching for flowers
 */
public class SearchGridBee extends AbstractBee {

    /**
     * Constructor for the bee that flies back and forth searching for flowers
     */
    public SearchGridBee() {
        super();
        super.getBeeImage().setImage(new Image("file:garden_jpgs\\bee-2.jpg"));
    }

    /**
     * A single time unit progressed and the bees locations have been updated.
     */
    public void timeProgressed(){
        //todo
        xLocation+=10;
        yLocation+=-5;
    }
}
