package garden_simulator;

import javafx.scene.image.Image;

public class GoodFlower extends AbstractFlower {

    public GoodFlower(int nectarValue, boolean nectar) {
        super(nectarValue, nectar);
        super.getFlowerImage().setImage(new Image("file:aster.jpg"));
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
