
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MolecularMass
{ 
    
    public static final String DELIMITER = ",";
    public static final int ELEMENT_AMOUNT = 118;

    //The order of information in the Elements.csv file
    public static final int IDNUMBER_LOC = 0;
    public static final int SYMBOL_LOC = 1;
    public static final int NAME_LOC = 2;
    public static final int MASS_LOC =3;
    
    public static String elementFile = "Elements.csv";

    
    public static void main(String[] args)
    {
        java.util.Scanner kb = new java.util.Scanner(System.in);
        System.out.print("Enter the molecule: ");
        String molString = kb.nextLine();     
        System.out.print("Your Molecular Weight is: " + calculate(molString));
        kb.close();
    }
   
    //Calculates the weight of a chemical formula
    public static int calculate(String m)
    {
        IntStack stack = new IntStack();
        StringBuilder operands = new StringBuilder();
        int length = m.length();

        //Turns input into postfix
        turnPost(length, operands, stack, m);

        //Shows the postfix
        System.out.println("turnPost result: " + operands);

        //Takes the posfix expression and solves the Equation
        parseFix(length, operands, stack);
        

        return stack.pop();
    }

    //Turns chemical formula into postfix notation
    public static void turnPost(int length, StringBuilder operands, IntStack stack, String m){
        for(int i = 0; i < length;i++){
            
            if(!(m.charAt(i) >= 'a' && m.charAt(i) <= 'z')){
                //Letter
                if(m.charAt(i) >= 'A'){
                    operands.append(m.charAt(i));
                    
                    if((i+1) < length){
                        
                        if(m.charAt(i+1) >= 'a' && m.charAt(i+1) <= 'z'){
                            int p = 1;
                            while((p + i) < length && m.charAt(i+p) >= 'a' && m.charAt(i+p) <= 'z'){
                                operands.append(m.charAt(i+p));
                                p++;
                                
                            }

                        }
                    }
                    plusMult(i,length,operands,stack, m);
                }
                //Left parenthesis
                else if(m.charAt(i) == '('){
                    stack.push('(');   
                }
                //right parenthesis
                else if(m.charAt(i) == ')'){
                    while(stack.peek() != '('){
                        int temp = stack.pop();
                        if(temp == '+'){
                            operands.append('+');
                        }else{
                            operands.append('*');
                        }
                    }
                    stack.pop();
                    plusMult(i,length,operands,stack, m);
                }
                //number
                else{
                    operands.append(m.charAt(i));
                    plusMult(i,length,operands,stack, m);
                } 
            }
            //Lowercase through goes here

        }

        //Pops the stack until it is empty adding the remaining operators are added 
        while(stack.peek() != -1){
            if(stack.peek() == '*'){
                operands.append('*');
                
            }else if(!(stack.peek() >= 'a' && stack.peek() <= 'z')){
                operands.append('+');
            }
            stack.pop();
        }
    }

    //Adds a plus symbol after each element so it works in postfix
    public static void plusMult(int i,int length,StringBuilder operands,IntStack stack, String m){
        System.out.println("Operand entering plusMult: " + operands);
        if(i + 1 < length && m.charAt(i+1) != ')' && m.charAt(i+1) < 'a'){
            
            if(m.charAt(i+1) > '9' || m.charAt(i+1) < '0'){
                        
                if((stack.peek() == '+' || stack.peek() == '*')&& stack.peek() != '(' && stack.peek() != ')'){
                    int temp = stack.pop();
                    if(temp == '+'){
                        operands.append('+');
                    }else{
                        operands.append('*');
                    }
                }
                     
                stack.push('+');
            }else{
                        
                if(stack.peek() == '*' && stack.peek() != '(' && stack.peek() != ')'){
                    int temp = stack.pop();
                    if(temp == '+'){
                        System.out.println("PushedLetter plus");
                        operands.append('+');
                    }else{
                        operands.append('*');
                    }
                }
                       
                       
                stack.push('*');
                        
            }

            System.out.println("Code actually ran in plusMult");
        }
        System.out.println("Operand leaving plusMult: " + operands);
    }

    //Takes the posfix expression and solves the Equation
    public static void parseFix(int length, StringBuilder operands, IntStack stack){
        int olength = operands.length();
        int op1 = 0;
        int op2 = 0;
        
        for(int i = 0; i < olength; i++){
            if(!(operands.charAt(i) >= 'a' && operands.charAt(i) <= 'z')){

                if(operands.charAt(i) >= 'A' && operands.charAt(i) <= 'Z'){
                    StringBuilder fullSymb = new StringBuilder();
                    fullSymb.append(operands.charAt(i));
                    int p = 1;
                    if(i + 1 < olength){
                        if(operands.charAt(i+1) >= 'a' && operands.charAt(i+1) <= 'z'){
                            while((i+p) < length && operands.charAt(i+p) > 96 && operands.charAt(i+p) < 123){
                                fullSymb.append(operands.charAt(i+p)); 
                                p++;
                            }
                        
                        }
                    }
                    int pushElem = 0;
                    try{
                        pushElem = findChar(fullSymb.toString());
                    }catch(IOException e){
                        System.out.println("IOException: "+ e.getMessage());
                    }
                    stack.push(pushElem);
                    
                }else if(operands.charAt(i) == '*'){
                    op2 = stack.pop();
                    op1 = stack.pop();
                    op1 = op1 * op2;
                    stack.push(op1);
                }else if(operands.charAt(i) == '+'){
                    op2 = stack.pop();
                    op1 = stack.pop();
                    op1 = op1 + op2;
                    stack.push(op1);
                }else{
                    stack.push(operands.charAt(i)-'0');
                }

            }
            //Lovercase through goes here
        }
    }

    //Finds the Corresponding elemental symbal and outputs the mass
    public static int findChar(String symbol) throws IOException{
        FileReader fr = null;
        BufferedReader br = null;
        fr = new FileReader(elementFile);
        br = new BufferedReader(fr);
        
        
        String line;
       
        int mass = 0;
        while((line = br.readLine()) != null){
            String[] partition = line.split(DELIMITER);
            
            if(partition[SYMBOL_LOC].equals(symbol)){

                mass = (int)Math.round(Double.parseDouble(partition[MASS_LOC]));
            }

        }

        br.close();
        if(mass == 0){
            System.out.println("Symbol does not equal Character");
        }
        
        System.out.println("Mass leaving findChar: " + mass);
        return mass;
    } 


}

