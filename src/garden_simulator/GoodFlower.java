package garden_simulator;

import javafx.scene.image.Image;

public class GoodFlower extends AbstractFlower {

    public GoodFlower(int nectarValue, boolean nectar) {
        super(nectarValue, nectar);
        super.getFlowerImage().setImage(new Image("file:garden_jpgs\\aster.jpg"));
    }
}
