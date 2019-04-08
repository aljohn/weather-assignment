package com.assignment.weather.common.network.models;

import com.google.gson.annotations.SerializedName;

public class DayForecastReturn {

    @SerializedName("valid_date")
    private String date;
    @SerializedName("wind_gust_spd")
    private double windGustSpeed;
    @SerializedName("wind_spd")
    private double windSpeed;
    @SerializedName("wind_cdir")
    private String windDirection;
    @SerializedName("temp")
    private double temperature;
    @SerializedName("max_temp")
    private double maxTemperature;
    @SerializedName("min_temp")
    private double minTemperature;
    @SerializedName("app_max_temp")
    private double apparentMaxTemperature;
    @SerializedName("app_min_temp")
    private double apparentMinTemperature;
    @SerializedName("precip")
    private double precipitation;
    @SerializedName("weather")
    private WeatherReturn weather;
    @SerializedName("rh")
    private double humidity;
    @SerializedName("pres")
    private double pressure;

    public String getDate() {
        return date;
    }

    public double getWindGustSpeed() {
        return windGustSpeed;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getApparentMaxTemperature() {
        return apparentMaxTemperature;
    }

    public double getApparentMinTemperature() {
        return apparentMinTemperature;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public WeatherReturn getWeather() {
        return weather;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }
}
