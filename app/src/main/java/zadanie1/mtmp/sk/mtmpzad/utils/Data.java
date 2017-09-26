package zadanie1.mtmp.sk.mtmpzad.utils;

import android.content.Intent;

import java.io.Serializable;
import java.util.List;

/**
 * Created by z003r0bf on 26. 9. 2017.
 */

public class Data implements Serializable {

    private List<Integer> x;

    private List<Integer>y;

    private List<Double> times;

    private double endTime;

    private double maxDistance;

    public Data(List<Integer> x, List<Integer> y, List<Double> times, double endTime, double maxDistance) {
        this.x = x;
        this.y = y;
        this.times = times;
        this.endTime = endTime;
        this.maxDistance = maxDistance;
    }

    public List<Double> getTimes() {
        return times;
    }

    public void setTimes(List<Double> times) {
        this.times = times;
    }

    public List<Integer> getX() {
        return x;
    }

    public void setX(List<Integer> x) {
        this.x = x;
    }

    public List<Integer> getY() {
        return y;
    }

    public void setY(List<Integer> y) {
        this.y = y;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }
}
