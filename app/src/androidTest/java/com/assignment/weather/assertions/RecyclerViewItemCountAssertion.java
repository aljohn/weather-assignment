package com.assignment.weather.assertions;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecyclerViewItemCountAssertion implements ViewAssertion {

    private int expectedItemCount;

    public RecyclerViewItemCountAssertion(int expectedItemCount) {
        this.expectedItemCount = expectedItemCount;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null){
            assertThat(adapter.getItemCount(), is(expectedItemCount));
        }
    }
}
