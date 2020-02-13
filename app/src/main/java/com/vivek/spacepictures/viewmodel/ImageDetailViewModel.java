package com.vivek.spacepictures.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.vivek.spacepictures.db.PicsRepository;
import com.vivek.spacepictures.model.Picture;
import com.vivek.spacepictures.utils.Constants;

public class ImageDetailViewModel extends AndroidViewModel {

    private final static PagedList.Config config
            = new PagedList.Config.Builder()
            .setPageSize(Constants.PAGE_SIZE)
            .setInitialLoadSizeHint(Constants.PAGE_INITIAL_LOAD_SIZE_HINT)
            .setPrefetchDistance(Constants.PAGE_PREFETCH_DISTANCE)
            .setEnablePlaceholders(true)
            .build();
    private PicsRepository picsRepository;
    private LiveData<PagedList<Picture>> pictures;

    public ImageDetailViewModel(final Application application) {
        super(application);
        picsRepository = new PicsRepository(application);
    }

    public LiveData<PagedList<Picture>> getPictures() {
        if (pictures == null) {
            pictures = picsRepository.getAllPics(config);
        }
        return pictures;
    }
}
