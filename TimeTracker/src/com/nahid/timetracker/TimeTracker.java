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
	private static final String TIME_KEY = "time";
	private static final String NOTES_KEY = "notes";
	private TimeTrackerDatabaseHelper databaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		databaseHelper = new TimeTrackerDatabaseHelper(this);
		
		
		ListView listView = (ListView) findViewById(R.id.time_list);
		timeTrackerAdapter = new TimeTrackerAdapter(this, databaseHelper.getTimeRecordList());
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
				String time = data.getStringExtra(TIME_KEY);
				String notes = data.getStringExtra(NOTES_KEY);
				
				databaseHelper.saveTimeRecord(time, notes);
				
				timeTrackerAdapter.changeCursor(databaseHelper.getTimeRecordList());
				
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
