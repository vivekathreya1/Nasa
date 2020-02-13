package com.vivek.spacepictures.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.vivek.spacepictures.model.Picture;
import com.vivek.spacepictures.utils.GetData;

@Database(entities = {Picture.class}, version = 1, exportSchema = false)
public abstract class PicsRoomDatabase extends RoomDatabase {

    private static final String TAG = "PicsRoomDatabase";
    private static PicsRoomDatabase instance;
    private static final String DATABASE_NAME = "nasaimages.db";

    public abstract PicsDao picsDao();


    public static PicsRoomDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (PicsRoomDatabase.class) {
                if (instance == null) {

                        instance = Room.databaseBuilder(context, PicsRoomDatabase.class, DATABASE_NAME)
                                .addCallback(new Callback() {
                                    @Override
                                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                        super.onCreate(db);
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                instance.picsDao().insertPics(GetData.getDataFromJson(context));
                                            }
                                        }).start();

                                    }
                                })
                                .fallbackToDestructiveMigration().build();
                    }
                }
            }
//        }
        return instance;

    }
}
