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
     */
    public StraightToFlowerBee() {
        super();
        super.getBeeImage().setImage(new Image("file:garden_jpgs\\bee-1.png"));
    }

    /**
     * A single time unit progressed and the bees locations have been updated.
     */
    public void timeProgressed() {
        // Do nothing if the bee is dead
        if (isDead()) {
            return;
        }
        // initializes flower
        int numFlowers = GardenController.flowers.size();
        if (flower == null) {
            flower = GardenController.flowers.get((int) (Math.random() * numFlowers));
        }

        if (getDistance(flower.getXLocation(), flower.getYLocation()) >= moveDistance) {
            // Uses the ratios to calculate and update the locations
            if (Math.abs(yLocation - flower.getYLocation()) == 0) {
                // Edge case where the bee is lined up in the Y direction
                updateLocations(moveDistance, 0);
            } else if (Math.abs(xLocation - flower.getXLocation()) == 0) {
                // Edge case where the bee is lined up in the X direction
                updateLocations(0, moveDistance);
            } else {
                // The normal cases, ratios are created to help calculate the distance
                double ratio = Math.abs(xLocation - flower.getXLocation()) /
                        (Math.abs(yLocation - flower.getYLocation()) +
                                Math.abs(xLocation - flower.getXLocation()));
                updateLocations((moveDistance * ratio), (moveDistance * (1 - ratio)));
            }
        } else {
            // Resets the flower if the current flower is out of nectar
            if (!flower.hasNectar()) {
                flower = null;
            }
        }
        // Loss of energy due to time tick
        addEnergy(-2);
    }

    /**
     * Updates the bees location
     *
     * @param xMove - addition to x dimension
     * @param yMove - addition to y dimension
     */
    private void updateLocations(double xMove, double yMove) {
        if (xLocation > flower.getXLocation()) {
            xLocation = xLocation - xMove;
        } else {
            xLocation = xLocation + xMove;
        }

        if (yLocation > flower.getYLocation()) {
            yLocation = yLocation - yMove;
        } else {
            yLocation = yLocation + yMove;
        }
    }
}
