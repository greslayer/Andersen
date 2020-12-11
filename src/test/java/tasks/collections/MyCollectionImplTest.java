package tasks.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


class MyCollectionImplTest {

    MyCollection<String> linkedList;
    String testString = "Test String ";

    @BeforeEach
    public void init(){
        linkedList = new MyCollectionImpl<String>();
        for (int i=0;i<10;i++){
            linkedList.add(testString+i);
        }
    }
    @Test
    public void add(){
        assertFalse(linkedList.isEmpty());
    }
    @Test
    public void get(){
        assertEquals(testString+1,linkedList.get(8));
    }




    @Test
    public void size(){
        assertEquals(10, linkedList.size());
    }
    @Test
    public void indexOverflow(){
        assertThrows(IndexOutOfBoundsException.class,()-> linkedList.get(11));
    }
    @Test
    public void contains(){
        assertTrue(linkedList.contains(testString+3));
    }
    @Test
    public void notContains(){
        assertFalse(linkedList.contains(testString+11));
    }
    @Test
    public void remove(){
        String removeString = testString+5;
        linkedList.remove(removeString);
        assertFalse(linkedList.contains(removeString));
    }
    @Test
    public void removeException(){
        assertThrows(NoSuchElementException.class,()->linkedList.remove(testString));
    }

}