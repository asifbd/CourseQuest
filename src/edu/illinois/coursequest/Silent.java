package edu.illinois.coursequest;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.IBinder;

//TODO setup service
public class Silent extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
//		AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
//		audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
//		
//		stopSelf();
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		myTask.execute();
		return;
	}
	AsyncTask<Void, Void, Void> myTask= new AsyncTask<Void, Void, Void>() {
		@Override
		protected Void doInBackground(Void...arg0){
			//Execute Task
			AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
			audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			stopSelf();
		}
	};
	
}
