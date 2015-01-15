package com.nahid.timetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddTimeActivity extends Activity {
	
	private Button cancelButton;
	private Button saveButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_time);
		seUpViews();
	}
	
	private void seUpViews() {
		cancelButton = (Button)findViewById(R.id.cancel_button);
		saveButton = (Button)findViewById(R.id.save_button);
		
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				EditText timeView = (EditText)findViewById(R.id.time_view);
				intent.putExtra("time", timeView.getText().toString());
				EditText notesView = (EditText)findViewById(R.id.notes_view);
				intent.putExtra("notes", notesView.getText().toString());
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_time, menu);
		return true;
	}

}
