

public class IntStack
{
	// declare your private fields here
	private int[] data;
	private int top;
	/**
	 * Create an empty stack.
	 */
	
	public IntStack()
	{
		data = new int[150];
		top = -1;
		
	}

	

	
	/** 
	 * Pushes an item onto the top of this stack.
	 * @param x
	 */
	public void push(int x)
	{
		data[++top] = x;
	}
	
	/** 
	 * Removes the object at the top of this stack and returns that object as the value of this function.
	 * @return int The value removed from the stack. If empty, returns -1
	 */
	public int pop()
	{
		if(top == -1){
			return -1;
		}

		return data[top--];
		
	}


	public int peek()
	{
		if(top == -1){
			return -1;
		}

		return data[top];
	}
}

