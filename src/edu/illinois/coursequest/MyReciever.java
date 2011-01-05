package edu.illinois.coursequest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		//Sucess!!!! You reached me!
		Intent ss = new Intent(context, Silent.class);
		context.startService(ss);
//		Toast.makeText(context, (CharSequence)"test",
//                Toast.LENGTH_LONG).show();
	}

}
