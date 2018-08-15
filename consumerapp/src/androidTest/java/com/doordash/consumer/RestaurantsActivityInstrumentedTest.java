package com.doordash.consumer;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.doordash.consumer.UI.RestaurantsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RestaurantsActivityInstrumentedTest {

	@Rule
	public ActivityTestRule<RestaurantsActivity> activityActivityTestRule = new ActivityTestRule<> (RestaurantsActivity.class);

	@Test
	public void checkRestaurantsAreShown() {
		// count number of children in view
		Activity activity = activityActivityTestRule.getActivity();
		RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recycleView);

		try {
			// Wait for list to be populated
			Thread.sleep(2000);
		} catch (Exception e) {

		}

		// On my Nexus 6P device the recyclerview starts off with 5 restaurants visible
		assertTrue(recyclerView.getChildCount() >= 4);
	}

}
