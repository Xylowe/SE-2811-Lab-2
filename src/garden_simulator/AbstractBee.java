/*
 * Course: SE2811
 * Winter 2019-2020
 * Lab 2 - The Flowers and the Bees
 * Name: Trenton Bowser and Matt Aleck
 * Created 12/12/2019
 */
package garden_simulator;

import javafx.scene.image.ImageView;

/**
 * Abstract bee that implements the bee interface
 */
public abstract class AbstractBee implements Bee {
    protected double xLocation;
    protected double yLocation;
    protected final double moveDistance = 16;
    private double energy;
    private boolean dead;
    private ImageView beeImage;

    /**
     * Constructor for the abstract bee
     */
    public AbstractBee() {
        dead = false;
        energy = MAX_ENERGY;
        xLocation = Math.random() * (GardenController.width - 50);
        yLocation = Math.random() * (GardenController.height - 45);
        this.beeImage = new ImageView();
    }

    /**
     * Returns the current x location of the bee
     *
     * @return x location of the bee
     */
    public double getXLocation() {
        return xLocation;
    }

    /**
     * Returns the current y location of the bee
     *
     * @return y location of the bee
     */
    public double getYLocation() {
        return yLocation;
    }

    /**
     * Returns the bee ImageView associated with the bee, can be set to the bees image
     *
     * @return the ImageView of the bee
     */
    public ImageView getBeeImage() {
        return beeImage;
    }

    /**
     * A single time unit progressed and the bees locations have been updated.
     */
    public abstract void timeProgressed();

    /**
     * Returns whether or not the bee is alive
     *
     * @return True if the bee is dead, False if the bee is alive
     */
    public boolean isDead() {
        if (energy <= 0) {
            dead = true;
        }
        return dead;
    }

    /**
     * Returns the energy level of the bee
     *
     * @return the bee's energy
     */
    public double getEnergy() {
        return energy;
    }

    /**
     * Adds energy to the bee's energy
     * The added energy can be negative to remove the bees energy
     *
     * @param addAmount amount pf energy to add to the bee
     */
    public void addEnergy(double addAmount) {
        energy = energy + addAmount;
        if (energy > MAX_ENERGY) {
            energy = MAX_ENERGY;
        } else if (energy <= 0) {
            dead = true;
        }
    }

    /**
     * Calculates the Euclidean distance between the bee and the given object coordinates
     *
     * @param x - the objects x coordinate
     * @param y - the objects y coordinate
     * @return the distance between the bee and the object
     */
    public double getDistance(double x, double y) {
        return getDistance(xLocation, yLocation, x, y);
    }

    /**
     * Calculates the Euclidean distance between two coordinates
     *
     * @param x1 - the first object x coordinate
     * @param y1 - the first object y coordinate
     * @param x2 - the second object x coordinate
     * @param y2 - the second object y coordinate
     * @return the distance between the objects
     */
    public double getDistance(double x1, double y1, double x2, double y2) {
        double xDifference = Math.abs(x1 - x2);
        double yDifference = Math.abs(y1 - y2);
        return Math.sqrt((xDifference * xDifference) + (yDifference * yDifference));
    }
}
