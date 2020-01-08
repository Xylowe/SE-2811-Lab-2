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
    private double energy;
    private boolean dead;
    private ImageView beeImage;

    /**
     * Constructor for the abstract bee
     *
     * @param xLocation starting x location of the bee
     * @param yLocation starting y location of the bee
     */
    public AbstractBee(double xLocation, double yLocation) {
        dead = false;
        energy = MAX_ENERGY;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
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
}
