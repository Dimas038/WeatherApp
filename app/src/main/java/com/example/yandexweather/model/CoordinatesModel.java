package com.example.yandexweather.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CoordinatesModel implements Parcelable {

    public static final Creator<CoordinatesModel> CREATOR = new Creator<CoordinatesModel>() {
        @Override
        public CoordinatesModel createFromParcel(Parcel in) {
            return new CoordinatesModel(in);
        }

        @Override
        public CoordinatesModel[] newArray(int size) {
            return new CoordinatesModel[size];
        }
    };
    private double lat;
    private double lon;
    private String title;

    public CoordinatesModel(double lat, double lon, String title) {
        this.lat = lat;
        this.lon = lon;
        this.title = title;
    }

    protected CoordinatesModel(Parcel in) {
        lat = in.readDouble();
        lon = in.readDouble();
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lat);
        dest.writeDouble(lon);
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getTitle() {
        return title;
    }
}
