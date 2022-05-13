package sample.rabbit;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jdk.jfr.SettingDefinition;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;


public class CommonRabbit extends Rabbit {
    public double rabbitSpeed = 2;
    transient private ImageView imageView = new ImageView("resources/commonRabbit.png");

    public CommonRabbit() {
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
        imageView.setY(posY);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.imageView = new ImageView("resources/commonRabbit.png");
    }

    @Serial
    private void writeObject(ObjectOutputStream oou) throws IOException {
        oou.defaultWriteObject();
    }
}
