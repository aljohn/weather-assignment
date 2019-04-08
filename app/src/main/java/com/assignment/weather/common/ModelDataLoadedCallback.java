package com.assignment.weather.common;

public interface ModelDataLoadedCallback<T> {
    void onSuccess(T data);
    void onError();
}
