package sample;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class CommonRabbit extends Rabbit {
    private static ImageView imageView = new ImageView("resources/commonRabbit.png");


    public CommonRabbit() {
        super(imageView);
    }


    @Override
    public void move() {

    }
}


