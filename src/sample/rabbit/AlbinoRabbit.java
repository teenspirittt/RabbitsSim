package sample.rabbit;

import javafx.scene.Group;
import javafx.scene.image.ImageView;


public class AlbinoRabbit extends Rabbit {
    public double rabbitSpeed = 2;


    private final ImageView imageView = new ImageView("resources/albino.png");

    public AlbinoRabbit() {
        super();
    }

    @Override
    public void spawn(int x, int y, Group root) {
        imageView.setY(y);
        imageView.setX(x);
        root.getChildren().add(imageView);
    }

    @Override
    public void delete(Group root) {
        root.getChildren().remove(imageView);
    }

    @Override
    public void moveImage() {
        imageView.setX(posX);
    }
}
