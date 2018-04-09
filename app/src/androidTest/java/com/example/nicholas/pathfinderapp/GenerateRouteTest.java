package com.example.nicholas.pathfinderapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by delgado on 4/8/18.
 */


@RunWith(AndroidJUnit4.class)
public class GenerateRouteTest {

    @Rule
    public ActivityTestRule<GenerateRouteActivity> mActivityRule  =
            new ActivityTestRule(GenerateRouteActivity.class);


    @Test
    public void testKmAndMilesButtons(){
        onView(withId(R.id.kilometers_button)).perform(click());

        onView(withText(R.string.NineToTwentyFourKM)).check(matches(isDisplayed()));
        onView(withText(R.string.OneToEightKM)).check(matches(isDisplayed()));

        onView(withId(R.id.miles_button)).perform(click());

        onView(withText(R.string.SixToFifteenMiles)).check(matches(isDisplayed()));
        onView(withText(R.string.OneToFiveMiles)).check(matches(isDisplayed()));
    }


    @Test
    public void testSmallRunButtons(){
        onView(withId(R.id.kilometers_button)).perform(click());
        onView(withId(R.id.smallRun_button)).perform(click());

        onView(withId(R.id.start_button)).check(matches(isDisplayed()));

        onView(withText(R.string.selectShortRun))
                .inRoot(withDecorView(not(mActivityRule.getActivity()
                        .getWindow().getDecorView()))).check(matches(isDisplayed()));


        onView(withId(R.id.miles_button)).perform(click());
        onView(withId(R.id.smallRun_button)).perform(click());

        onView(withText(R.string.selectShortRun))
                .inRoot(withDecorView(not(mActivityRule.getActivity()
                        .getWindow().getDecorView()))).check(matches(isDisplayed()));

    }


    @Test
    public void testMediumRunButtons(){
        onView(withId(R.id.kilometers_button)).perform(click());
        onView(withId(R.id.mediumRun_button)).perform(click());

        onView(withId(R.id.start_button)).check(matches(isDisplayed()));

        onView(withText(R.string.selectMediumRun))
                .inRoot(withDecorView(not(mActivityRule.getActivity()
                        .getWindow().getDecorView()))).check(matches(isDisplayed()));


        onView(withId(R.id.miles_button)).perform(click());
        onView(withId(R.id.mediumRun_button)).perform(click());

        onView(withText(R.string.selectMediumRun))
                .inRoot(withDecorView(not(mActivityRule.getActivity()
                        .getWindow().getDecorView()))).check(matches(isDisplayed()));
    }




}
