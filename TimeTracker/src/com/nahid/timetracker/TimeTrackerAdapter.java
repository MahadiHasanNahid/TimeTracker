package com.nahid.timetracker;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TimeTrackerAdapter extends CursorAdapter {

	private ArrayList<TimeRecord> times = new ArrayList<TimeRecord>();

	public TimeTrackerAdapter(Context context, Cursor cursor) {
		super(context, cursor);
	}

	public void addTimeRecord(TimeRecord timeRecord){
		times.add(timeRecord);
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView nameTextView = (TextView) view.findViewById(R.id.time_view);
		nameTextView.setText(cursor.getString(1));
		//nameTextView.setText(cursor.getString(1));
		TextView valueTextView = (TextView) view.findViewById(R.id.notes_view);
		valueTextView.setText(cursor.getString(2));
		//valueTextView.setText(cursor.getString(cursor.getColumnIndex("2")));
		
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.time_list_item, parent, false);
		return view;
	}
	
	@Override
	public int getCount() {
		return times.size();
	}

	@Override
	public Object getItem(int index) {
		return getItem(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {

		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());

			view = inflater.inflate(R.layout.time_list_item, parent, false);
		}

		TimeRecord time = times.get(index);

		TextView timeTextView = (TextView) view.findViewById(R.id.time_view);
		timeTextView.setText(time.getTime());

		TextView notesTextView = (TextView) view.findViewById(R.id.notes_view);
		notesTextView.setText(time.getNotes());

		return view;
	}



}
