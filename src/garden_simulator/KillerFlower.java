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
 * Flower that will drain the energy from a bee
 */
public class KillerFlower extends AbstractFlower {

    private int damage;

    /**
     * Constructor for the flower that will drain the energy from a bee
     *
     * @param damage amount of damage the flower will do
     */
    public KillerFlower(int damage) {
        super(0, false);
        this.damage = damage;
        getFlowerImage().setImage(new Image("file:garden_jpgs\\nightshade.jpg"));
    }

    /**
     * Returns the amount of damage the flower will inflict
     *
     * @return amount of damage
     */
    public int getDamage() {
        return damage;
    }
}
