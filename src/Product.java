public class Product {

    static final int Name_length =35;
    static int Description_length = 75;
    static int ID_Length = 6;

    String name;
    String description;
    String ID;
    double cost;

    public Product(String name, String description, String ID, double cost) {
        this.name = formatString(name, Name_length);
        this.description = formatString(description, Description_length);
        this.ID = formatString(ID,ID_Length);
        this.cost = cost;
    }
    private String formatString(String input, int length) {
        return String.format("%-" + length + "s", input).substring(0, length);
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return ID;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Description: %s, Cost: %.2f", ID, name, description, cost);
    }
}


