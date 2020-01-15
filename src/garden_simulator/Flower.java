/*
 * Course: SE2811
 * Winter 2019-2020
 * Lab 4 - The Flowers and the Bees
 * Name: Trenton Bowser and Matt Aleck
 * Created 12/12/2019
 */
package garden_simulator;

/**
 * Interface containing all Flower function headers
 */
public interface Flower {

    /**
     * Returns the current x location of the flower
     *
     * @return x location of the flower
     */
    public double getXLocation();

    /**
     * Returns the current y location of the flower
     *
     * @return y location of the flower
     */
    public double getYLocation();

    /**
     * Status of the flowers nectar
     *
     * @return true if the flower has nectar
     */
    public boolean hasNectar();
}
