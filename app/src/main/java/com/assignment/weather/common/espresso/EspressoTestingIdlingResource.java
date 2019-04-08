package com.assignment.weather.common.espresso;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;

public class EspressoTestingIdlingResource {

    private static CountingIdlingResource mCountingIdlingResource =
            new CountingIdlingResource("globalIdlingResource");

    public static void increment() {
        mCountingIdlingResource.increment();
    }

    public static void decrement() {
        mCountingIdlingResource.decrement();
    }

    public static IdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }

}
