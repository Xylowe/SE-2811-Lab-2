/*
 * Course: SE2811
 * Winter 2019-2020
 * Lab 2 - The Flowers and the Bees
 * Name: Trenton Bowser and Matt Aleck
 * Created 12/12/2019
 */
package garden_simulator;

/**
 * Interface containing all Bee function headers
 */
public interface Bee {
    double MAX_ENERGY = 100.0;

    /**
     * Returns the current x location of the bee
     *
     * @return x location of the bee
     */
    double getXLocation();

    /**
     * Returns the current y location of the bee
     *
     * @return y location of the bee
     */
    double getYLocation();

    /**
     * A single time unit progressed and the bees locations have been updated.
     */
    void timeProgressed();

    /**
     * Returns whether or not the bee is alive
     *
     * @return True if the bee is dead, False if the bee is alive
     */
    boolean isDead();

    /**
     * Returns the energy level of the bee
     *
     * @return the bee's energy
     */
    double getEnergy();

    /**
     * Adds energy to the bee's energy
     * The added energy can be negative to remove the bees energy
     *
     * @param addAmount amount pf energy to add to the bee
     */
    void addEnergy(double addAmount);
}
