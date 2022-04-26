package sample.rabbit;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import sample.IBehaviour;

public abstract class Rabbit extends Group implements IBehaviour {

    private static ImageView imageView = new ImageView();
    public int lifeTime;
    public int birthTime;
    public int ID;

    protected Rabbit() {
        super();
    }

    public abstract void spawn(int x, int y, Group root);

    public abstract void delete(Group root);

    public int getBirthTime() {
        return birthTime;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public int getID() {
        return ID;
    }

    public void setBirthTime(int birthTime) {
        this.birthTime = birthTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


}
