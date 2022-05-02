package sample.rabbit;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import sample.rabbitAI.myVector;

import java.util.Random;
import java.util.Vector;

public class CommonRabbit extends Rabbit {

    public myVector way;
    public double rabbitSpeed = 0.03;


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
    public void move(Rabbit rabbit) {

    }

    public void moveImage() {


        imageView.setX(posX);

        imageView.setY(posY);

    }


}


