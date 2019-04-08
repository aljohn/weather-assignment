package com.assignment.weather.common;

import android.content.res.Resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.DrawableRes;

public class Utils {

    private static SimpleDateFormat serverDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private static SimpleDateFormat clientDateFormat = new SimpleDateFormat("d MMM y", Locale.getDefault());

    @DrawableRes
    public static int getDrawableResByName(Resources resources, String name, String packageName) {
        return resources.getIdentifier(name, "drawable", packageName);
    }

    public static String formatDate(String date) {
        try {
            Date dateInServerFormat = serverDateFormat.parse(date);
            return clientDateFormat.format(dateInServerFormat);
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }

    public static double roundOff(double num) {
        return Math.round(num * 100.0) / 100.0;
    }
}
