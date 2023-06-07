package com.alekhin.museys.room;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Museum implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String museumImage;
    public String museumTitle;
    public String museumDescription;
    public String museumAddress;
    public String museumNumber;
    public String museumSite;

    public Museum(int id, String museumImage, String museumTitle, String museumDescription, String museumAddress, String museumNumber, String museumSite) {
        this.id = id;
        this.museumImage = museumImage;
        this.museumTitle = museumTitle;
        this.museumDescription = museumDescription;
        this.museumAddress = museumAddress;
        this.museumNumber = museumNumber;
        this.museumSite = museumSite;
    }


    protected Museum(Parcel in) {
        id = in.readInt();
        museumImage = in.readString();
        museumTitle = in.readString();
        museumDescription = in.readString();
        museumAddress = in.readString();
        museumNumber = in.readString();
        museumSite = in.readString();
    }

    public static final Creator<Museum> CREATOR = new Creator<Museum>() {
        @Override
        public Museum createFromParcel(Parcel in) {
            return new Museum(in);
        }

        @Override
        public Museum[] newArray(int size) {
            return new Museum[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(museumImage);
        dest.writeString(museumTitle);
        dest.writeString(museumDescription);
        dest.writeString(museumAddress);
        dest.writeString(museumNumber);
        dest.writeString(museumSite);
    }
}