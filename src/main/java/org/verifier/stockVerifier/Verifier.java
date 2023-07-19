package org.verifier.stockVerifier;

import java.util.Scanner;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Verifier {
    public static void main(String[] args) {
        OrderBook orderBook = new OrderBook();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] orderData = line.split(",");
            if (orderData.length < 3) break;
            String orderId = orderData[0];
            Side side = orderData[1].charAt(0) == 'B' ? Side.BUY : Side.SELL;
            int price = Integer.parseInt(orderData[2]);
            int quantity = Integer.parseInt(orderData[3]);

            Order order = new Order(orderId,  price, side, quantity);
            orderBook.processOrder(order);
        }
        OutputUtil.printTrades(orderBook.trades, orderBook.getBuyOrders(), orderBook.getSellOrders());
    }
}