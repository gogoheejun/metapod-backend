package com.metapod.api.rest.demo.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MultilineTextTest {

    @Test
    void creation() {
        MultilineText multilineText = new MultilineText("1\n2\n3");

        Assertions.assertEquals("1\n2\n3", multilineText.toString());
    }

}