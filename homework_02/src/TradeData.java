class TradeData {
    private final String type;
    private final double price;

    TradeData(String type, double price) {
        this.type = type;
        this.price = price;
    }

    String getType() {
        return type;
    }

    double getPrice() {
        return price;
    }
}
