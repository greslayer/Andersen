package tasks.collections;

class Entry<T> {
    private final T data;
    private Entry<T> next;
    private Entry<T> previous;

    public Entry<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Entry<T> previous) {
        this.previous = previous;
    }

    public Entry(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Entry<T> getNext() {
        return next;
    }

    public void setNext(Entry<T> next) {
        this.next = next;
    }
}
