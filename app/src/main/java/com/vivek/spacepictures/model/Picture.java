package com.vivek.spacepictures.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "pictureTable")
public class Picture implements Comparable<Picture> {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "copyright")
    @SerializedName("copyright")
    @Expose
    private String copyright;

    @ColumnInfo(name = "date")
    @SerializedName("date")
    @Expose
    private String date;

    @ColumnInfo(name = "explanation")
    @SerializedName("explanation")
    @Expose
    private String explanation;

    @ColumnInfo(name = "hdurl")
    @SerializedName("hdurl")
    @Expose
    private String hdurl;

    @ColumnInfo(name = "media_type")
    @SerializedName("media_type")
    @Expose
    private String mediaType;

    @ColumnInfo(name = "service_version")
    @SerializedName("service_version")
    @Expose
    private String serviceVersion;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    private String title;

    @ColumnInfo(name = "url")
    @SerializedName("url")
    @Expose
    private String url;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "copyright='" + copyright + '\'' +
                ", date='" + date + '\'' +
                ", explanation='" + explanation + '\'' +
                ", hdurl='" + hdurl + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", serviceVersion='" + serviceVersion + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }



    @Override
    public int compareTo(Picture picture) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = sdf.parse(date);
            Date date2 = sdf.parse(picture.date);
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(date1);
            cal2.setTime(date2);
            if (cal1.after(cal2)) {
                return -1;
            } else if (cal1.before(cal2)) {
                return 1;
            } else if (cal1.equals(cal2)) {
                return 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return 0;
    }
}
