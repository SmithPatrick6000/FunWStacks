package student;

public class MolecularMass
{ 
    public static final int HYDROGEN = 1;
    public static final int CARBON = 12;
    public static final int OXYGEN = 16;

    
    public static void main(String[] args)
    {
        java.util.Scanner kb = new java.util.Scanner(System.in);
        System.out.print("Enter the molecule: ");
        String molString = kb.nextLine();     
        System.out.print("Your Molecular Weight is: " + calculate(molString));
        kb.close();
    }

    public static int calculate(String m)
    {
        IntStack stack = new IntStack();
        StringBuilder operands = new StringBuilder();

        int length = m.length();
        for(int i = 0; i < length;i++){
            
            //Letter
            if(m.charAt(i) > 64){
                operands.append(m.charAt(i));
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
           
        }
        while(stack.peek() != -1){
            if(stack.peek() == 42){
                operands.append('*');
                
            }else{
                operands.append('+');
            }
            stack.pop();
        }

        int olength = operands.length();
        int op1 = 0;
        int op2 = 0;
        for(int i = 0; i < olength; i++){
            
            if(operands.charAt(i) == 'H'){
                stack.push(HYDROGEN);
            }else if(operands.charAt(i) == 'C'){
                stack.push(CARBON);
            }else if(operands.charAt(i) == 'O'){
                stack.push(OXYGEN);
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

        }
        



        return stack.pop();
    }

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

   
}

