package org.verifier.stockVerifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberFormatterTest {

    @Test
    void formatQuantity() {
        assertEquals(NumberFormatter.formatQuantity(1243), "      1,243");
    }

    @Test
    void formatPrice() {
        assertEquals(NumberFormatter.formatPrice(123), "   123");
    }
}