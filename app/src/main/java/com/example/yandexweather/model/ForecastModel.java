package com.example.yandexweather.model;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ForecastModel {
    @SerializedName("date")
    private String date;
    @SerializedName("sunrise")
    private String sunrise;
    @SerializedName("parts")
    private PartsModel parts;
    @SerializedName("hours")
    private List<HourModel> hours;
    @SerializedName("icon")
    private String icon;

    public String getDateReverse() {
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date d = null;
        try {
            d = originalFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetFormat.format(d);
    }

    public String getDayOfWeek() {
        try {
            DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = originalFormat.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(d);

            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            switch (dayOfWeek) {
                case 1:
                    return "Воскресенье";
                case 2:
                    return "Понедельник";
                case 3:
                    return "Вторник";
                case 4:
                    return "Среда";
                case 5:
                    return "Четверг";
                case 6:
                    return "Пятница";
                case 7:
                    return "Суббота";
            }
            return String.valueOf(dayOfWeek);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSunrise() {
        return sunrise;
    }

    public PartsModel getParts() {
        return parts;
    }

    public List<HourModel> getHours() {
        return hours;
    }

    public String getIcon() {
        return "https://yastatic.net/weather/i/icons/blueye/color/svg/" + icon + ".svg";
    }
}
