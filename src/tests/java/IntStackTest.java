


import org.junit.Test;
import static org.junit.Assert.*;




public class IntStackTest{

    @Test
    public void testPushPop(){
        IntStack stack = new IntStack();
        stack.push(5);
        assertEquals(5, stack.pop());
        
    }

}