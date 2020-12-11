package tasks.multithreading;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    public static Hippodrome game;
    private List<Horse> horses = new ArrayList<>();

    public List<Horse> getHorses() {
        return horses;
    }

    public static void main(String[] args) {
        game = new Hippodrome();
        Horse bojack = new Horse("Bojack",3,0);
        Horse arrow = new Horse("Arrow",3,0);
        Horse snail = new Horse("Snail",3,0);
        game.horses.add(bojack);
        game.horses.add(arrow);
        game.horses.add(snail);
        game.run();
        game.printWinner();
    }

    public void run(){
        for (int i=0;i<20;i++){
            move();
            print();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void move(){
        for (Horse horse:horses){
            horse.move();
        }
    }

    public void print(){
        for (Horse horse : horses){
            horse.print();
        }
        for (int i=0;i<4;i++){
            System.out.println("");
        }
    }

    public void printWinner(){
        System.out.println("Winner is "+getWinner());
    }
    public Horse getWinner(){
        return horses.stream()
                .max(Horse::compareTo)
                .get();
    }
}
