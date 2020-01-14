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
 * Flower that will give the bees energy
 */
public class GoodFlower extends AbstractFlower {

    /**
     * Constructor for the flower that gives the bees energy
     *
     * @param nectarValue - amount of nectar
     */
    public GoodFlower(int nectarValue, int nectarPool) {
        super(nectarValue, nectarPool, true);
        super.getFlowerImage().setImage(new Image("file:garden_jpgs\\aster.png"));
    }
}
