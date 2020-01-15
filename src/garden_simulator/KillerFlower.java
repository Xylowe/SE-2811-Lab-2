/*
 * Course: SE2811
 * Winter 2019-2020
 * Lab 4 - The Flowers and the Bees
 * Name: Trenton Bowser and Matt Aleck
 * Created 12/12/2019
 */
package garden_simulator;

import javafx.scene.image.Image;

/**
 * Flower that will drain the energy from a bee
 */
public class KillerFlower extends AbstractFlower {

    /**
     * Constructor for the flower that will drain the energy from a bee
     *
     * @param damage amount of damage the flower will do
     */
    public KillerFlower(int damage, int nectarPool) {
        super(-damage, nectarPool, true);
        getFlowerImage().setImage(new Image("file:garden_pngs/nightshade.png"));
    }
}
