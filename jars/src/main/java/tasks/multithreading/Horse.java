package tasks.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Horse implements Comparable<Horse>, Runnable {
    private String name;
    private double speed;
    private double distance;
    private final CyclicBarrier cyclicBarrier;

    public Horse(String name, double speed, double distance, CyclicBarrier cyclicBarrier) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            distance += (speed * Math.random());
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void print() {
        for (int i = 0; i < Math.round(distance); i++) {
            System.out.print(".");
        }
        System.out.print(name + "\n");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return name + " = " + distance;
    }

    @Override
    public int compareTo(Horse horse) {
        return (int) (distance - horse.distance) * 100000;
    }
}
