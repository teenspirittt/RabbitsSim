package sample.rabbit;

import javafx.scene.Group;
import sample.IBehaviour;
import sample.model.Model;


import java.io.*;

public abstract class Rabbit extends Group implements IBehaviour, Serializable {

    protected int lifeTime;
    protected int birthTime;
    protected int ID;
    protected float posX;
    protected float posY;
    protected float birthX;
    protected float birthY;
    protected float destX = 400;
    protected float destY = 400;

    public float getDestX() {
        return destX;
    }

    public void setDestX(float destX) {
        this.destX = destX;
    }

    public float getDestY() {
        return destY;
    }

    public void setDestY(float destY) {
        this.destY = destY;
    }

    public float getBirthX() {
        return birthX;
    }

    public void setBirthX(float birthX) {
        this.birthX = birthX;
    }

    public float getBirthY() {
        return birthY;
    }

    public void setBirthY(float birthY) {
        this.birthY = birthY;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public int getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(int birthTime) {
        this.birthTime = birthTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    protected Rabbit() {
        super();
    }

    public abstract void spawn(float x, float y, Group root);

    public abstract void delete(Group root);

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
    }

    @Serial
    private void writeObject(ObjectOutputStream oot) throws IOException, ClassNotFoundException {
        lifeTime -= Model.getInstance().gettTick() - birthTime;
        oot.defaultWriteObject();
    }


}
