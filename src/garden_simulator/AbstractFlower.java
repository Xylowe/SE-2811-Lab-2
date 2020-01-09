/*
 * Course: SE2811
 * Winter 2019-2020
 * Lab 2 - The Flowers and the Bees
 * Name: Trenton Bowser and Matt Aleck
 * Created 12/12/2019
 */
package garden_simulator;

import javafx.scene.image.ImageView;

import java.lang.Math;

/**
 * Abstract flower that implements the flower interface
 */
abstract class AbstractFlower implements Flower {

    private int nectarValue;
    private boolean nectar;
    private double XLocation;
    private double YLocation;
    private ImageView flowerImage;

    /**
     * Constructor for the abstract flower
     *
     * @param nectarValue - the amount of nectar
     * @param nectar      - if the flower has nectar
     */
    public AbstractFlower(int nectarValue, boolean nectar) {
        this.nectarValue = nectarValue;
        this.nectar = nectar;
        XLocation = Math.random() * 850;
        YLocation = Math.random() * 650;
        this.flowerImage = new ImageView();
    }

    public int getNectarValue() {
        return nectarValue;
    }

    public double getXLocation() {
        return XLocation;
    }

    public double getYLocation() {
        return YLocation;
    }

    public boolean hasNectar() {
        return nectar;
    }

    public void setNectar(boolean nectar) {
        this.nectar = nectar;
    }

    public ImageView getFlowerImage() {
        return flowerImage;
    }
}
