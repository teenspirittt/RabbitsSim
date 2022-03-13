package sample;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.awt.event.ActionEvent;


public abstract class Rabbit extends Group implements IBehaviour {

    protected Rabbit(ImageView imageView) {
        super(imageView);
    }

    public void spawn(int x, int y) {
        ImageView imageView = (ImageView) this.getChildren().get(0);
        imageView.setY(y);
        imageView.setX(x);
        imageView.setScaleX(0.3);
        imageView.setScaleY(0.3);
    }

}
