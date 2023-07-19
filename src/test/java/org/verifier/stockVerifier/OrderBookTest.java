package org.verifier.stockVerifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderBookTest {

    @Test
    void processOrder() {
        OrderBook orderBook = new OrderBook();
        orderBook.processOrder(new Order("10000", 98, Side.BUY, 25500));
        orderBook.processOrder(new Order("10005", 105, Side.SELL, 20000));
        orderBook.processOrder(new Order("10001", 100, Side.SELL, 500));

        orderBook.processOrder(new Order("10002", 100, Side.SELL, 10000));
        orderBook.processOrder(new Order("10003", 99, Side.BUY, 50000));
        orderBook.processOrder(new Order("10004", 103, Side.SELL, 100));

        String ans = OutputUtil.printTrades(orderBook.trades, orderBook.getBuyOrders(), orderBook.getSellOrders());
        assertEquals(ans, "     50,000     99 |    100         500\n" +
                "     25,500     98 |    100      10,000\n" +
                "                   |    103         100\n" +
                "                   |    105      20,000\n");

        assertEquals(MD5Hash.calculateMD5(ans), "8ff13aad3e61429bfb5ce0857e846567");
    }

    @Test
    void processOrder2() {
        OrderBook orderBook = new OrderBook();
        orderBook.processOrder(new Order("10000", 98, Side.BUY, 25500));
        orderBook.processOrder(new Order("10005", 105, Side.SELL, 20000));
        orderBook.processOrder(new Order("10001", 100, Side.SELL, 500));

        orderBook.processOrder(new Order("10002", 100, Side.SELL, 10000));
        orderBook.processOrder(new Order("10003", 99, Side.BUY, 50000));
        orderBook.processOrder(new Order("10004", 103, Side.SELL, 100));

        orderBook.processOrder(new Order("10006", 105, Side.BUY, 16000));

        String ans = OutputUtil.printTrades(orderBook.trades, orderBook.getBuyOrders(), orderBook.getSellOrders());
        assertEquals(ans, "trade 10006,10001,100,500\n" +
                "trade 10006,10002,100,10000\n" +
                "trade 10006,10004,103,100\n" +
                "trade 10006,10005,105,5400\n" +
                "     50,000     99 |    105      14,600\n" +
                "     25,500     98 |                   \n");

        assertEquals(MD5Hash.calculateMD5(ans), "ce8e7e5ab26ab5a7db6b7d30759cf02e");
    }
}