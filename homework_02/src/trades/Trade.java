package trades;

public class Trade {
    private final double price;

    public Trade(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Trade " +
                this.getClass().getSimpleName() +
                ", price=" + price;
    }
}
