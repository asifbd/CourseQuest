package edu.illinois.coursequest;

/**
 * TODO Lock in portrait mode
 *  

 */
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class CourseQuest extends TabActivity {
	/**
	 * TODO have icons for tabs
	 */

	/** Called when the activity is first created. */
	public TabHost tabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setAlarm();
		// Resources res = getResources(); // Resource object to get Drawables
		tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, DayView.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("").setIndicator("Day View").setContent(
				intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, WeekView.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("").setIndicator("Week View").setContent(
				intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, ViewAll.class);

		spec = tabHost.newTabSpec("").setIndicator("All View").setContent(
				intent);
		// setIndicator("",
		// res.getDrawable(R.drawable.weekview)).setContent(intent);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
	}

	private void setAlarm() {
		String alarm = Context.ALARM_SERVICE;
		AlarmManager am;
		am = (AlarmManager) getSystemService(alarm);

		Intent intent = new Intent("REFRESH_THIS");
		PendingIntent op;
		op = PendingIntent.getBroadcast(this, 0, intent, 0);

		int type = AlarmManager.ELAPSED_REALTIME_WAKEUP;
		long interval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
		long triggerTime = SystemClock.elapsedRealtime() + interval;

		am.setInexactRepeating(type, triggerTime, interval, op);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, "Add Course");
		menu.add(0, 2, 0, "About"); // maybe later....
		menu.add(0, 3, 0, "Exit");
		return true;
	}

	//
	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Intent goToEditInfo = new Intent(getApplicationContext(),
					AddCourse.class);
			startActivityForResult(goToEditInfo, 0);
			// Brings up Edit Info
			return true;
		case 2:
			AlertDialog.Builder about = new AlertDialog.Builder(this);
			about.setTitle("About");
			about
					.setMessage("Course Quest 2010 \n Marcell Vazquez-Chanlatte \n Anthony Louie \n Eric Mills \n Devin Bhushan");
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

}