package com.vivek.spacepictures.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vivek.spacepictures.db.PicsRepository;
import com.vivek.spacepictures.model.Picture;
import com.vivek.spacepictures.utils.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpaceImagesViewModel extends AndroidViewModel {
    private static final String TAG = "SpaceImagesViewModel";

    private LiveData<PagedList<Picture>> pictures;

    private final static PagedList.Config config
            = new PagedList.Config.Builder()
            .setPageSize(Constants.PAGE_SIZE)
            .setInitialLoadSizeHint(Constants.PAGE_INITIAL_LOAD_SIZE_HINT)
            .setPrefetchDistance(Constants.PAGE_PREFETCH_DISTANCE)
            .setEnablePlaceholders(true)
            .build();
    private PicsRepository picsRepository;

    public SpaceImagesViewModel(final Application application) {
        super(application);
        picsRepository = new PicsRepository(application);
        new GetDataTask().execute();
    }

    public LiveData<PagedList<Picture>> getPictures() {
        if (pictures == null) {
            pictures = picsRepository.getAllPics(config);
        }
        return pictures;
    }

    private class GetDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            picsRepository.getFirstId();
            return null;
        }
    }
}
