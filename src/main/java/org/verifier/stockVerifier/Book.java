package org.verifier.stockVerifier;

import java.util.TreeMap;

public class Book {
    private final String stockName;
    private final Side side;
    private final TreeMap<Long, PriceLevel> limitMap = new TreeMap<>();

    public TreeMap<Long, PriceLevel> getLimitMap() {
        return limitMap;
    }

    public Book(String stockName, Side side) {
        this.stockName = stockName;
        this.side = side;
    }

    public PriceLevel getLowestPriceLevel() {
        if (limitMap.size() == 0) {
            return null;
        }
        return limitMap.firstEntry().getValue();
    }

    public PriceLevel getHighestPriceLevel() {
        if (limitMap.size() == 0) {
            return null;
        }
        return limitMap.lastEntry().getValue();
    }

    public void addOrder(Order order) {
        if (!limitMap.containsKey(order.getPrice())) {
            limitMap.put(order.getPrice(), new PriceLevel(order.getPrice()));
        }
        limitMap.get(order.getPrice()).addOrder(order);
    }
}
