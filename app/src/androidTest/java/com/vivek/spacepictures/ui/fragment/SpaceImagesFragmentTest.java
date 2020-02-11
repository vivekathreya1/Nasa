package com.vivek.spacepictures.ui.fragment;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.vivek.spacepictures.R;
import com.vivek.spacepictures.ui.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4ClassRunner.class)
public class SpaceImagesFragmentTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(MainActivity.class);

    @Test
    public void test_isFragmentVisible(){
        onView(withId(R.id.pics_constraint_layout)).check(matches( isDisplayed()));
    }


}