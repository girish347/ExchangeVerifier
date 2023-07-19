package org.verifier.stockVerifier;

import java.util.ArrayList;
import java.util.List;

class OrderBook {
    List<Trade> trades = new ArrayList<>();
    private final Book buyOrders;
    private final Book sellOrders;

    public Book getBuyOrders() {
        return buyOrders;
    }

    public Book getSellOrders() {
        return sellOrders;
    }

    public OrderBook() {
        buyOrders = new Book("default", Side.BUY);
        sellOrders = new Book("default", Side.SELL);
    }

    public void processOrder(Order order) {
        if (order.getSide() == Side.BUY) {
            processBuyOrder(order);
        } else if (order.getSide() == Side.SELL) {
            processSellOrder(order);
        }
    }

    private void processBuyOrder(Order order) {
        do {
            PriceLevel priceLevel = sellOrders.getLowestPriceLevel();
            if (priceLevel == null) break;

            if (priceLevel.price <= order.getPrice()) {
                priceLevel.executeTrades(order, trades);
            } else {
                break;
            }
            if (priceLevel.isOrderEmpty()) {
                sellOrders.getLimitMap().remove(priceLevel.price);
            }
        } while (order.getRemainingQuantity() > 0);

        if (order.getRemainingQuantity() > 0) {
            buyOrders.addOrder(order);
        }
    }
    private void processSellOrder(Order order) {
        List<Trade> trades = new ArrayList<>();
        do {
            PriceLevel priceLevel = buyOrders.getHighestPriceLevel();
            if (priceLevel == null) break;
            if (priceLevel.price >= order.getPrice()) {
                priceLevel.executeTrades(order, trades);
            } else {
                break;
            }
            if (priceLevel.isOrderEmpty()) {
                buyOrders.getLimitMap().remove(priceLevel.price);
            }
        } while (order.getRemainingQuantity() > 0);

        if (order.getRemainingQuantity() > 0) {
            sellOrders.addOrder(order);
        }
    }
}