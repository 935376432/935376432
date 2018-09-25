package com.test.weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class WeatherTest {
    @Test
    public void test() {
        Weather1 weather = new Weather1();
        weather.setTemperature(32);
        assertEquals("HOT", weather.getFeeling());
        weather.setTemperature(25);
        assertEquals("GOOD", weather.getFeeling());
        weather.setTemperature(18);
        assertEquals("GOOD", weather.getFeeling());
        weather.setTemperature(10);
        assertEquals("COLD", weather.getFeeling());
        weather.setTemperature(-1);
        assertEquals("FROZEN", weather.getFeeling());
        weather.setTemperature(-101);
        try {
            weather.getFeeling();
            fail("An exception should be thrown");
        } catch (RuntimeException re) {
            assertTrue(re.getMessage().startsWith("Invalid Temperature:"));
        }
        weather.setTemperature(120);
        try {
            weather.getFeeling();
            fail("An exception should be thrown");
        } catch (RuntimeException re) {
            assertTrue(re.getMessage().startsWith("Invalid Temperature:"));
        }
    }
}