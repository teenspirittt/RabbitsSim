package sample;

import sample.rabbit.*;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Vector;

public class Model {
    private static Model instance;
    private int crCount = 0, alCount = 0;
    private int crChance = 70;
    private int alChance = 20;
    private int alLifeTime = 5;
    private int crLifeTime = 5;
    private int crTime = 2;
    private int alTime = 2;
    private int tTick = 0;
    private int alThreadPriority = 5;
    private int crThreadPriority = 5;
    private int mainThreadPriority = 5;
    private boolean isTimerWorking = false;
    private boolean isStatsVisible = false;
    private boolean isAdvancedMode = true;

    private final Vector<Rabbit> rabbitsVector = new Vector<>();
    private final TreeSet<Integer> rabbitsIdSet = new TreeSet<>();
    private final HashMap<Integer, Integer> rabbitsLifeTimeMap = new HashMap<>();

    static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public Vector<Rabbit> getRabbitsVector() {
        return rabbitsVector;
    }

    public HashMap<Integer, Integer> getRabbitsLifeTimeMap() {
        return rabbitsLifeTimeMap;
    }

    public TreeSet<Integer> getRabbitsIdSet() {
        return rabbitsIdSet;
    }

    public void resetStats() {
        crCount = 0;
        alCount = 0;
        tTick = 0;
        rabbitsVector.clear();
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

    public int getAlLifeTime() {
        return alLifeTime;
    }

    public int getCrLifeTime() {
        return crLifeTime;
    }

    public boolean isStatsVisible() {
        return isStatsVisible;
    }

    public boolean isAdvancedMode() {
        return isAdvancedMode;
    }

    public int getAlThreadPriority() {
        return alThreadPriority;
    }

    public void setAlThreadPriority(int alThreadPriority) {
        this.alThreadPriority = alThreadPriority;
    }

    public int getCrThreadPriority() {
        return crThreadPriority;
    }

    public void setCrThreadPriority(int crThreadPriority) {
        this.crThreadPriority = crThreadPriority;
    }

    public int getMainThreadPriority() {
        return mainThreadPriority;
    }

    public void setMainThreadPriority(int mainThreadPriority) {
        this.mainThreadPriority = mainThreadPriority;
    }

    public void setAdvancedMode(boolean advancedMode) {
        isAdvancedMode = advancedMode;
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

    public void setAlLifeTime(int alLifeTime) {
        this.alLifeTime = alLifeTime;
    }

    public void setCrLifeTime(int crLifeTime) {
        this.crLifeTime = crLifeTime;
    }

    public void settTick(int tTick) {
        this.tTick = tTick;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
