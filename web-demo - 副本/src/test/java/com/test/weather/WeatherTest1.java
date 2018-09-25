/*
 *jiji java
 */
package com.test.weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WeatherTest1 {

    private Weather weather;

    @Before
    public void setUp() throws Exception {
        weather = new Weather();
    }

    @After
    public void tearDown() throws Exception {
        weather = null;
    }

    @Test
    public void test01() {
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
    }

    @Test(timeout = 10)
    public void test02() {
        weather.setTemperature(-101);
        try {
            weather.getFeeling();
            fail("An exception should be thrown");
        } catch (RuntimeException re) {
            assertTrue(re.getMessage().startsWith("Invalid Temperature:"));
        }
    }

    @Test(expected = RuntimeException.class)
    public void test03() {
        weather.setTemperature(120);
        weather.getFeeling();
    }

    @Ignore
    @Test
    public void test04() {
        throw new RuntimeException();
    }
}
