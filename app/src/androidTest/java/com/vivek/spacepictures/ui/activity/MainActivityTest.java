package com.vivek.spacepictures.ui.activity;

import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.vivek.spacepictures.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(MainActivity.class);

    @Test
    public void test_isActivtyInView(){
      onView(withId(R.id.main_activity_layout)).check(matches( isDisplayed()));
    }

}