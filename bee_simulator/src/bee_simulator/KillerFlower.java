package bee_simulator;

import javafx.scene.image.Image;

public class KillerFlower extends AbstractFlower {

    private int damage;

    public KillerFlower(int damage) {
        super(0, false);
        this.damage = damage;
        getFlowerImage().setImage(new Image("file:nightshade.jpg"));
    }

    public int getDamage() {
        return damage;
    }

}
