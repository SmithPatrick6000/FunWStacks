import static org.junit.Assert.*;

import org.junit.Test;

public class DoubleStackTest {
	DoubleStack stack = new DoubleStack();
	
	@Test
    public void testPopPush(){
        stack.push(5.5);
        assertEquals(5.5, stack.pop(), 0.001);
    }
	
	@Test
	public void testFILO() {
		stack.push(1.3);
        stack.push(2.9);
        stack.push(3.7);
        assertEquals(3.7, stack.pop(), 0.001);
        assertEquals(2.9, stack.pop(), 0.001);
        assertEquals(1.3, stack.pop(), 0.001);
	}
	
	@Test
	public void testPopEmptyStack() {
		assertEquals(-1, stack.pop(), 0.001);
	}
	
	@Test
	public void testPeekEmptyStack() {
		assertEquals(-1, stack.peek(), 0.001);
	}
    
    @Test
    public void testPeekNonEmpty() {
    	stack.push(5);
    	double stackPeek = stack.peek();
    	assertEquals(stackPeek, stack.pop(), 0.001);
    }

}
