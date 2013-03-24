package gr.vb.it.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HashMapTest {
    
    @Test
    public void HashMapTest(){
        int MAXIMUM_CAPACITY = 1 << 2;
        assertEquals(4, MAXIMUM_CAPACITY);
    }

}
