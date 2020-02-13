package com.vivek.spacepictures.ui;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.vivek.spacepictures.db.PicsDao;
import com.vivek.spacepictures.db.PicsRoomDatabase;
import com.vivek.spacepictures.model.Picture;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class PicDatabaseTest {

    private PicsDao picsDao;
    private PicsRoomDatabase instance;
    private int LIST_ITEM_IN_TEST = 3;
    private Picture picture = new Picture("Elias Chasiotis", "2019-12-28",
            "Yes, but have you ever seen a sunrise like this?  Here, after initial cloudiness, the Sun appeared to rise in two pieces and during partial eclipse, causing the photographer to describe it as the most stunning sunrise of his life.  The dark circle near the top of the atmospherically-reddened Sun is the Moon -- but so is the dark peak just below it.  This is because along the way, the Earth's atmosphere had an inversion layer of unusually warm air which acted like a gigantic lens and created a second image.  For a normal sunrise or sunset, this rare phenomenon of atmospheric optics is known as the Etruscan vase effect. The featured picture was captured two mornings ago from Al Wakrah, Qatar.  Some observers in a narrow band of Earth to the east were able to see a full annular solar eclipse -- where the Moon appears completely surrounded by the background Sun in a ring of fire.  The next solar eclipse, also an annular eclipse, will occur in 2020 June.    Notable Images Submitted to APOD: The Partial Solar Eclipse of 2019 December"
            , "https://apod.nasa.gov/apod/image/1912/DistortedSunrise_Chasiotis_2442.jpg",
             "A Distorted Sunrise Eclipse", "https://apod.nasa.gov/apod/image/1912/DistortedSunrise_Chasiotis_1080.jpg"
    );

    private List<Picture> pictureList = new ArrayList<>();

    @Before
    public void setUp(){
        instance =PicsRoomDatabase.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        picsDao = instance.picsDao();
    }

    @Test
    public void test_check_items() {
        picsDao.getFirstId();
        Picture picture = picsDao.getPicture(this.picture.getCopyright());
        Assert.assertEquals(this.picture.getCopyright(), picture.getCopyright());
    }

    @After
    public void clearDb(){
        instance.close();

    }

}
