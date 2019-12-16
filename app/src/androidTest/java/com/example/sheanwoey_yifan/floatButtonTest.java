package com.example.sheanwoey_yifan;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class floatButtonTest {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Test
    public void floatButtonTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.username),
                        childAtPosition(
                                allOf(withId(R.id.loginForm),
                                        childAtPosition(
                                                withId(R.id.activity_main),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("user"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                allOf(withId(R.id.loginForm),
                                        childAtPosition(
                                                withId(R.id.activity_main),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("123457"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.toggleRegister), withText("Register"),
                        childAtPosition(
                                allOf(withId(R.id.loginForm),
                                        childAtPosition(
                                                withId(R.id.activity_main),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.userRegister),
                        childAtPosition(
                                allOf(withId(R.id.registerForm),
                                        childAtPosition(
                                                withId(R.id.activity_main),
                                                2)),
                                0),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("user"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.passRegister),
                        childAtPosition(
                                allOf(withId(R.id.registerForm),
                                        childAtPosition(
                                                withId(R.id.activity_main),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("123456"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.register), withText("Register"),
                        childAtPosition(
                                allOf(withId(R.id.registerForm),
                                        childAtPosition(
                                                withId(R.id.activity_main),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                allOf(withId(R.id.container),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction constraintLayout = onView(
                allOf(withId(R.id.team1),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        constraintLayout.perform(scrollTo(), click());

        ViewInteraction constraintLayout2 = onView(
                allOf(withId(R.id.pokeContainer),
                        childAtPosition(
                                allOf(withId(R.id.pokemonList),
                                        childAtPosition(
                                                withId(R.id.container),
                                                0)),
                                5),
                        isDisplayed()));
        constraintLayout2.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
