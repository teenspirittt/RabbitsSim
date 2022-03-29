package sample;

import java.util.ArrayList;

public class Model {

    private static Model instance;

    private int crCount = 0, alCount = 0;
    private int crChance = 70;
    private int alChance = 20;
    private int crTime = 2;
    private int alTime = 2;
    private int tTick = 0;
    private boolean isTimerWorking = false;
    private boolean isStatsVisible = false;

    private final ArrayList<Rabbit> rabbitsList = new ArrayList();

    static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public ArrayList<Rabbit> getRabbitsList() {
        return rabbitsList;
    }

    public void resetStats() {
        crCount = 0;
        alCount = 0;
        tTick = 0;
        rabbitsList.clear();
    }


    public int getAlChance() {
        return alChance;
    }

    public int getAlTime() {
        return alTime;
    }

    public int getCrChance() {
        return crChance;
    }

    public int getCrCount() {
        return crCount;
    }

    public int getAlCount() {
        return alCount;
    }

    public boolean isStatsVisible() {
        return isStatsVisible;
    }

    public boolean isTimerWorking() {
        return isTimerWorking;
    }

    public int getCrTime() {
        return crTime;
    }

    public int gettTick() {
        return tTick;
    }

    public void setStatsVisible(boolean statsVisible) {
        isStatsVisible = statsVisible;
    }

    public void setTimerWorking(boolean timerWorking) {
        isTimerWorking = timerWorking;
    }

    public void setAlCount(int alCount) {
        this.alCount = alCount;
    }

    public void setCrCount(int crCount) {
        this.crCount = crCount;
    }

    public void setAlChance(int alChance) {
        this.alChance = alChance;
    }

    public void setAlTime(int alTime) {
        this.alTime = alTime;
    }

    public void setCrChance(int crChance) {
        this.crChance = crChance;
    }

    public void setCrTime(int crTime) {
        this.crTime = crTime;
    }

    public void settTick(int tTick) {
        this.tTick = tTick;
    }


}
