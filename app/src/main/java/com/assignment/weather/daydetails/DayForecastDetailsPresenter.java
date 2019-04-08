package com.assignment.weather.daydetails;

import com.assignment.weather.common.Utils;
import com.assignment.weather.common.pojos.DayForecast;

import java.lang.ref.WeakReference;

public class DayForecastDetailsPresenter implements DayForecastDetailsContract.Presenter {

    private WeakReference<DayForecastDetailsContract.View> viewWeakReference;

    DayForecastDetailsPresenter(DayForecastDetailsContract.View view) {
        this.viewWeakReference = new WeakReference<>(view);
    }

    @Override
    public void onLoad(DayForecast dayForecast) {

        if(viewWeakReference.get() == null){
            return;
        }

        DayForecastDetailsContract.View view = viewWeakReference.get();

        view.setHeaderDetails(
                Utils.formatDate(dayForecast.getDate()),
                dayForecast.getTempAverage(),
                dayForecast.getDescription(),
                dayForecast.getIcon());

        view.setWindDetails(dayForecast.getWindGustSpeed(),
                dayForecast.getWindSpeed(),
                dayForecast.getWindDirection());

        view.setTemperatureDetails(dayForecast.getTempAverage(),
                dayForecast.getTempMax(),
                dayForecast.getTempMin(),
                dayForecast.getTempAppMax(),
                dayForecast.getTempAppMin());

        view.setOtherDetails(dayForecast.getChanceOfRain(),
                dayForecast.getPressure(),
                dayForecast.getHumidity());
    }

    @Override
    public void onBackButtonClicked() {
        if(viewWeakReference.get() != null){
            viewWeakReference.get().exitScreen();
        }
    }
}
