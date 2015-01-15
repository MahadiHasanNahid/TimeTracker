package com.nahid.timetracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class TimeTracker extends Activity {

	TimeTrackerAdapter timeTrackerAdapter;
	public static final int TIME_ENTRY_REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView = (ListView) findViewById(R.id.time_list);
		timeTrackerAdapter = new TimeTrackerAdapter();
		listView.setAdapter(timeTrackerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onCreateOptionsMenu(menu);

		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.time_list_menu, menu);

		getMenuInflater().inflate(R.menu.activity_time_tracker, menu);
		return true;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TIME_ENTRY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				String time = data.getStringExtra("time");
				String notes = data.getStringExtra("notes");
				timeTrackerAdapter.addTimeRecord(new TimeRecord(time, notes));
				timeTrackerAdapter.notifyDataSetChanged();
				
			}
		}	
		
    }
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		if (item.getItemId() == R.id.add_time_menu_item) {

			Intent intent = new Intent(this, AddTimeActivity.class);
			//startActivity(intent);
			startActivityForResult(intent, TIME_ENTRY_REQUEST_CODE);
			return true;
		}

		return super.onMenuItemSelected(featureId, item);

	}

}
