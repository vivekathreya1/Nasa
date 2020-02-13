package com.vivek.spacepictures.ui.fragment;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.vivek.spacepictures.R;
import com.vivek.spacepictures.model.Picture;
import com.vivek.spacepictures.ui.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;


@RunWith(AndroidJUnit4ClassRunner.class)
public class SpaceImagesTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(MainActivity.class);

    private int LIST_ITEM_IN_TEST = 3;
    private Picture picture = new Picture("Elias Chasiotis", "2019-12-28",
            "Yes, but have you ever seen a sunrise like this?  Here, after initial cloudiness, the Sun appeared to rise in two pieces and during partial eclipse, causing the photographer to describe it as the most stunning sunrise of his life.  The dark circle near the top of the atmospherically-reddened Sun is the Moon -- but so is the dark peak just below it.  This is because along the way, the Earth's atmosphere had an inversion layer of unusually warm air which acted like a gigantic lens and created a second image.  For a normal sunrise or sunset, this rare phenomenon of atmospheric optics is known as the Etruscan vase effect. The featured picture was captured two mornings ago from Al Wakrah, Qatar.  Some observers in a narrow band of Earth to the east were able to see a full annular solar eclipse -- where the Moon appears completely surrounded by the background Sun in a ring of fire.  The next solar eclipse, also an annular eclipse, will occur in 2020 June.    Notable Images Submitted to APOD: The Partial Solar Eclipse of 2019 December"
            , "https://apod.nasa.gov/apod/image/1912/DistortedSunrise_Chasiotis_2442.jpg",
             "A Distorted Sunrise Eclipse", "https://apod.nasa.gov/apod/image/1912/DistortedSunrise_Chasiotis_1080.jpg"
    );

    @Test
    public void test_isFragmentVisible() {
        onView(withId(R.id.pics_constraint_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_clickItem_isDetailFragmentVisible() {
        onView(withId(R.id.recycler_view_images))
                .perform(RecyclerViewActions.actionOnItemAtPosition(LIST_ITEM_IN_TEST, click()));
        onView(withId(R.id.image_copyrights)).check(matches(withText(containsString(picture.getCopyright()))));
        onView(withId(R.id.image_explanation)).check(matches(withText(containsString(picture.getExplanation()))));
    }

    @Test
    public void test_backNav_toPicListFragment() {
        onView(withId(R.id.recycler_view_images))
                .perform(RecyclerViewActions.actionOnItemAtPosition(LIST_ITEM_IN_TEST, click()));
        pressBack();
        onView(withId(R.id.pics_constraint_layout)).check(matches(isDisplayed()));
    }


}