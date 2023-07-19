package org.verifier.stockVerifier;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PriceLevel {
    public Long price;
    private final Deque<Order> orders  = new LinkedList<>();

    public Deque<Order> getOrders() {
        return orders;
    }

    public PriceLevel(Long price) {
        this.price = price;
    }

    public Boolean isOrderEmpty() {
        return orders.isEmpty();
    }

    // return how much quanity is left
    public void executeTrades(Order orderToExecute, List<Trade> trades) {
        while (orderToExecute.getRemainingQuantity() > 0 && !orders.isEmpty()) {
           Order order = orders.peekFirst();
           long availableQuantity = order.getRemainingQuantity();
           long executableQuantity = orderToExecute.getRemainingQuantity();
            if (availableQuantity <= executableQuantity) {
               order.reduce(availableQuantity);
               orderToExecute.reduce(availableQuantity);
               orders.pollFirst();
               trades.add(new Trade(orderToExecute.getOrderId(), order.getOrderId(), this.price, availableQuantity));
           } else {
               order.reduce(executableQuantity);
               orderToExecute.reduce(executableQuantity);
               trades.add(new Trade(orderToExecute.getOrderId(), order.getOrderId(), this.price, executableQuantity));
           }
        }
    }
    public void addOrder(Order order) {
        orders.addLast(order);
    }
}

