package com.example.nick0.gamebacklog.Model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GameObjectDao {

    @Query("SELECT * FROM gameObject")
    public List<GameObject> getAllGameObject();

    @Insert
    public void insertGameObjects(GameObject teams);

    @Delete
    public void deleteGameObjects(GameObject teams);

    @Update
    public void updateGameObjects(GameObject teams);

}
