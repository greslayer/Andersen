package tasks;

import tasks.collections.MyCollection;
import tasks.collections.MyCollectionImpl;

public class Main {
    public static void main(String[] args) {

        MyCollection<Integer> integers = new MyCollectionImpl<>();
        for (int i=0;i<10;i++){
            integers.add(i);
        }
        integers.add(null);
        integers.add(11);
        integers.add(12);
        integers.clear();
        System.out.println(integers);
    }
}
