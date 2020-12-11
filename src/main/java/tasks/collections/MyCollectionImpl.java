package tasks.collections;

import java.util.NoSuchElementException;

public class MyCollectionImpl<T> implements MyCollection<T> {

    private Entry<T> top;
    private int size;




    public void add(T t) {
        Entry<T> entry = new Entry<>(t);
        bindEntries(entry,top);
        top=entry;
        size++;
    }

    public void remove(T t) throws NoSuchElementException{
        var  current = find(t);
            if (current==null) throw new NoSuchElementException();
        var previous = current.getPrevious();
        var next = current.getNext();
        bindEntries(previous,next);
        size--;
    }

    public boolean contains(T t) {
        return find(t) != null;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public T get(int i) {
        if (i<size&&!isEmpty()){
            return iterate(i).getData();
        }
        throw new IndexOutOfBoundsException(i+" not in bounds for size "+size);

    }

    public int size() {
        return size;
    }

    @Override
    public void clear() {
        top=null;
        size=0;
    }

    private Entry<T> iterate(int i){
        Entry<T> entry = top;
        for (int iter=0;iter<i;iter++){
            entry=entry.getNext();
        }
        return entry;

    }


    private Entry<T> find(T t){

        Entry<T> current = top;
        while (current!=null){
            if (current.getData().equals(t))
                break;
            current=current.getNext();
        }
        return current;
    }

    private void bindEntries(Entry<T> previous,Entry<T> next){
        if (next!=null){
            next.setPrevious(previous);
        }
        previous.setNext(next);


    }

}
