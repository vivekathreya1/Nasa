package com.vivek.spacepictures.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Constants {

    public static final int PAGE_SIZE = 10;
    public static final int PAGE_INITIAL_LOAD_SIZE_HINT = 10;
    public static final int PAGE_PREFETCH_DISTANCE = 5;

    public static final Gson GSON;

    static {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        GSON = gsonBuilder.create();
    }
}
