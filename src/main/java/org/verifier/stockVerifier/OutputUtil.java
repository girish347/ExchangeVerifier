package org.verifier.stockVerifier;

import java.util.*;

public class OutputUtil {
    public static String printTrades(List<Trade> trades, Book buyOrders, Book sellOrders)  {
        StringBuilder sb = new StringBuilder();
        for (Trade trade : trades) {
            sb.append("trade ").append(trade.order1())
                    .append(",")
                    .append(trade.order2())
                    .append(",")
                    .append(trade.price())
                    .append(",")
                    .append(trade.quantity())
                    .append("\n");
        }
        Iterator<Map.Entry<Long, PriceLevel>> buyiterator = buyOrders.getLimitMap().entrySet().iterator();
        Iterator<Map.Entry<Long, PriceLevel>> selliterator = sellOrders.getLimitMap().entrySet().iterator();
        List<Order> sellOrder = new ArrayList<>();
        List<Order> buyOrder = new ArrayList<>();

        while(selliterator.hasNext()) {
            sellOrder.addAll(selliterator.next().getValue().getOrders());
        }
        while(buyiterator.hasNext()) {
            List<Order> list = new ArrayList<>(buyiterator.next().getValue().getOrders());
            Collections.reverse(list);
            buyOrder.addAll(list);
        }
        Collections.reverse(buyOrder);
        int index =0 ;
        while(index < buyOrder.size()  && index < sellOrder.size()) {
            Order b = buyOrder.get(index);
            Order s = sellOrder.get(index);

            sb.append(NumberFormatter.formatQuantity(b.getRemainingQuantity()));
            sb.append(" ");
            sb.append(NumberFormatter.formatPrice(b.getPrice()));
            sb.append(" | ");
            sb.append(NumberFormatter.formatPrice(s.getPrice()));
            sb.append(" ");
            sb.append(NumberFormatter.formatQuantity(s.getRemainingQuantity()));
            sb.append("\n");
            index++;
        }
        String space = "";
        for (int i=0; i < 18; i++) {
            space = space + " ";
        }

        while(index < buyOrder.size() ) {
            Order b = buyOrder.get(index);
            sb.append(NumberFormatter.formatQuantity(b.getRemainingQuantity()));
            sb.append(" ");
            sb.append(NumberFormatter.formatPrice(b.getPrice()));
            sb.append(" | ");
            sb.append(space);
            sb.append("\n");
            index++;
        }

        while(index < sellOrder.size()) {
            Order s = sellOrder.get(index);

            sb.append(space);
            sb.append(" | ");
            sb.append(NumberFormatter.formatPrice(s.getPrice()));
            sb.append(" ");
            sb.append(NumberFormatter.formatQuantity(s.getRemainingQuantity()));
            index++;
            sb.append("\n");
        }


        System.out.println(sb);

        return sb.toString();
    }
}
