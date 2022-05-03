package sample.rabbit;

import javafx.scene.Group;
import sample.IBehaviour;

public abstract class Rabbit extends Group implements IBehaviour {

    protected int lifeTime;
    protected int birthTime;
    protected int ID;
    protected float posX;
    protected float posY;
    protected int birthX;
    protected int birthY;
    protected int destX = 400;
    protected int destY = 400;


    public int getDestX() {
        return destX;
    }

    public void setDestX(int destX) {
        this.destX = destX;
    }

    public int getDestY() {
        return destY;
    }

    public void setDestY(int destY) {
        this.destY = destY;
    }

    public int getBirthX() {
        return birthX;
    }

    public void setBirthX(int birthX) {
        this.birthX = birthX;
    }

    public int getBirthY() {
        return birthY;
    }

    public void setBirthY(int birthY) {
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

    public abstract void spawn(int x, int y, Group root);

    public abstract void delete(Group root);


}
