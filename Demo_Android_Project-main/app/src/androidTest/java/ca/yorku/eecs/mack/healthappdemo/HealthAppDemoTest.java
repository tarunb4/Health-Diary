package ca.yorku.eecs.mack.healthappdemo;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.SeekBar;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *  Espresso Test for UI interactions
 *  Goes through different activities -
 *  Creating account
 *  My account details
 *  Wellness goals - adding, editing, checking if its populated
 *  Daily diary radio buttons and sliders to get the idea of going through the flow of trials
* */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class HealthAppDemoTest {
    @Rule
    public ActivityScenarioRule<HealthAppDemo> mActivityScenarioRule =
            new ActivityScenarioRule<>(HealthAppDemo.class);

    @Test
    public void healthAppDemoTest() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.btnCreateAccount), withContentDescription("Create Account"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0)));
        appCompatImageButton.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextUsername),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("user"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextEmail),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("user@email.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTextPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("123"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonCreateAccount), withText("Create Account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton.perform(click());

        pressBack();

        ViewInteraction textView = onView(
                allOf(withId(R.id.textView3), withText("user"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText("user")));

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.btnMyAccount), withContentDescription("My Account"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0)));
        appCompatImageButton2.perform(scrollTo(), click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textViewUserName), withText("Username: user"),
                        withParent(withParent(withId(R.id.cardViewUserInfo))),
                        isDisplayed()));
        textView2.check(matches(withText("Username: user")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textViewEmail), withText("Email: user@email.com"),
                        withParent(withParent(withId(R.id.cardViewUserInfo))),
                        isDisplayed()));
        textView3.check(matches(withText("Email: user@email.com")));

        pressBack();

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.btnWellnessGoals), withContentDescription("Wellness Goals\n"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                0)));
        appCompatImageButton3.perform(scrollTo(), click());

        ViewInteraction button = onView(
                allOf(withId(R.id.buttonAddGoal), withText("+"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction editText = onView(
                allOf(childAtPosition(
                                allOf(withId(android.R.id.custom),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        editText.perform(replaceText("new"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(android.R.id.button1), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        button2.perform(scrollTo(), click());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.buttonSaveGoals), withText("Save Goals"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        button3.perform(click());

        ViewInteraction button4 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        button4.perform(scrollTo(), click());

        ViewInteraction button5 = onView(
                allOf(withId(R.id.buttonAddGoal), withText("+"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0),
                        isDisplayed()));
        button5.perform(click());

        ViewInteraction editText2 = onView(
                allOf(childAtPosition(
                                allOf(withId(android.R.id.custom),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        editText2.perform(replaceText("new goals"), closeSoftKeyboard());

        ViewInteraction button6 = onView(
                allOf(withId(android.R.id.button1), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        button6.perform(scrollTo(), click());

        ViewInteraction button7 = onView(
                allOf(withId(R.id.buttonSaveGoals), withText("Save Goals"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        button7.perform(click());

        ViewInteraction button8 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        button8.perform(scrollTo(), click());

        ViewInteraction textView4 = onView(
                allOf(withText("new"),
                        childAtPosition(
                                allOf(withId(R.id.goalsLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                0)));
        textView4.perform(scrollTo(), click());

        DataInteraction textView5 = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(1);
        textView5.perform(click());

        ViewInteraction button9 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        button9.perform(scrollTo(), click());

        pressBack();

        ViewInteraction textView6 = onView(
                withId(R.id.textView4));
        textView6.check(matches(withText("\nToday's Goal:-\nnew goals")));


        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.btnDailyDiary), withContentDescription("Daily Diary"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0)));
        appCompatImageButton4.perform(click());

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.option3e), withText("Okay"),
                        childAtPosition(
                                allOf(withId(R.id.optionsRadioGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2)));
        radioButton.perform(click());

        ViewInteraction radioButton2 = onView(
                allOf(withId(R.id.option3s), withText("7–8 Hours"),
                        childAtPosition(
                                allOf(withId(R.id.options),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                2)));
        radioButton2.perform(click());

        ViewInteraction radioButton3 = onView(
                allOf(withId(R.id.option2st), withText("3–5 Intensity"),
                        childAtPosition(
                                allOf(withId(R.id.optionsst),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                5)),
                                1)));
        radioButton3.perform(click());

        ViewInteraction radioButton4 = onView(
                allOf(withId(R.id.option1ex), withText("Walking"),
                        childAtPosition(
                                allOf(withId(R.id.optionse),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                7)),
                                0)));
        radioButton4.perform(scrollTo(), click());

        ViewInteraction radioButton5 = onView(
                allOf(withId(R.id.option1n), withText("3 Meals a Day"),
                        childAtPosition(
                                allOf(withId(R.id.optionsn),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                9)),
                                0)));
        radioButton5.perform(scrollTo(), click());

        ViewInteraction button10 = onView(
                allOf(withId(R.id.next), withText("Next ->"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        button10.perform(click());

        onView(withId(R.id.sliderSeekBar)).perform(setProgress(1));

        // Repeat this for other SeekBars
        onView(withId(R.id.sliderSeekBar2)).perform(setProgress(1));
        onView(withId(R.id.sliderSeekBar1)).perform(setProgress(1));
        onView(withId(R.id.sliderSeekBar3)).perform(setProgress(1));
        onView(withId(R.id.sliderSeekBar4)).perform(setProgress(1));

        // Perform a click on the "Next" button
        onView(withId(R.id.next)).perform(click());

        pressBack();

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

    public static ViewAction setProgress(final int progress) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(SeekBar.class);
            }

            @Override
            public String getDescription() {
                return "Set progress on SeekBar";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((SeekBar) view).setProgress(progress);
            }
        };
    }

}
