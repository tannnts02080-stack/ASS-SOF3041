package com.lab8;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatorTest {

    Calculator c = new Calculator();

    @Test
    public void testAdd() {
        Assert.assertEquals(c.add(2, 3), 5);
    }

    @Test
    public void testSub() {
        Assert.assertEquals(c.sub(5, 3), 2);
    }

    @Test
    public void testMul() {
        Assert.assertEquals(c.mul(4, 2), 8);
    }

    @Test
    public void testDiv() {
        Assert.assertEquals(c.div(10, 2), 5);
    }
}
