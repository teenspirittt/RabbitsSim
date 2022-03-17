package sample;

import javafx.scene.Group;
import javafx.scene.image.ImageView;


public abstract class Rabbit extends Group implements IBehaviour {

    private static ImageView imageView = new ImageView();

    protected Rabbit() {
        super();
    }


    public abstract void spawn(int x, int y, Group root);


}
