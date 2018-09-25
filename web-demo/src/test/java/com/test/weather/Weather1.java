package com.test.weather;
public class Weather1 {

    private int temperature;
    public int getTemperature() {
        return temperature;
    }
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getFeeling() {
        if (temperature > 100 || temperature < -100) {
            throw new RuntimeException("Invalid Temperature: " + temperature);
        }

        if (temperature > 25) {
            return "HOT";
        } else if (temperature >= 18) {
            return "GOOD";
        } else if (temperature > 0) {

            return "COLD";
        } else {
            return "FROZEN";
        }
    }
}