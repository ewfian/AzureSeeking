package com.ceas.azureseeking;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
	    ActionBar actionBar = getActionBar();  
	    actionBar.setDisplayHomeAsUpEnabled(true);  

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

}
