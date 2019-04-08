package com.assignment.weather.daydetails;

import com.assignment.weather.common.pojos.DayForecast;

public interface DayForecastDetailsContract {

    interface View {
        void setHeaderDetails(String date, double temperature, String forecast, String icon);
        void setWindDetails(double windGustSpeed, double windSpeed, String windDirection);
        void setTemperatureDetails(double tempAverage, double tempMax, double tempMin, double tempAppMax, double tempAppMin);
        void setOtherDetails(double chanceOfRain, double pressure, double humidity);
        void exitScreen();
    }

    interface Presenter {
        void onLoad(DayForecast dayForecast);
        void onBackButtonClicked();
    }
}
