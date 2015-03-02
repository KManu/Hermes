package com.MIGH.hermes;


import com.google.android.gms.common.GooglePlayServicesUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
//import android.widget.Button;
import android.widget.TextView;


public class About extends ActionBarActivity {
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		//Initial setting on the action bar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		
		// Nothing to see here. 
		String action=null;
		Intent recieved = getIntent();
		action = recieved.getExtras().getString("Selected");
		
		
		TextView header = (TextView) findViewById(R.id.aboutPage_heading);
		TextView body = (TextView) findViewById(R.id.aboutPage_body);
		//Button background=(Button)findViewById(R.id.aboutPage_backButton); // doesn't go back, back is for background
		
		String libsUsed="The following were used under the stated license. \n "
				+ "\n Google Maps Android API v2 "
				+ "\n Android SQLiteAssetHelper by Jeff Gilfelt"
				+ "\n\n\n\n ";
		
		// DIsplays the appropriate text depending on whether the user came for the license info or about info
		if( action.equals("Licence")){
			header.setText("Open Source Licences");
			body.setText(libsUsed+getLegalInfo());
		}
		else if (action.equals("About")){
			header.setText("About This Application");
			body.setText("This application was made by a team of highly dedicated and passionate monkeys. Ignore banana references. If you find any.");
		}
		
		
	}
	
	
	
	
	
	
	
	
	private String getLegalInfo() {
		// TODO Auto-generated method stub
		String noGooglePlay="It appears Google Play services have not been intsalled on your mobile device";
		String openSourceSoftwareLicenseInfo =
	            GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo(this);
	        if(openSourceSoftwareLicenseInfo == null){
	            return noGooglePlay;
	        }
	        else {
	        	return openSourceSoftwareLicenseInfo;
	        }
	}








	protected void onStart(){
		super.onStart();
	}

}
