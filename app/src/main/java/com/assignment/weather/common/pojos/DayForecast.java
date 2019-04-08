package com.assignment.weather.common.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class DayForecast implements Parcelable {

    private String date;
    private String icon;
    private String description;
    private double windGustSpeed;
    private double windSpeed;
    private String windDirection;
    private double tempAverage;
    private double tempMax;
    private double tempMin;
    private double tempAppMax;
    private double tempAppMin;
    private double chanceOfRain;
    private double pressure;
    private double humidity;

    public DayForecast setDate(String date) {
        this.date = date;
        return this;
    }

    public DayForecast setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public DayForecast setDescription(String description) {
        this.description = description;
        return this;
    }

    public DayForecast setWindGustSpeed(double windGustSpeed) {
        this.windGustSpeed = windGustSpeed;
        return this;
    }

    public DayForecast setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public DayForecast setWindDirection(String windDirection) {
        this.windDirection = windDirection;
        return this;
    }

    public DayForecast setTempAverage(double tempAverage) {
        this.tempAverage = tempAverage;
        return this;
    }

    public DayForecast setTempMax(double tempMax) {
        this.tempMax = tempMax;
        return this;
    }

    public DayForecast setTempMin(double tempMin) {
        this.tempMin = tempMin;
        return this;
    }

    public DayForecast setTempAppMax(double tempAppMax) {
        this.tempAppMax = tempAppMax;
        return this;
    }

    public DayForecast setTempAppMin(double tempAppMin) {
        this.tempAppMin = tempAppMin;
        return this;
    }

    public DayForecast setChanceOfRain(double chanceOfRain) {
        this.chanceOfRain = chanceOfRain;
        return this;
    }

    public DayForecast setPressure(double pressure) {
        this.pressure = pressure;
        return this;
    }

    public DayForecast setHumidity(double humidity) {
        this.humidity = humidity;
        return this;
    }

    public String getDate() {
        return date;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public double getWindGustSpeed() {
        return windGustSpeed;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public double getTempAverage() {
        return tempAverage;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempAppMax() {
        return tempAppMax;
    }

    public double getTempAppMin() {
        return tempAppMin;
    }

    public double getChanceOfRain() {
        return chanceOfRain;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.icon);
        dest.writeString(this.description);
        dest.writeDouble(this.windGustSpeed);
        dest.writeDouble(this.windSpeed);
        dest.writeString(this.windDirection);
        dest.writeDouble(this.tempAverage);
        dest.writeDouble(this.tempMax);
        dest.writeDouble(this.tempMin);
        dest.writeDouble(this.tempAppMax);
        dest.writeDouble(this.tempAppMin);
        dest.writeDouble(this.chanceOfRain);
        dest.writeDouble(this.pressure);
        dest.writeDouble(this.humidity);
    }

    public DayForecast() {
    }

    protected DayForecast(Parcel in) {
        this.date = in.readString();
        this.icon = in.readString();
        this.description = in.readString();
        this.windGustSpeed = in.readDouble();
        this.windSpeed = in.readDouble();
        this.windDirection = in.readString();
        this.tempAverage = in.readDouble();
        this.tempMax = in.readDouble();
        this.tempMin = in.readDouble();
        this.tempAppMax = in.readDouble();
        this.tempAppMin = in.readDouble();
        this.chanceOfRain = in.readDouble();
        this.pressure = in.readDouble();
        this.humidity = in.readDouble();
    }

    public static final Creator<DayForecast> CREATOR = new Creator<DayForecast>() {
        @Override
        public DayForecast createFromParcel(Parcel source) {
            return new DayForecast(source);
        }

        @Override
        public DayForecast[] newArray(int size) {
            return new DayForecast[size];
        }
    };
}
