package com.example.nick0.gamebacklog.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.widget.Spinner;

public class GameObjectOud implements Parcelable {

    private String mTitle, mPlatform, mNotes, mStatus, mDate;

    public GameObjectOud(String mTitle, String mPlatform, String mNotes, String mStatus, String mDate){

        this.mTitle = mTitle;
        this.mPlatform = mPlatform;
        this.mNotes = mNotes;
        this.mStatus = mStatus;
        this.mDate = mDate;
    }
    public String getmTitle(){
        return mTitle;
    }
    public void setmTitle(String mTitle){
        this.mTitle = mTitle;
    }
    public String getmPlatform(){
        return mPlatform;
    }
    public void setmPlatform(String mPlatform){
        this.mPlatform = mPlatform;
    }
    public String getmNotes(){
        return mNotes;
    }
    public void setmNotes(String mNotes){
        this.mNotes = mNotes;
    }
    public String getmStatus(){
        return mStatus;
    }
    public void setmStatus(String mStatus){
        this.mStatus = mStatus;
    }
    public String getmDate(){
        return mDate;
    }
    public void setmDate(String mDate){
        this.mDate = mDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeString(this.mPlatform);
        dest.writeString(this.mNotes);
        dest.writeString(this.mStatus);
        dest.writeString(this.mDate);
    }

    protected GameObjectOud(Parcel in) {
        this.mTitle = in.readString();
        this.mPlatform = in.readString();
        this.mNotes = in.readString();
        this.mStatus = in.readString();
        this.mDate = in.readString();
    }

    public static final Creator<GameObject> CREATOR = new Creator<GameObject>() {
        @Override
        public GameObject createFromParcel(Parcel source) {
            return new GameObject(source);
        }

        @Override
        public GameObject[] newArray(int size) {
            return new GameObject[size];
        }
    };
}
