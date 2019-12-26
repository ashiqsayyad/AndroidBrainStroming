package com.ashsample.androidconcepts.databinding;

import android.os.Parcel;
import android.os.Parcelable;

public class DataModel implements Parcelable {
    public String androidVersion, androidName;

    public DataModel(String androidName, String androidVersion) {

        this.androidName = androidName;
        this.androidVersion = androidVersion;
    }
    public DataModel(Parcel parcel){
        this.androidName = parcel.readString();
        this.androidVersion = parcel.readString();
    }

    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.androidName);
        dest.writeString(this.androidVersion);

    }
    public static Creator<DataModel> getCREATOR() {
        return CREATOR;
    }


}
