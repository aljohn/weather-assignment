package com.assignment.weather.common.network;

import com.assignment.weather.BuildConfig;
import com.assignment.weather.common.network.models.ForecastsReturn;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherServiceRequests {

    private static final String BASE_URL = "https://api.weatherbit.io/v2.0/";
    private static final String KEY = "3b59e698f30f496dbb931bb39cb2fc56";

    private static WeatherServiceRequests instance;

    public static WeatherServiceRequests getInstance() {
        if (instance == null) {
            instance = new WeatherServiceRequests();
        }
        return instance;
    }

    private WeatherService weatherService;

    private WeatherServiceRequests() {

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url().newBuilder().addQueryParameter("key",KEY).build();
                        request = request.newBuilder().url(url).build();
                        return chain.proceed(request);
                    }
                });

        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();

        weatherService = retrofit.create(WeatherService.class);
    }

    public Call<ForecastsReturn> getForecasts(int days, double lat, double lon) {
        return weatherService.getForecasts(String.valueOf(days), String.valueOf(lat), String.valueOf(lon));
    }
}
