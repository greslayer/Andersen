package tasks.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Hippodrome {
    public static Hippodrome game;
    private final List<Horse> horses = new ArrayList<>();
    CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

    public static void main(String[] args) {
        game = new Hippodrome();
        Horse bojack = new Horse("Bojack", 3, 0, game.cyclicBarrier);
        Horse arrow = new Horse("Arrow", 3, 0, game.cyclicBarrier);
        Horse snail = new Horse("Snail", 3, 0, game.cyclicBarrier);
        game.getHorses().add(bojack);
        game.getHorses().add(arrow);
        game.getHorses().add(snail);
        game.run();
        game.printWinner();
    }

    public void run() {
        move();
        for (int i = 0; i < 20; i++) {
            print();
            try {
                Thread.sleep(500);
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }

    }

    public void move() {
        for (Horse horse : horses) {
            Thread thread = new Thread(horse);
            thread.start();

        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        for (int i = 0; i < 4; i++) {
            System.out.println();
        }
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner());
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Horse::compareTo)
                .get();
    }
}
