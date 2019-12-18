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
 * Bee that goes directly to the flower
 */
public class StraightToFlowerBee extends AbstractBee {
    private AbstractFlower flower;

    /**
     * Constructor for the bee that flies directly to the flower
     *
     * @param xLocation starting x location of the bee
     * @param yLocation starting y location of the bee
     */
    public StraightToFlowerBee(double xLocation, double yLocation) {
        super(xLocation, yLocation);
        super.getBeeImage().setImage(new Image("file:bee-1.jpg"));
    }

    /**
     * A single time unit progressed and the bees locations have been updated.
     */
    public void timeProgressed() {
        //todo
        xLocation+=10;
        yLocation+=10;
    }

    /**
     * Updates the bee's target flower
     *
     * @param flower the flower to target
     */
    public void targetFlower(AbstractFlower flower) {
        this.flower = flower;
    }
}
