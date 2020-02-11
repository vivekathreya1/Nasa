package com.vivek.spacepictures.utils;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vivek.spacepictures.model.Picture;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetData {

    public static List<Picture> getDataFromJson(Context context) {
        String json = null;
        List<Picture> pictures = new ArrayList<>();
        try {
            InputStream inputStream = context.getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            Type pictureType = new TypeToken<List<Picture>>() {
            }.getType();
            pictures = gson.fromJson(json, pictureType);
//            Collections.sort(pictures);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pictures;
    }
}
