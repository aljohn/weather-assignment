package com.assignment.weather.daydetails;

import com.assignment.weather.common.Utils;
import com.assignment.weather.common.pojos.DayForecast;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DayForecastDetailsPresenterTest {

    @Mock
    private DayForecastDetailsContract.View view;
    private DayForecastDetailsPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new DayForecastDetailsPresenter(view);
    }

    @Test
    public void shouldSetForecastDetailsOnScreenLoad() {
        String date = "2019-12-12";
        String icon = "icon";
        String desc = "desc";
        String windDirection = "NE";

        DayForecast dayForecast = new DayForecast()
                .setDate(date)
                .setTempAverage(25.0)
                .setIcon(icon)
                .setDescription(desc)
                .setWindGustSpeed(10)
                .setWindSpeed(5)
                .setWindDirection(windDirection)
                .setTempMax(30)
                .setTempMin(28)
                .setTempAppMax(31)
                .setTempAppMin(29)
                .setChanceOfRain(100)
                .setPressure(99)
                .setHumidity(88);

        presenter.onLoad(dayForecast);

        verify(view, times(1))
                .setHeaderDetails(Utils.formatDate(date), 25.0, desc, icon);
        verify(view, times(1))
                .setWindDetails(10, 5, windDirection);
        verify(view, times(1)).setTemperatureDetails(25.0, 30, 28, 31, 29);
        verify(view, times(1)).setOtherDetails(100, 99, 88);
    }

    @Test
    public void shouldExitScreenWhenBackButtonIsClicked() {
        presenter.onBackButtonClicked();
        verify(view).exitScreen();
    }
}
