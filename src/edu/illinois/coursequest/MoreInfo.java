package edu.illinois.coursequest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MoreInfo extends Activity {
	private static Course currentCourse;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.moreinfo);
	}

	public void infoUpdate(View view) {
		// TODO fix currentCourse to actually be currentCoure
		// Course currentCourse = CourseList.currentCourse;

		if (currentCourse != null) {
			CourseInfo info = currentCourse.getInfo();
			String times = currentCourse.getFormattedTime();
			((TextView) findViewById(R.id.timename)).setText(times);

			((TextView) findViewById(R.id.coursename)).setText(info.getName());
			((TextView) findViewById(R.id.profname)).setText(info.getProf());
			((TextView) findViewById(R.id.sectionname)).setText(info
					.getSection());
			((TextView) findViewById(R.id.crnname))
					.setText(getString(R.string.CRN) + ": " + info.getCrn());
			((TextView) findViewById(R.id.lecturename)).setText(currentCourse
					.getType());
			return; // early return
		}
		finish();
		Toast.makeText(getApplicationContext(), (getString(R.string.no_class)),
				Toast.LENGTH_SHORT).show();
	}

	public void goMoreView(View view) {
		setContentView(R.layout.moreinfo);
		infoUpdate(null);
	}

	public void back(View view) {
		finish();
	}
}
