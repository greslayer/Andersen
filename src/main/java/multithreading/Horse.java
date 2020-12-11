package multithreading;

public class Horse implements Comparable<Horse> {
    private String name;
    private double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public void move(){
        distance+=(speed*Math.random());
    }

    public void print(){
        for (int i=0;i<Math.round(distance);i++){
            System.out.print(".");
        }
        System.out.print(name+"\n");
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
        return "Horse{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                '}';
    }

    @Override
    public int compareTo(Horse horse) {
        return (int) (distance-horse.distance)*100000;
    }
}
