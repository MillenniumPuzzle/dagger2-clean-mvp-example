package es.voghdev.prjdagger2;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import es.voghdev.prjdagger2.ui.activity.UserListActivity;
import es.voghdev.prjdagger2.usecase.GetUsers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UsersListUITest {

    @Mock
    GetUsers getUsers;

    @Rule
    public IntentsTestRule<UserListActivity> activityRule =
            new IntentsTestRule<>(UserListActivity.class, true, false);

    @Test
    public void shouldShowRecyclerView() {
        startActivity();

        onView(withId(R.id.users_list)).check(matches(isDisplayed()));
    }

    private UserListActivity startActivity() {
        return activityRule.launchActivity(null);
    }
}