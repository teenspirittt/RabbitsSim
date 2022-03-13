package sample;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class AlbinoRabbit extends Rabbit {
    private static ImageView imageView = new ImageView("resources/albino.png");

    public AlbinoRabbit() {
        super(imageView);
    }

    @Override
    public void move() {

    }


}
