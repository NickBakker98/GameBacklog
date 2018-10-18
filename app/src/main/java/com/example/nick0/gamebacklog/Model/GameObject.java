package com.example.nick0.gamebacklog.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.widget.Spinner;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

    @Entity(tableName = "gameObject")
    public class GameObject implements Parcelable {


        public GameObject(String mTitle, String mPlatform, String mNotes, String mStatus, String mDate) {
            this.mTitle = mTitle;
            this.mPlatform = mPlatform;
            this.mNotes = mNotes;
            this.mStatus = mStatus;
            this.mDate = mDate;
        }

        @PrimaryKey(autoGenerate = true)
        private long id;

        @ColumnInfo (name = "Title")
        private String mTitle;

        @ColumnInfo(name = "Platform")
        private String mPlatform;

        @ColumnInfo(name = "Notes")
        private String mNotes;

        @ColumnInfo(name = "Status")
        private String mStatus;

        @ColumnInfo(name = "Date")
        private String mDate;

        public String getTitle() {
            return mTitle;
        }
        public void setTitle(String mTitle) {
            this.mTitle = mTitle;
        }
        public String getPlatform(){
            return mPlatform;
        }
        public void setPlatform(String mPlatform){
            this.mPlatform = mPlatform;
        }
        public String getNotes(){
            return mNotes;
        }
        public void setNotes(String mNotes){
            this.mNotes = mNotes;
        }
        public String getStatus(){
            return mStatus;
        }
        public void setStatus(String mStatus){
            this.mStatus = mStatus;
        }
        public String getDate(){
            return mDate;
        }
        public void setDate(String mDate){
            this.mDate = mDate;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(this.id);
            dest.writeString(this.mTitle);
            dest.writeString(this.mPlatform);
            dest.writeString(this.mNotes);
            dest.writeString(this.mStatus);
            dest.writeString(this.mDate);
        }

        protected GameObject(Parcel in) {
            this.id = in.readLong();
            mTitle = in.readString();
            mPlatform = in.readString();
            mNotes = in.readString();
            mStatus = in.readString();
            mDate = in.readString();
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
