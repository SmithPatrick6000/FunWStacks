
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
        for(int i = 0; i < length;i++){
            
            if(!(m.charAt(i) > 96 && m.charAt(i) < 123)){
                System.out.println("Uppercase got through");
                //Letter
                if(m.charAt(i) > 64){
                    operands.append(m.charAt(i));
                    
                    if((i+1) < length){
                        
                        if(m.charAt(i+1) > 96 && m.charAt(i+1) < 123){
                            int p = 1;
                            while((p + i) < length && m.charAt(i+p) > 96 && m.charAt(i+p) < 123){
                                operands.append(m.charAt(i+p));
                                p++;
                                
                            }

                        }
                    }
                    plusMult(i,length,operands,stack, m);
                }
                //Left parenthesis
                else if(m.charAt(i) == 40){
                    stack.push('(');   
                }
                //right parenthesis
                else if(m.charAt(i) == 41){
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
            }else{
                System.out.println("Lowercase got through");
            }

        }
        while(stack.peek() != -1){
            if(stack.peek() == 42){
                operands.append('*');
                
            }else{
                operands.append('+');
            }
            stack.pop();
        }

        //Takes the posfix expression and solves the Equation
        int olength = operands.length();
        int op1 = 0;
        int op2 = 0;
        System.out.println(operands);
        for(int i = 0; i < olength; i++){
            if(!(operands.charAt(i) > 96 && operands.charAt(i) < 123)){

                if(operands.charAt(i) > 64 && operands.charAt(i) < 91){
                    StringBuilder fullSymb = new StringBuilder();
                    fullSymb.append(operands.charAt(i));
                    int p = 1;
                    if(i + 1 < olength){
                        if(operands.charAt(i+1) > 96 && operands.charAt(i+1) < 123){
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
                    stack.push(operands.charAt(i)-48);
                }

            }else{
                System.out.println("Lowercase got through");
            }
        }
        
        
        return stack.pop();
    }

    //Adds a plus symbol after each element so it works in postfix
    public static void plusMult(int i,int length,StringBuilder operands,IntStack stack, String m){
        if(i + 1 < length && m.charAt(i+1) != 41){
                   
            if(m.charAt(i+1) > 64 || m.charAt(i+1) < 47){
                        
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
        System.out.println(mass);
        return mass;
    } 


}

