package com.example.ramadanmoustafa.tablereservation.presentation.CustomersList;

import static android.support.test.espresso.Espresso.onView;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CustomersActivityTest {

    @Rule
    public ActivityTestRule<CustomersActivity> mActivityTestRule =
            new ActivityTestRule<>(CustomersActivity.class);

    @Before
    public void setUp() throws Exception {
        //onView(ViewMatchers.withText())
    }
}