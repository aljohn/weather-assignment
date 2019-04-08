package com.assignment.weather.home;

import com.assignment.weather.common.ModelDataLoadedCallback;
import com.assignment.weather.common.espresso.EspressoTestingIdlingResource;
import com.assignment.weather.common.pojos.DayForecast;
import com.assignment.weather.common.pojos.Forecasts;

import java.lang.ref.WeakReference;
import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.Model model;
    private WeakReference<HomeContract.View> viewWeakReference;

    HomePresenter(HomeContract.Model model, HomeContract.View view) {
        this.model = model;
        this.viewWeakReference = new WeakReference<>(view);
    }

    @Override
    public void onLocationObtained(double latitude, double longitude) {
        EspressoTestingIdlingResource.increment();
        model.getForecasts(latitude, longitude, new ModelDataLoadedCallback<Forecasts>() {
            @Override
            public void onSuccess(Forecasts data) {

                if(viewWeakReference.get() == null) {
                    return;
                }

                HomeContract.View view = viewWeakReference.get();

                view.setProgressVisibility(false);
                view.setContentVisibility(true);

                view.setCityAndCountry(data.getCity() + ", " + data.getCountry());

                if (data.getDayForecastList().size() > 0) {
                    view.setTodayDetails(data.getDayForecastList().get(0));

                    List<DayForecast> next10DaysList = data.getDayForecastList()
                            .subList(1, data.getDayForecastList().size());
                    view.setNext10DaysForecasts(next10DaysList);
                }

                EspressoTestingIdlingResource.decrement();
            }

            @Override
            public void onError() {
                if (viewWeakReference.get() != null) {
                    viewWeakReference.get().showFetchError();
                }
            }
        });
    }

    @Override
    public void onDayForecastClicked(DayForecast dayForecast) {
        if (viewWeakReference.get() != null) {
            viewWeakReference.get().showDayForecastDetailsScreen(dayForecast);
        }
    }
}
