package com.assignment.weather;

import android.Manifest;

import com.assignment.weather.assertions.RecyclerViewItemCountAssertion;
import com.assignment.weather.common.espresso.EspressoTestingIdlingResource;
import com.assignment.weather.daydetails.DayForecastDetailsActivity;
import com.assignment.weather.home.HomeActivity;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class HomeUITest {

    @Rule
    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);
    @Rule
    public GrantPermissionRule locationPermissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoTestingIdlingResource.getIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoTestingIdlingResource.getIdlingResource());
    }

    @Test
    public void isTodaysWeatherDisplayed() {
        onView(withId(R.id.container_today)).check(matches(isDisplayed()));
    }

    @Test
    public void areForecastsForTheNext10DaysDisplayed() {
        onView(withId(R.id.rv_forecast)).check(new RecyclerViewItemCountAssertion(10));
    }

    @Test
    public void shouldLaunchDetailsScreenWhenItemIsClicked() {
        Intents.init();
        onView(withId(R.id.rv_forecast))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        intended(hasComponent(DayForecastDetailsActivity.class.getName()));
        Intents.release();
    }
}
