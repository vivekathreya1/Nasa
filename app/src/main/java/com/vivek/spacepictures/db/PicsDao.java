package com.vivek.spacepictures.db;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vivek.spacepictures.model.Picture;

import java.util.List;

@Dao
public interface PicsDao {


    @Query("SELECT * FROM pictureTable ORDER BY date DESC")
    DataSource.Factory<Integer, Picture> getPictures();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPics(List<Picture> picture);

    @Query("SELECT count(*) FROM pictureTable ")
    int getFirstId();


}
