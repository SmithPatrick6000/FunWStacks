//import student.IntStack;

public class Main {
    

    public static void main(String[] args) {
        IntStack Animal = new IntStack();



        // Animal.push(1);
        // int saved = Animal.peek();
        // System.out.println(saved);
        // saved = Animal.pop();
        // System.out.println(saved);

        for(int i = 0; i < 10; i++){
            System.out.println(i);
            Animal.push(i);
        }
        for(int i = 0; i < 10; i++){
            System.out.println(Animal.pop());
        }
    }
}
