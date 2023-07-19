package org.verifier.stockVerifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MD5HashTest {

    @Test
    void calculateMD5() {
        assertEquals( MD5Hash.calculateMD5("123"), "202cb962ac59075b964b07152d234b70");
    }
}