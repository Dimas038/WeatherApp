package com.example.yandexweather.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DayModel implements Parcelable {

    public static final Creator<DayModel> CREATOR = new Creator<DayModel>() {
        @Override
        public DayModel createFromParcel(Parcel in) {
            return new DayModel(in);
        }

        @Override
        public DayModel[] newArray(int size) {
            return new DayModel[size];
        }
    };
    private int id;
    private String title;

    public DayModel(int id, String title) {
        this.id = id;
        this.title = title;
    }

    protected DayModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
