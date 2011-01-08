package edu.illinois.coursequest;

/**
 * TODO Lock in portrait mode
 *  

 */
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

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

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, "Add Course");
		menu.add(0, 2, 0, "About"); // maybe later....
		menu.add(0, 3, 0, "Exit");
		return true;
	}
	/*
	 * This alarm receiver class is the broadcast receiver but currently the onreceive method is not being called.
	 */
	public class alarmReceiver extends BroadcastReceiver
	{
		Context context = getApplicationContext();
		@Override
		public void onReceive(Context context, Intent intent)
		{
			AudioManager ringControl = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE); // Makes an AudioManager
			ringControl.setRingerMode(0); // sets the phone's ringer to silent
			Toast.makeText(context, "Alarm worked.", Toast.LENGTH_SHORT).show(); // notifies the user that the alarm worked.
		}
	}
	//
	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Intent goToEditInfo = new Intent(getApplicationContext(),
					AddCourse.class);
			startActivityForResult(goToEditInfo, 0);
			/*
			 * Everything below this is the newly implemented alarm I was trying to set.
			 * The Alarm Receiver class is just above this method.
			 * This should work but the alarmReceiver either isn't registered correctly in the manifest or
			 * 		the pendingintent I wrote was wrong...take a look and see what you think?
			*/
			Intent intent = new Intent(this, alarmReceiver.class); // creates intent referring to 
																   //alarmreceiver as the broadcast receiver
			PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
							// makes the same intent a pending intent..
			AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);// creates an alarm manager
			alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (10000), pendingIntent); // set the first alarm
			Toast.makeText(this, "Alarm set", Toast.LENGTH_SHORT).show(); // indicates that the alarm was set.
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