public class DoubleStack
{
	private double[] data;
	private int top;
	
	//Initialize data and marks the top of the stack
	public DoubleStack() {
		data = new double[150];
		top = -1;
	}
	
	/**
	 * Pushes an item onto the top of the stack
	 * @param x
	 */
	public void push(double x) {
		data[++top] = x;
	}
	
	/**
	 * Pops the number at the top of the stack
	 * @return double The value removed from the stack, if empty returns -1
	 */
	public double pop() {
		if(top == -1) {
			return -1;
		}
		
		return data[top--];
	}
	
	/**
	 * Peeks at the number on top of the stack without removing it
	 * @return double The value peeked at the top of the stack, if empty returns -1
	 */
	public double peek() {
		if(top == -1) {
			return -1;
		}
		return data[top];
	}
	
	
}