package com.example.nick0.gamebacklog.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.nick0.gamebacklog.Model.GameObject;
import com.example.nick0.gamebacklog.Model.GameObjectDao;


@Database(entities = {GameObject.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {


    public abstract GameObjectDao gameObjectDao();

    private final static String NAME_DATABASE = "gameobject_db";


    //Static instance
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {

        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context, AppDatabase.class, NAME_DATABASE)
                    .build();
        }
        return sInstance;

    }

}
