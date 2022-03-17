package sample;

import javafx.scene.Group;
import javafx.scene.image.ImageView;


public class AlbinoRabbit extends Rabbit {
    private ImageView imageView = new ImageView("resources/albino.png");

    public AlbinoRabbit() {
        super();
    }

    @Override
    public void spawn(int x, int y, Group root) {
        this.imageView.setY(y);
        this.imageView.setX(x);
        root.getChildren().add(this.imageView);
    }


    @Override
    public void move() {

    }
}
