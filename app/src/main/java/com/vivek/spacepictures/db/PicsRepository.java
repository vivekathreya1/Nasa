package com.vivek.spacepictures.db;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.vivek.spacepictures.model.Picture;

import java.util.List;

public class PicsRepository {

    private PicsDao picsDao;

    public PicsRepository(Application application){
        PicsRoomDatabase picsRoomDatabase = PicsRoomDatabase.getInstance(application);
        picsDao = picsRoomDatabase.picsDao();
    }

    public int getFirstId(){
       return picsDao.getFirstId();
    }


    public LiveData<PagedList<Picture>> getAllPics(PagedList.Config config) {
        DataSource.Factory<Integer, Picture> factory = picsDao.getPictures();
        return new LivePagedListBuilder<>(factory, config)
                .build();
    }
}
