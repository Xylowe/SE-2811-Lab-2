package garden_simulator;

import javafx.scene.image.ImageView;

import java.lang.Math;

abstract class AbstractFlower implements Flower {

    private int nectarValue;
    private boolean nectar;
    private double XLocation;
    private double YLocation;
    private ImageView flowerImage;

    public AbstractFlower(int nectarValue, boolean nectar) {
        this.nectarValue = nectarValue;
        this.nectar = nectar;
        XLocation = Math.random()*900;
        YLocation = Math.random()*700;
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

    public ImageView getFlowerImage() {
        return flowerImage;
    }
}
