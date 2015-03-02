/**
 * 
 */
package com.MIGH.hermes;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import com.bumptech.glide.Glide;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.*;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.*;



public class CompanyProfiles extends ActionBarActivity {
	ImageView CompanyLogo;
	TextView CompanyName;
	TextView CompanyDescription;
	ListView CompanyPhoneNumbers;
	TextView CompanyLocation;
	TextView PhoneNumberLabel;
	TextView LocationLabel;
	ImageView WebButton;
	ImageView MapButton;
	ImageView FavButton;
	RelativeLayout back;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.companyprofile_flat);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		// receiving intent
		Intent compToDisp = getIntent();
		String compID= new String();
		compID = compToDisp.getStringExtra("SELECTED_FROM_LIST");
		final CompanyModel profileToDisplay = getComp(compID);
		
		actionBar.setTitle("");
		
		
		//Array of colors to supply to background
		int [] colors={
				R.color.flat_bright_green,
				R.color.flat_bright_red,
				R.color.flat_dark_green,
				R.color.flat_dark_red,
				R.color.flat_deep_blue,
				R.color.flat_deep_purple,
				R.color.flat_gold,
				R.color.flat_light_blue,
				R.color.flat_light_purple,
				R.color.flat_orange
				};
		

		
		
		//Toast to test if everything is ok
		//String testName = profileToDisplay.getName() ;
		//Toast toast = Toast.makeText(getApplicationContext(), testName, Toast.LENGTH_SHORT);
		//toast.show();
		
		
		//View declarations
//		
//		 CompanyLogo = (ImageView) findViewById(R.id.ProfilePage_CompanyLogo);
//		 CompanyName = (TextView) findViewById(R.id.ProfilePage_CompanyName);
//		 CompanyDescription = (TextView) findViewById(R.id.ProfilePage_CompanyDescription);
//		 CompanyPhoneNumbers = (ListView) findViewById(R.id.ProfilePage_PhoneNumbersListView);
//		 CompanyLocation = (TextView) findViewById(R.id.ProfilePage_CompanyLocation);
//		 WebButton = (ImageButton) findViewById(R.id.ProfilePage_WebsiteButton);
//		 MapButton = (ImageButton) findViewById(R.id.ProfilePage_MapButton);
//		 PhoneNumberLabel = (TextView) findViewById(R.id.ProfilePage__PhoneNumbLabel);
//		 LocationLabel =(TextView) findViewById(R.id.ProfilePage_LocationLabel);
		
		
			back =(RelativeLayout) findViewById(R.id.ProfilePageFlat_RelativeBack);
			CompanyLogo= (ImageView) findViewById(R.id.ProfilePageFlat_CompanyLogo);
			CompanyName = (TextView) findViewById(R.id.ProfilePageFlat_CompanyName);
			CompanyLogo = (ImageView) findViewById(R.id.ProfilePageFlat_CompanyLogo);
			CompanyName = (TextView) findViewById(R.id.ProfilePageFlat_CompanyName);
			CompanyDescription = (TextView) findViewById(R.id.ProfilePageFlat_CompanyDescription);
			CompanyPhoneNumbers = (ListView) findViewById(R.id.ProfilePageFlat_PhoneNumberList);
			CompanyLocation = (TextView) findViewById(R.id.ProfilePageFlat_CompanyLocation);
			WebButton = (ImageView) findViewById(R.id.ProfilePageFlat_WebButton);
			MapButton = (ImageView) findViewById(R.id.ProfilePageFlat_MapButton);
			FavButton =(ImageView) findViewById(R.id.ProfilePageFlat_FavButton);
			PhoneNumberLabel = (TextView) findViewById(R.id.ProfilePageFlat_PhoneNumbersLabel);
			LocationLabel =(TextView) findViewById(R.id.ProfilePageFlat_LocationLabel);
		
		
		// Setting up background to a random color
			Random random = new Random();
			int chosenColor = colors[random.nextInt(colors.length-1)];
			back.setBackgroundResource(chosenColor );
			ColorDrawable actionBarColor = new ColorDrawable(getResources().getColor(chosenColor));
			actionBar.setBackgroundDrawable(actionBarColor);
		
		
		//Setting the content views to content from the CompanyModel object
			String imgLoc = profileToDisplay.getLogo();
			Glide.load(imgLoc)
				.placeholder(R.drawable.comp_placeholder)
				.animate(R.anim.abc_fade_in)
				.error(R.drawable.comp_placeholder)
				.into(CompanyLogo);
		
		
		
		CompanyName.setText(profileToDisplay.getName());
		CompanyDescription.setText(profileToDisplay.getDescription());
		//CompanyPhoneNumbers   Skip phone numbers till list is setup
		CompanyLocation.setText(profileToDisplay.getLocation());
		
		
		// Setting up the Phone Number List View
		String [] phoneNumbs=profileToDisplay.getPhone() ;
		PhoneNumberListAdapter numbsAdapter=new PhoneNumberListAdapter(getApplicationContext(),R.layout.phone_list_row,phoneNumbs);
		CompanyPhoneNumbers.setAdapter(numbsAdapter);
		
		//Fonts instantiations from res assets
		//Typeface trajanPro3 = Typeface.createFromAsset(getAssets(),"fonts/TrajanPro3-Regular.otf");
		//Typeface caslonR = Typeface.createFromAsset(getAssets(), "fonts/ACaslonPro-Regular.otf");
		Typeface calibri = Typeface.createFromAsset(getAssets(), "fonts/calibril.ttf");	
	
		//Setting fonts for textViews
		CompanyName.setTypeface(calibri, R.id.ProfilePageFlat_CompanyName);
		CompanyDescription.setTypeface(calibri,R.id.ProfilePageFlat_CompanyDescription);
		CompanyLocation.setTypeface(calibri,R.id.ProfilePageFlat_CompanyLocation);
		PhoneNumberLabel.setTypeface(calibri,R.id.ProfilePageFlat_PhoneNumbersLabel);
		LocationLabel.setTypeface(calibri,R.id.ProfilePageFlat_LocationLabel);
		
		
		
		
		// Misc View settings
		// Setting scroll bars on description and location text.
		CompanyDescription.setMovementMethod(ScrollingMovementMethod.getInstance());
		CompanyLocation.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		//Button settings 
		
		// List view click event handling
		CompanyPhoneNumbers.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String numbToCall=null ;
				numbToCall =(String) parent.getItemAtPosition(position);
				numbToCall="tel:"+numbToCall;
				Intent toDialerIntent = new Intent(Intent.ACTION_DIAL);
				toDialerIntent.setData(Uri.parse(numbToCall));
				startActivity(toDialerIntent);
			}
		});
		
		
		//Open the map and pass the GPS coordinates when you click the map button
		MapButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent FindOnMap=new Intent(getApplicationContext(), MapPage.class);
				String GPS= profileToDisplay.getGPS();
				String Name = profileToDisplay.getName();
				// GPS NOT DEFINED YET
				FindOnMap.putExtra("GPS_LOCATION", GPS);
				FindOnMap.putExtra("COMPANY_NAME", Name);
				startActivity(FindOnMap);
			}
		});
		
		//Change the fav icon when its selected and add company to fav list
		FavButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				FavButton.setImageResource(R.drawable.ic_profile_fav_selected);
				
				//Add to favs, the display toast
				Toast faved= Toast.makeText(getApplicationContext(), "Added to Saved Companies", Toast.LENGTH_SHORT);
				//faved.show();
				
				Toast extra=Toast.makeText(getApplicationContext(), "Actual company faving has not yet been implemented. The button just changes colors. :)", Toast.LENGTH_LONG);
				extra.show();
				
			}
		});
		
		
		// Show the website details when you click the web button
		WebButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Maybe create an interactive dialogue containing internet data about the company? Website,FB, etc?
				String website = profileToDisplay.getWebsite();
				Toast webSiteToast = Toast.makeText(getApplicationContext(), website, Toast.LENGTH_SHORT);
				webSiteToast.show();
			}
		});
		
		

		

	}
	


	
	@Override
	public void onStart() {
		super.onStart();
		// TODO Auto-generated method stub

	}
	
	public CompanyModel getComp(String CIN){
		CompanyModel gotten= null;
		DBAdapter adapter = new DBAdapter(getApplicationContext());
		adapter.open();
		
		gotten = adapter.getRow(CIN);
		
		adapter.close();
		return gotten;
		
	}

}
