package com.assignment.weather.home;

import com.assignment.weather.common.ModelDataLoadedCallback;
import com.assignment.weather.common.pojos.DayForecast;
import com.assignment.weather.common.pojos.Forecasts;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HomePresenterTest {

    private HomeContract.Presenter homePresenter;
    @Mock
    private HomeContract.View view;
    @Mock
    private HomeContract.Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        homePresenter = new HomePresenter(model, view);
    }

    @Test
    public void shouldLaunchDetailsScreenWhenListItemIsClicked() {
        DayForecast dayForecast = any(DayForecast.class);
        homePresenter.onDayForecastClicked(dayForecast);
        verify(view, times(1)).showDayForecastDetailsScreen(dayForecast);
    }

    @Test
    public void shouldFetchForecastsWhenLocationIsObtained() {
        homePresenter.onLocationObtained(0.0, 0.0);
        verify(model, times(1))
                .getForecasts(anyDouble(), anyDouble(), any(ModelDataLoadedCallback.class));
    }

    @Test
    public void shouldShowContentWhenForecastsAreLoaded() {
        ArgumentCaptor<ModelDataLoadedCallback> captor = ArgumentCaptor.forClass(ModelDataLoadedCallback.class);
        homePresenter.onLocationObtained(0.0, 0.0);
        verify(model).getForecasts(anyDouble(), anyDouble(), captor.capture());

        Forecasts testForecasts = new Forecasts();
        List<DayForecast> dayForecastList = new ArrayList<>();
        dayForecastList.add(new DayForecast());
        testForecasts.setDayForecastList(dayForecastList);

        captor.getValue().onSuccess(testForecasts);

        verify(view, times(1)).setContentVisibility(true);
        verify(view, times(1)).setTodayDetails(any(DayForecast.class));
        verify(view, times(1)).setNext10DaysForecasts(anyList());
    }
}
