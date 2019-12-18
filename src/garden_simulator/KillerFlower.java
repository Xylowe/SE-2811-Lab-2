package garden_simulator;

import javafx.scene.image.Image;

public class KillerFlower extends AbstractFlower {

    private int damage;

    public KillerFlower(int damage) {
        super(0, false);
        this.damage = damage;
        getFlowerImage().setImage(new Image("file:garden_jpgs\\nightshade.jpg"));
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public double getXLocation() {
        return super.getXLocation();
    }

    public double getYLocation() {
        return super.getYLocation();
    }

    @Override
    public boolean hasNectar() {
        return super.hasNectar();
    }

    @Override
    public int getNectarValue() {
        return super.getNectarValue();
    }
}
