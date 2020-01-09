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
 * Bee that randomly chooses directions to travel for a random number of ticks
 */
public class SearchGridBee extends AbstractBee {
    private int direction;
    private int tickCount;

    /**
     * Constructor for the bee that flies back and forth searching for flowers
     */
    public SearchGridBee() {
        super();
        super.getBeeImage().setImage(new Image("file:garden_jpgs\\bee-2.jpg"));
        direction = -1;
        tickCount = 0;
    }

    /**
     * A single time unit progressed and the bees locations have been updated.
     */
    public void timeProgressed() {
        // Do nothing if the bee is dead
        if (isDead()) {
            return;
        }
        // initializes direction
        if (tickCount == 0) {
            direction = (int) (Math.random() * 4);
            tickCount = (int) (Math.random() * 25) + 1;
        }

        //todo check flowers to see if on one and stop

        // moves the bee in the specified direction
        switch (direction) {
            case 0:
                //move right
                xLocation = xLocation + moveDistance;
                break;
            case 1:
                //move down
                yLocation = yLocation + moveDistance;
                break;
            case 2:
                //move left
                xLocation = xLocation - moveDistance;
                break;
            case 3:
                //move up
                yLocation = yLocation - moveDistance;
                break;
        }

        // Force opposite direction if on edge
        if(xLocation < 10 || yLocation < 10) {
            direction = direction - 2;
        } else if(xLocation > GardenController.width - 100 || yLocation > GardenController.height - 90) {
            direction = direction + 2;
        }

        // Loss of energy due to time tick and updating time tick
        addEnergy(-2);
        --tickCount;
    }
}
