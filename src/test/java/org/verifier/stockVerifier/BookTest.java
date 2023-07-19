package org.verifier.stockVerifier;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @org.junit.jupiter.api.Test
    void getLowestPriceLevel() {
        Book book = new Book("", Side.BUY);
        book.addOrder(new Order("123", 1, Side.BUY, 2));
        assertEquals(book.getLowestPriceLevel().price,1);
    }

    @org.junit.jupiter.api.Test
    void getHighestPriceLevel() {
        Book book = new Book("", Side.BUY);
        book.addOrder(new Order("123", 1, Side.BUY, 2));
        book.addOrder(new Order("123", 5, Side.BUY, 2));


        assertEquals(book.getHighestPriceLevel().price,5);
    }


}