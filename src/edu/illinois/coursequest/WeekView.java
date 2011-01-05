package edu.illinois.coursequest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnLongClickListener;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * TODO make grid view instead of this....
 * 
 * @author marcell
 * 
 */
public class WeekView extends Activity {
	private static Course tempCourse;
	private static int count = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		goWeekView(null);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		goWeekView(null);
	}

	public void goWeekView(View view) {
		setContentView(R.layout.portrait_weekview);
		this.weekUpdate(null);

	}

	public void weekUpdate(View view) {
		// updates all the values in the week view

		for (int i = 0; i < 5; i++) { // loop through days
			for (int j = 0; j < 12; j++) { // loop through hours

				String courseName = "";
				int color = 0;
				Course temp = CourseList.getSlotPos(j, i);
				if (temp != null) {
					courseName = temp.getName();
					int courseColor = temp.getColor();
					color = Color.rgb(Color.red(courseColor), Color
							.green(courseColor), Color.blue(courseColor));// course.getColor();
				}

				ViewGroup innerRootView = (ViewGroup) findViewById(R.id.widget62);
				TableRow row = (TableRow) innerRootView.getChildAt(j + 1);
				TextView text = (TextView) row.getChildAt(i + 1);
				text.setText(courseName);
				text.setBackgroundColor(color);
				text.setVisibility(0);
				text.setGravity(17);
				text.setTextColor(Color.WHITE);
				text.setId(count++);
				text.setLongClickable(true);
				text.setTag(temp);
				text.setOnLongClickListener(new OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						// TODO find currentCourse
						tempCourse = (Course) v.getTag();
						goMoreView(null);
						return true;
					}
				});

			}

		}
	}

	public void infoUpdate(View view) {
		// TODO fix currentCourse to actually be currentCoure
		// Course currentCourse = CourseList.currentCourse;

		if (tempCourse != null) {
			String times = CourseList.getFormattedTime(tempCourse);
			((TextView) findViewById(R.id.timename)).setText(times);

			((TextView) findViewById(R.id.coursename)).setText(tempCourse
					.getName());
			((TextView) findViewById(R.id.profname)).setText(tempCourse
					.getProf());
			((TextView) findViewById(R.id.sectionname)).setText(tempCourse
					.getSection());
			((TextView) findViewById(R.id.crnname)).setText("CRN: "
					+ tempCourse.getCrn());
			String classType;
			if (tempCourse instanceof Lecture)
				classType = "Lecture";
			else if (tempCourse instanceof Lab)
				classType = "Lab";
			else
				classType = "Discussion";
			((TextView) findViewById(R.id.lecturename)).setText(classType);
			return; // early return
		}
		goWeekView(null);
		Toast.makeText(getApplicationContext(), ("No Class There!"),
				Toast.LENGTH_SHORT).show();
	}

	public void goMoreView(View view) {
		setContentView(R.layout.moreinfo);
		infoUpdate(null);
	}

	public void back(View view) {
		goWeekView(null);
	}

}
