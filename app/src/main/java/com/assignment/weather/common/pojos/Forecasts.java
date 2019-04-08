package com.assignment.weather.common.pojos;

import java.util.List;

public class Forecasts {

    private List<DayForecast> dayForecastList;
    private String city;
    private String country;

    public List<DayForecast> getDayForecastList() {
        return dayForecastList;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Forecasts setDayForecastList(List<DayForecast> dayForecastList) {
        this.dayForecastList = dayForecastList;
        return this;
    }

    public Forecasts setCity(String city) {
        this.city = city;
        return this;
    }

    public Forecasts setCountry(String country) {
        this.country = country;
        return this;
    }
}
