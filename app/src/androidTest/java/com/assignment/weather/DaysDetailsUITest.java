package com.assignment.weather;

import android.content.Intent;

import com.assignment.weather.common.pojos.DayForecast;
import com.assignment.weather.daydetails.DayForecastDetailsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class DaysDetailsUITest {

    @Rule
    public ActivityTestRule<DayForecastDetailsActivity> activityRule = new ActivityTestRule<DayForecastDetailsActivity>(DayForecastDetailsActivity.class) {
        @Override
        protected Intent getActivityIntent() {
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

            return DayForecastDetailsActivity
                    .getIntent(InstrumentationRegistry.getInstrumentation().getTargetContext(), dayForecast);
        }
    };

    @Test
    public void areForecastDetailsDisplayed() {
        onView(withId(R.id.txt_date)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_degrees)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_weather)).check(matches(isDisplayed()));
        onView(withId(R.id.img_weather_icon)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_wind_gust_speed)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_wind_speed)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_wind_direction)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_temp_average)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_temp_max)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_temp_min)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_temp_app_max)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_temp_app_min)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_precipitation)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_pressure)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_humidity)).check(matches(isDisplayed()));
    }
}
