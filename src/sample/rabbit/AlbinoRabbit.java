package sample.rabbit;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;


public class AlbinoRabbit extends Rabbit {
    public double rabbitSpeed = 2;


    transient private ImageView imageView = new ImageView("resources/images/alRabbit.png");

    public AlbinoRabbit() {
        super();
    }

    @Override
    public void spawn(float x, float y, Group root) {
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

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.imageView = new ImageView("resources/albino.png");
    }

    @Serial
    private void writeObject(ObjectOutputStream oou) throws IOException {
        oou.defaultWriteObject();
    }
}
