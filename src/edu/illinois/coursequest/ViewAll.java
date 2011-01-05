package edu.illinois.coursequest;

import android.app.ListActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.AdapterView.AdapterContextMenuInfo;

/**
 * TODO link to CourseList
 * 
 * @author marcell
 * 
 */
public class ViewAll extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fillData();
	}

	private void fillData() {
		setListAdapter(new ArrayAdapter<String>(this, R.layout.course_row,
				CourseList.courseNames.toArray(new String[CourseList.length])));
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		this.registerForContextMenu(lv);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, 1, 0, "Edit");
		menu.add(0, 2, 0, "Delete"); // maybe later....
	}

	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();

		switch (item.getItemId()) {
		case 1:
			Intent goToEditInfo = new Intent(getApplicationContext(),
					AddCourse.class);
			goToEditInfo.putExtra(CourseList.KEY_ID, info.id);
			startActivityForResult(goToEditInfo, 0);
			// // Brings up Edit Info
			return true;
		case 2:
			CourseList.deleteCourse((int) info.id);
			fillData();
			return true;
		}
		return false;
	}


}
