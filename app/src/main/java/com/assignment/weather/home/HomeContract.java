package com.assignment.weather.home;

import com.assignment.weather.common.ModelDataLoadedCallback;
import com.assignment.weather.common.pojos.DayForecast;
import com.assignment.weather.common.pojos.Forecasts;

import java.util.List;

public interface HomeContract {

    interface Model {
        void getForecasts(double lat,
                          double lon,
                          ModelDataLoadedCallback<Forecasts> callback);
    }

    interface View {
        void setContentVisibility(boolean isVisible);
        void setProgressVisibility(boolean isVisible);
        void showFetchError();
        void setCityAndCountry(String cityAndCountry);

        void setTodayDetails(DayForecast todaysForecast);
        void setNext10DaysForecasts(List<DayForecast> next10DaysList);
        void showDayForecastDetailsScreen(DayForecast dayForecast);
    }

    interface Presenter {
        void onLocationObtained(double latitude, double longitude);
        void onDayForecastClicked(DayForecast dayForecast);
    }
}
