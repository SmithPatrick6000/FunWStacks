


import org.junit.Test;
import static org.junit.Assert.*;




public class IntStackTest{
	IntStack stack = new IntStack();
    
	@Test
    public void testPushPop(){
        stack.push(5);
        assertEquals(5, stack.pop());
    }
	
	@Test
	public void testFILO() {
		stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
	}
	
	@Test
	public void testPopEmptyStack() {
		assertEquals(-1, stack.pop());
	}
	
	@Test
	public void testPeekEmptyStack() {
		assertEquals(-1, stack.peek());
	}
    
    @Test
    public void testPeekNonEmpty() {
    	stack.push(5);
    	int stackPeek = stack.peek();
    	assertEquals(stackPeek, stack.pop());
    }
    


}