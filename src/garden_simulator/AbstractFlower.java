/*
 * Course: SE2811
 * Winter 2019-2020
 * Lab 4 - The Flowers and the Bees
 * Name: Trenton Bowser and Matt Aleck
 * Created 12/12/2019
 */
package garden_simulator;

import javafx.scene.image.ImageView;

import java.lang.Math;

/**
 * Abstract flower that implements the flower interface
 */
public abstract class AbstractFlower implements Flower {

    private int nectarValue;
    private boolean nectar;
    private int nectarPool;
    private double XLocation;
    private double YLocation;
    private ImageView flowerImage;

    /**
     * Constructor for the abstract flower
     *
     * @param nectarValue - the amount of nectar
     * @param nectar      - if the flower has nectar
     */
    public AbstractFlower(int nectarValue, int nectarPool, boolean nectar) {
        this.nectarValue = nectarValue;
        this.nectarPool = nectarPool;
        this.nectar = nectar;
        XLocation = Math.random() * (GardenController.width - 50);
        if(XLocation > 200) {
            YLocation = Math.random() * (GardenController.height - 45);
        } else {
            YLocation = Math.random() * (GardenController.height - 245);
        }
        this.flowerImage = new ImageView();
    }

    public int getNectarValue() {
        return nectarValue;
    }

    public int getNectarPool() {
        return nectarPool;
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

    public void setNectarPool(int nectarPool) {
        this.nectarPool = nectarPool;
    }

    public ImageView getFlowerImage() {
        return flowerImage;
    }
}
