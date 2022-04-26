package sample.rabbit;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class CommonRabbit extends Rabbit {

    private final ImageView imageView = new ImageView("resources/commonRabbit.png");

    public CommonRabbit() {
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
    public void move() {

    }
}

