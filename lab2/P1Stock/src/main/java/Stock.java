public class Stock {

    private String label;
    private float quantity;

    public Stock(String label, float quantity) {
        this.label = label;
        this.quantity = quantity;
    }

    public String getLabel() {
        return label;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
