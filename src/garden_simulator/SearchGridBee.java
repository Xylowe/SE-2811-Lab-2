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
    private boolean onFlower;

    /**
     * Constructor for the bee that flies back and forth searching for flowers
     */
    public SearchGridBee() {
        super();
        super.getBeeImage().setImage(new Image("file:garden_jpgs/bee-2.png"));
        direction = -1;
        tickCount = 0;
        onFlower = false;
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

        // check flowers to determine if the bee is at one, if so stop on the flower
        for (AbstractFlower flower : GardenController.flowers) {
            double distance = getDistance(flower.getXLocation(), flower.getYLocation());
            if (distance < moveDistance && flower.hasNectar()) {
                onFlower = true;
            }
        }

        if(!onFlower) {
            // moves the bee in the specified direction when not on flower
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

            // Force opposite direction if on edge for a few ticks
            if (xLocation < 10 || yLocation < 10) {
                direction = direction - 2;
                tickCount = tickCount + 5;
            } else if (xLocation > GardenController.width - 50 || yLocation > GardenController.height - 45) {
                direction = direction + 2;
                tickCount = tickCount + 5;
            }
            --tickCount;
        } else {
            // Bee stays on the flower and takes in nectar, no movement occurs
            onFlower = false;
        }

        // Loss of energy due to time tick
        addEnergy(-1);
    }
}
