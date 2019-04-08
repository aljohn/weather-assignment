package com.assignment.weather.home;

import com.assignment.weather.common.ModelDataLoadedCallback;
import com.assignment.weather.common.network.WeatherServiceRequests;
import com.assignment.weather.common.network.models.DayForecastReturn;
import com.assignment.weather.common.network.models.ForecastsReturn;
import com.assignment.weather.common.pojos.DayForecast;
import com.assignment.weather.common.pojos.Forecasts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeModel implements HomeContract.Model{

    private static final int DAYS = 11;

    @Override
    public void getForecasts(double lat, double lon, final ModelDataLoadedCallback<Forecasts> callback) {
        WeatherServiceRequests.getInstance()
                .getForecasts(DAYS, lat, lon)
                .enqueue(new Callback<ForecastsReturn>() {
                    @Override
                    public void onResponse(Call<ForecastsReturn> call, Response<ForecastsReturn> response) {
                        if (response.isSuccessful()){
                            ForecastsReturn forecastsReturn = response.body();
                            if (forecastsReturn != null) {

                                List<DayForecast> dayForecastList = new ArrayList<>();

                                for(DayForecastReturn day: forecastsReturn.getForecasts()) {
                                    dayForecastList.add(new DayForecast()
                                            .setDate(day.getDate())
                                            .setIcon(day.getWeather().getIcon())
                                            .setDescription(day.getWeather().getDescription())
                                            .setWindGustSpeed(day.getWindGustSpeed())
                                            .setWindSpeed(day.getWindSpeed())
                                            .setWindDirection(day.getWindDirection())
                                            .setTempAverage(day.getTemperature())
                                            .setTempMax(day.getMaxTemperature())
                                            .setTempMin(day.getMinTemperature())
                                            .setTempAppMax(day.getApparentMaxTemperature())
                                            .setTempAppMin(day.getApparentMinTemperature())
                                            .setChanceOfRain(day.getPrecipitation())
                                            .setPressure(day.getPressure())
                                            .setHumidity(day.getHumidity()));
                                }

                                Forecasts forecasts = new Forecasts()
                                        .setCity(forecastsReturn.getCityName())
                                        .setCountry(forecastsReturn.getCountryCode())
                                        .setDayForecastList(dayForecastList);

                                callback.onSuccess(forecasts);

                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastsReturn> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
}
