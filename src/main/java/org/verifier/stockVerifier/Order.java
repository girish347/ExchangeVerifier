package org.verifier.stockVerifier;

public class Order {
    private final String orderId;
    private final Long price;
    private final Side side;
    private long remainingQuantity;
    private final long quantity;

    public Order(String orderId, long price, Side side, long quantity) {
        this.orderId = orderId;
        this.price = price;
        this.side = side;
        this.quantity  = quantity;
        this.remainingQuantity = quantity;
    }
    void reduce(long quantity) {
        remainingQuantity -= quantity;
    }
    public long getRemainingQuantity() {
        return remainingQuantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public long getPrice() {
        return price;
    }

    public Side getSide() {
        return side;
    }

}


record Trade(String order1, String order2, long price, long quantity) {
}