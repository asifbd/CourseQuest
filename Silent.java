package com.commonsware.android.syssvc.alarm;

import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Calendar;
import android.text.format.Time;

public class Silent extends WakefulIntentService {
	public Silent() {
		super("Silent");
	}

	@Override
	protected void doWakefulWork(Intent intent) {
		File log=new File(Environment.getExternalStorageDirectory(), "AlarmLog.txt");
		
		try {
			BufferedWriter out=new BufferedWriter( new FileWrite (log.getAbsolutePath(),log.exists()));
			out.write(new Date().toString());
			out.write("\n");
			out.close();
		}
		catch (IOException e) {
			Log.e("AppService", "Exception appending to log file", e);
		}
		Time t = new Time();
		//If theres a class at this time...set phone to vibrate
		if(CourseList.isClassAt(t.toString())){
		    AudioManager test = (AudioManager)this.getSystemService(this.AUDIO_SERVICE);
		    test.setRingerMode(1);
		}
	}
}