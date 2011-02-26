package edu.illinois.coursequest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import android.content.Intent;
import android.os.Environment;
import android.util.Log;

public class OnAlarmReceiver extends WakefulIntentService {
	public OnAlarmReceiver() {
		super("Silent");
	}

	@Override
	protected void doWakefulWork(Intent intent) {
		File log = new File(Environment.getExternalStorageDirectory(),
				"AlarmLog.txt");
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(log
					.getAbsolutePath(), log.exists()));

			out.write(new Date().toString());
			out.write("\n");
			out.close();
		} catch (IOException e) {
			Log.e("AppService", "Exception appending to log file", e);
		}
		
//		Time t = new Time();
//		// Get the between instance stored values
//		SharedPreferences preferences = getPreferences(MODE_PRIVATE);
//		String s = preferences.getString("CourseList", null);
//		// Set the values of the UI
//		CourseList sched;
//		if (s != null)
//			try {
//				sched = CourseParser.toCourseList(s);
//				return;
//			} catch (Exception e) {
//			}
//		sched = new CourseList();
//		if (sched.isClassAt(t.toString()))
//			;
	}
}