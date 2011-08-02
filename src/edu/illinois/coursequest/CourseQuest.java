package edu.illinois.coursequest;

/**
 * TODO Lock in portrait mode
 *  

 */
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

public class CourseQuest extends TabActivity {
	/**
	 * TODO set up saving and loading...
	 */

	/** Called when the activity is first created. */
	public TabHost tabHost;
	// TODO retrieve CourseList from file
	private static CourseList sched = new CourseList();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// setUpSched(); //Load Saved schedule
		setContentView(R.layout.main);
		setUpTabs();
	}

	@Override
	protected void onPause() {
		super.onPause();

		// Store values between instances here
		// SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		// SharedPreferences.Editor editor = preferences.edit();
		//
		// editor.putString("CourseList", sched.toString()); // value to store
		// // Commit to storage
		// editor.commit();
	}

	// private void setUpSched() {
	// retrieveSched();
	// }
	//
	// private void retrieveSched() {
	// // Get the between instance stored values
	// SharedPreferences preferences = getPreferences(MODE_PRIVATE);
	// String s = preferences.getString("CourseList", null);
	// // Set the values of the UI
	// if (s != null)
	// sched = CourseParser.toCourseList(s);
	// }

	private void setUpTabs() {
		// Resources res = getResources(); // Resource object to get Drawables
		tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, DayView.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("").setIndicator(
				this.getString(R.string.day_view)).setContent(intent);
		tabHost.addTab(spec);

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, WeekView.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("").setIndicator(
				this.getString(R.string.week_view)).setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(1);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, getString(R.string.add_course));
		menu.add(0, 2, 0, getString(R.string.all_view));
		menu.add(0, 3, 0, getString(R.string.about));
		menu.add(0, 4, 0, getString(R.string.exit));
		return true;
	}

	//
	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case 1:
			intent = new Intent(getApplicationContext(), AddOrEditCourse.class);
			startActivityForResult(intent, 0);
			// Brings up Edit Info
			return true;
		case 2:
			if (sched.getLength() != 0) {
				intent = new Intent().setClass(this, ViewAll.class);
				startActivity(intent);
			} else {
				Toast.makeText(getApplicationContext(),
						(getString(R.string.no_class)), Toast.LENGTH_SHORT)
						.show();
			}
			return true;
		case 3:
			AlertDialog.Builder about = new AlertDialog.Builder(this);
			about.setTitle(getString(R.string.about));
			about.setMessage(getString(R.string.about_app));
			about.show();
			return true;
		case 4:
			this.finish();
			return true;
		}
		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// bit of a hack to get this working the ListView to update (not open on
		// it :p)
		super.onActivityResult(requestCode, resultCode, data);
		tabHost.setCurrentTab(1);
	}

	public static CourseList getSched() {
		return sched;
	}

}