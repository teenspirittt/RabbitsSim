package sample.rabbit;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import sample.IBehaviour;

public abstract class Rabbit extends Group implements IBehaviour {

    private static ImageView imageView = new ImageView();
    protected int lifeTime;
    protected int birthTime;
    protected int ID;
    protected int posX;
    protected int posY;
    protected double speed;
    protected int birthX;
    protected int birthY;
    protected int startX;
    protected int startY;
    protected int destX = 20;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDirectionChangeTime() {
        return directionChangeTime;
    }

    public void setDirectionChangeTime(double directionChangeTime) {
        this.directionChangeTime = directionChangeTime;
    }

    public double directionChangeTime;

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

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    protected Rabbit() {
        super();
    }

    public abstract void spawn(int x, int y, Group root);

    public abstract void delete(Group root);




}
