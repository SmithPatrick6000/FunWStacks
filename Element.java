public class Element {
    private int id_number;
    private String symbol;
    private String name;
    private int mass;

//Still have variables for number and name for potential future uses in data set
    public Element(int id_number, String symbol, String name, int mass){
        this.id_number = id_number;
        this.symbol = symbol;
        this.name = name;
        this.mass = mass;
    }

    public int getID(){
        return id_number;
    }
    public String getSymbol(){
        return symbol;
    }
    public String getName(){
        return name;
    }
    public double getMass(){
        return mass;
    }

    public void setID(int id_number){
        this.id_number = id_number;
    }
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setMass(int mass){
        this.mass = mass;
    }
}
