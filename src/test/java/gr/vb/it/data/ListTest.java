package gr.vb.it.data;

import java.util.LinkedList;

import org.junit.Test;

public class ListTest {
    
    @Test
    public void testListRemove(){
        LinkedList<String> list = new LinkedList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        
        list.remove(1);
    }
    

}
