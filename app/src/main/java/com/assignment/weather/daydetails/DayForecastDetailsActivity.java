package com.assignment.weather.daydetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.weather.R;
import com.assignment.weather.common.Utils;
import com.assignment.weather.common.pojos.DayForecast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DayForecastDetailsActivity extends AppCompatActivity implements DayForecastDetailsContract.View {

    private static final String KEY_DAY_FORECAST = "key_day_forecast";

    public static Intent getIntent(Context context, DayForecast dayForecast) {
        Intent intent = new Intent(context, DayForecastDetailsActivity.class);
        intent.putExtra(KEY_DAY_FORECAST, dayForecast);
        return intent;
    }

    public static void launch(Context context, DayForecast dayForecast) {
        context.startActivity(getIntent(context, dayForecast));
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.txt_date)
    TextView txtDate;
    @BindView(R.id.txt_degrees)
    TextView txtDegrees;
    @BindView(R.id.txt_weather)
    TextView txtWeather;
    @BindView(R.id.img_weather_icon)
    ImageView imgWeatherIcon;

    @BindView(R.id.txt_wind_gust_speed)
    TextView txtWindGustSpeed;
    @BindView(R.id.txt_wind_speed)
    TextView txtWindSpeed;
    @BindView(R.id.txt_wind_direction)
    TextView txtWindDirection;

    @BindView(R.id.txt_temp_average)
    TextView txtAverageTemp;
    @BindView(R.id.txt_temp_max)
    TextView txtMaxTemp;
    @BindView(R.id.txt_temp_min)
    TextView txtMinTemp;
    @BindView(R.id.txt_temp_app_max)
    TextView txtMaxAppTemp;
    @BindView(R.id.txt_temp_app_min)
    TextView txtMinAppTemp;

    @BindView(R.id.txt_precipitation)
    TextView txtPrecipitation;
    @BindView(R.id.txt_pressure)
    TextView txtPressure;
    @BindView(R.id.txt_humidity)
    TextView txtHumidity;

    private DayForecastDetailsContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_forecast_details);
        ButterKnife.bind(this);

        toolbar.setNavigationOnClickListener(view -> {
            presenter.onBackButtonClicked();
        });

        presenter = new DayForecastDetailsPresenter(this);
        presenter.onLoad(getIntent().getParcelableExtra(KEY_DAY_FORECAST));
    }

    @Override
    public void setHeaderDetails(String date, double temperature, String forecast, String icon) {
        txtDate.setText(date);
        txtDegrees.setText(getString(R.string.temp_degrees, String.valueOf(temperature)));
        txtWeather.setText(forecast);
        imgWeatherIcon.setImageResource(Utils.getDrawableResByName(getResources(), icon, getPackageName()));
    }

    @Override
    public void setWindDetails(double windGustSpeed, double windSpeed, String windDirection) {
        txtWindGustSpeed.setText(String.valueOf(Utils.roundOff(windGustSpeed)));
        txtWindSpeed.setText(String.valueOf(Utils.roundOff(windSpeed)));
        txtWindDirection.setText(windDirection);
    }

    @Override
    public void setTemperatureDetails(double tempAverage, double tempMax, double tempMin, double tempAppMax, double tempAppMin) {
        txtAverageTemp.setText(getString(R.string.temp_degrees, String.valueOf(tempAverage)));
        txtMaxTemp.setText(getString(R.string.temp_degrees, String.valueOf(tempMax)));
        txtMinTemp.setText(getString(R.string.temp_degrees, String.valueOf(tempMin)));
        txtMaxAppTemp.setText(getString(R.string.temp_degrees, String.valueOf(tempAppMax)));
        txtMinAppTemp.setText(getString(R.string.temp_degrees, String.valueOf(tempAppMin)));
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void setOtherDetails(double chanceOfRain, double pressure, double humidity) {
        txtPrecipitation.setText(getString(R.string.percentage, String.valueOf(Utils.roundOff(chanceOfRain))));
        txtPressure.setText(String.valueOf(Utils.roundOff(pressure)));
        txtHumidity.setText(getString(R.string.percentage, String.valueOf(Utils.roundOff(humidity))));
    }

    @Override
    public void exitScreen() {
        finish();
    }
}
