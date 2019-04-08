package com.assignment.weather.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;

import com.assignment.weather.common.Utils;
import com.assignment.weather.common.espresso.EspressoTestingIdlingResource;
import com.assignment.weather.common.pojos.DayForecast;
import com.assignment.weather.daydetails.DayForecastDetailsActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.assignment.weather.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.List;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeContract.View, HomeForecastsListAdapter.ItemClickListener {

    private static final int REQUEST_CODE_GOOGLE_PLAY_SERVICES = 0;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    @BindView(R.id.container_main)
    View mainContainer;
    @BindView(R.id.container_content)
    View containerContent;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.txt_city_country)
    TextView txtCityCountry;
    @BindView(R.id.txt_today_weather)
    TextView txtTodayWeather;
    @BindView(R.id.txt_today_temp)
    TextView txtTodayTemp;
    @BindView(R.id.img_weather_icon)
    ImageView imgWeatherIcon;
    @BindView(R.id.rv_forecast)
    RecyclerView rvForecast;

    private HomeContract.Presenter presenter;
    private HomeForecastsListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new HomePresenter(new HomeModel(), this);

        listAdapter = new HomeForecastsListAdapter();
        listAdapter.setOnItemClickListener(this);

        rvForecast.setLayoutManager(new LinearLayoutManager(this));
        rvForecast.setAdapter(listAdapter);
        rvForecast.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(checkGooglePlayServices()) {
            if(checkLocationPermission()) {
                getLastKnownLocation();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION) {
            if(grantResults.length > 0){
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getLastKnownLocation();
                } else {
                    showLocationPermissionRationale();
                }
            }
        }
    }

    private boolean checkGooglePlayServices() {
        int resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            GoogleApiAvailability.getInstance()
                    .getErrorDialog(this, resultCode, REQUEST_CODE_GOOGLE_PLAY_SERVICES).show();
            return false;
        }
        return true;
    }

    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                showLocationPermissionRationale();
                return false;
            } else {
                requestLocationPermission();
                return false;
            }
        }
        return true;
    }

    private void showLocationPermissionRationale() {
        Snackbar.make(mainContainer, R.string.location_permission_rationale, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok, view -> requestLocationPermission())
                .setActionTextColor(ResourcesCompat.getColor(getResources(), R.color.textColorAccent, getTheme()))
                .show();
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_CODE_LOCATION_PERMISSION);
    }

    @SuppressLint("MissingPermission")
    private void getLastKnownLocation() {
        EspressoTestingIdlingResource.increment();
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        client.getLastLocation()
                .addOnCompleteListener(this, task -> {
                    if(task.getResult() != null) {
                        Location location = task.getResult();
                        presenter.onLocationObtained(location.getLatitude(), location.getLongitude());
                    }
                    EspressoTestingIdlingResource.decrement();
                });
    }

    @Override
    public void setContentVisibility(boolean isVisible) {
        containerContent.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setProgressVisibility(boolean isVisible) {
        pbLoading.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showFetchError() {
        Snackbar.make(mainContainer, R.string.failed_fetching_weather_data, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.try_again, view -> getLastKnownLocation())
                .setActionTextColor(ResourcesCompat.getColor(getResources(), R.color.textColorAccent, getTheme()))
                .show();
    }

    @Override
    public void setCityAndCountry(String cityAndCountry) {
        txtCityCountry.setText(cityAndCountry);
    }

    @Override
    public void setTodayDetails(DayForecast todaysForecast) {
        txtTodayWeather.setText(todaysForecast.getDescription());
        txtTodayTemp.setText(getString(R.string.temp_degrees, String.valueOf(todaysForecast.getTempAverage())));
        imgWeatherIcon.setImageResource(Utils.getDrawableResByName(getResources(), todaysForecast.getIcon(), getPackageName()));
    }

    @Override
    public void setNext10DaysForecasts(List<DayForecast> next10DaysList) {
        listAdapter.setData(next10DaysList);
    }

    @Override
    public void showDayForecastDetailsScreen(DayForecast dayForecast) {
        DayForecastDetailsActivity.launch(this, dayForecast);
    }

    @Override
    public void onClick(DayForecast dayForecast) {
        presenter.onDayForecastClicked(dayForecast);
    }
}
