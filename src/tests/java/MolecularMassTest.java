import static org.junit.Assert.*;

import org.junit.Test;

public class MolecularMassTest {

	@Test
	public void turnPostTest1() {
		DoubleStack stack = new DoubleStack();
		StringBuilder operands = new StringBuilder();
		String element = "H2O";
		int length = element.length();
		MolecularMass.turnPost(length, operands, stack, element);
		assertEquals("H2*O+", operands.toString());
	}

	// Tests if turnPost works with Chemicals with more then 1 character
	@Test
	public void turnPostTest2() {
		DoubleStack stack = new DoubleStack();
		StringBuilder operands = new StringBuilder();
		String element = "NaCl";
		int length = element.length();
		MolecularMass.turnPost(length, operands, stack, element);
		assertEquals("NaCl+", operands.toString());
	}

	// Tests if Parenthesis are handled correctly
	@Test
	public void turnPostTest3() {
		DoubleStack stack = new DoubleStack();
		StringBuilder operands = new StringBuilder();
		String element = "Ca(OH)2";
		int length = element.length();
		MolecularMass.turnPost(length, operands, stack, element);
		assertEquals("CaOH+2*+", operands.toString());
	}

	// Tests if multiple parenthesis are handled correctly
	@Test
	public void turnPostTest4() {
		DoubleStack stack = new DoubleStack();
		StringBuilder operands = new StringBuilder();
		String element = "H(Uuo(H)3)";
		int length = element.length();
		MolecularMass.turnPost(length, operands, stack, element);
		assertEquals("HUuoH3*++", operands.toString());
	}

	
	  //Tests if turnPost works with numbers over 9
	  @Test 
	  public void turnPostTest5() { 
		  DoubleStack stack = new DoubleStack();
		  StringBuilder operands = new StringBuilder(); String element = "H19"; int
		  length = element.length(); MolecularMass.turnPost(length, operands, stack,element); 
		  assertEquals("H19*",operands.toString()); }
	 

	@Test
	public void findCharTest1() throws Exception {
		assertEquals(1.008, MolecularMass.findChar("H"),0.0001);
	}

	// Tests if findChar works for molecules with 2 chars
	@Test
	public void findCharTest2() throws Exception {
		assertEquals(40.078, MolecularMass.findChar("Ca"),0.0001);
	}

	// Tests if findChar works for molecules with 3 chars
	@Test
	public void findCharTest3() throws Exception {
		assertEquals(294.0, MolecularMass.findChar("Uuo"),0.0001);
	}

	@Test
	public void calculateTest1() {
		assertEquals(18.015, MolecularMass.calculate("H2O"),0.0001);
	}

	// Tests to solve more complicated formulas
	@Test
	public void calculateTest2() {
		assertEquals(298.032, MolecularMass.calculate("H(Uuo(H)3)"),0.0001);
	}

	// Tests to see if numbers over 9 are handled correctly
	  @Test 
	  public void calculateTest3() { 
		  assertEquals(19.152,MolecularMass.calculate("H19"),0.0001); 
	  }
	 

}
