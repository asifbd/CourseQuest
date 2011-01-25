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

public class CourseQuest extends TabActivity {
	/**
	 * TODO set up saving and loading...
	 */

	/** Called when the activity is first created. */
	public TabHost tabHost;
	// TODO retrieve CourseList from file
	private static CourseList sched;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setUpSched();
		setContentView(R.layout.main);
		setUpTabs();
	}



	// Store CourseList....
	@Override
	public Object onRetainNonConfigurationInstance() {
		if (sched != null)
			return sched;
		return super.onRetainNonConfigurationInstance();
	}
	
	private void setUpSched() {
		if (getLastNonConfigurationInstance() != null) {
			sched = (CourseList) getLastNonConfigurationInstance();
		} else {
			sched = new CourseList();
		}
	}
	
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

		intent = new Intent().setClass(this, WeekView.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("").setIndicator(
				this.getString(R.string.week_view)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, ViewAll.class);

		spec = tabHost.newTabSpec("")
				.setIndicator(getString(R.string.all_view)).setContent(intent);
		// setIndicator("",
		// res.getDrawable(R.drawable.weekview)).setContent(intent);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, getString(R.string.add_course));
		menu.add(0, 2, 0, getString(R.string.about)); // maybe later....
		menu.add(0, 3, 0, getString(R.string.exit));
		return true;
	}

	//
	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Intent intent = new Intent(getApplicationContext(),
					AddOrEditCourse.class);
			startActivityForResult(intent, 0);
			// Brings up Edit Info
			return true;
		case 2:
			AlertDialog.Builder about = new AlertDialog.Builder(this);
			about.setTitle(getString(R.string.about));
			about.setMessage(getString(R.string.about_app));
			about.show();
			return true;
		case 3:
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