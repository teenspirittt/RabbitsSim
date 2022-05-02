package sample.rabbitAI;

import sample.rabbit.Rabbit;

import java.util.Vector;

public abstract class BaseAI extends Thread {
    protected volatile boolean paused = true;
    protected final Object pauseLock = new Object();
    protected final Vector<Rabbit> rabbitsVector;

    public BaseAI(Vector<Rabbit> rabbitsVector) {
        this.rabbitsVector = rabbitsVector;
    }

    public void pause() {
        paused = true;
    }

    public void unpause(){
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }

    public boolean isPaused() {
        return paused;
    }
}
