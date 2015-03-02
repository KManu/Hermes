package com.MIGH.hermes;


import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.widget.*;

import com.MIGH.hermes.DrawerListAdapter;



public class MainActivity extends ActionBarActivity {

	DrawerLayout drawerLayout;
	ListView drawerList;
	ActionBarDrawerToggle actionBarDrawerToggle;
	Context context;
	GridView gridView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context= this;
		
		//Open the database
		DBAdapter  DB= new DBAdapter(this);
		DB.open();
		
		
		String []drawerOptions ={"View All Companies","Categories","Search","Saved Companies"};
		
		// Definitions of widgets and views on the main page 
		gridView =  (GridView) findViewById(R.id.Main_GridView);
		drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
		drawerList =(ListView) findViewById(R.id.main_drawerList);
		
		
		//Setting up the navigation drawer
		
		//Setting up drawer list view
		DrawerListAdapter drawerListAdapter =new DrawerListAdapter(getApplicationContext(),R.layout.drawer_list_row,drawerOptions);
		drawerList.setAdapter(drawerListAdapter);
		drawerList.setOnItemClickListener(new DrawerItemClickListener());
		
		
		
		// Drawer/ActionBar toggle settings
		 actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
	                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
			 
			 
			 				
	            /** Called when a drawer has settled in a completely closed state. */
	            public void onDrawerClosed(View view) {
	                super.onDrawerClosed(view);
	                getSupportActionBar().setTitle("Hermes");
	            }

	            /** Called when a drawer has settled in a completely open state. */
	            public void onDrawerOpened(View drawerView) {
	                super.onDrawerOpened(drawerView);
	                getSupportActionBar().setTitle("Hermes");
	                
	            }
	        };
	        
	    getSupportActionBar().setDisplayHomeAsUpEnabled(true);    
	    actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);    
		drawerLayout.setDrawerListener(actionBarDrawerToggle);
		
		// set up the grid view
			// get the random companies to display. 12 in number for a 3x4 grid
		ArrayList<CompanyModel> chosen = null ; 
		
		chosen = DB.getAllRows();		
		GridAdaptor gridAdaptor = new GridAdaptor(context, chosen);
		gridView.setAdapter(gridAdaptor);

		
		
		
		
		// Below is all going to be thrown out. 
		
		/*
		
		//Typeface trajanPro3 = Typeface.createFromAsset(getAssets(),"fonts/TrajanPro3-Regular.otf");
	//	Typeface caslonR = Typeface.createFromAsset(getAssets(), "fonts/ACaslonPro-Regular.otf");
		Typeface calibri = Typeface.createFromAsset(getAssets(), "fonts/calibril.ttf");	
		
		
		// Setting the fonts for the text views in the layout
		mapLabel.setTypeface(calibri);
		listingsLabel.setTypeface(calibri);
		searchLabel.setTypeface(calibri);
		profileButton.setTypeface(calibri);
		
		
		//OnClick listener for the profile button
		profileButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent goToProfile = new Intent(getApplicationContext(), CompanyProfiles.class);
				startActivity(goToProfile);
			}
		});
		
		//When the user clicks on the map button, it takes him to the map activity
		mapButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent goToMap= new Intent(getApplicationContext(),MapPage.class);
				startActivity(goToMap);
			}	
		});
		
		//When the user clicks on the listings buttons, takes him to listing activity
		listingsButton.setOnClickListener(new View.OnClickListener() {	
			 @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent goToListing = new Intent (getApplicationContext(), Listings.class);
				startActivity(goToListing);
				
			}
		});
		
		// The Search button triggers the search dialog which accepts, and passes data to the search activity
		
		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onSearchRequested();
				
				
			}
		});
		*/
		
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent ProfileViewIntent = new Intent(getApplicationContext(), CompanyProfiles.class);
				//String pos= position+"";
				CompanyModel selected= (CompanyModel) parent.getItemAtPosition(position);
				String compCIN = selected.getCIN()+"";
				ProfileViewIntent.putExtra("SELECTED_FROM_LIST", compCIN);
				startActivity(ProfileViewIntent);
				
			}
		});
		
		//End of onCreate
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
 	}
	
	//Action bar drawer toggle methods
	 @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        // Sync the toggle state after onRestoreInstanceState has occurred.
	        actionBarDrawerToggle.syncState();
	    }

	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        actionBarDrawerToggle.onConfigurationChanged(newConfig);
	    }
	    
	    
	    
	// 
	@Override
	public boolean onOptionsItemSelected (MenuItem item){
		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
	          return true;
	        }
		Intent chosen = new Intent (getApplicationContext(), About.class);
	
		switch (item.getItemId()){
		case R.id.action_licence:
			chosen.putExtra("Selected", "Licence");
			startActivity(chosen);
			return true;
		case R.id.action_aboutInfo:
			chosen.putExtra("Selected", "About");
			startActivity(chosen);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
				
				
		
	}
	
	
	//Drawer on click shiii
	public class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
        	 String option=(String) parent.getItemAtPosition(position);
             selectItem(option);
         }

 		private void selectItem(String dOption) {
 			 Intent fastTravel;// Little video game joke :D
 			
 			 if(dOption.equals("View All Companies")){
 				 	fastTravel = new Intent(context, Listings.class);
 					startActivity(fastTravel);
 					return;
 				}
 			else if(dOption.equals("Categories")){
 					Toast a = Toast.makeText(context, "This would show the categories page", Toast.LENGTH_SHORT);
 					a.show();
 					return;
 				}
 			else if(dOption.equals("Search")){
 					onSearchRequested();
 					return;
 				}
 			else if(dOption.equals("Saved Companies")){
 					Toast b = Toast.makeText(context, "This would show the saved/favorited companies page", Toast.LENGTH_SHORT);
 					b.show();
 					return;
 				}
 			else{
 				return;
 			}	
 		}
    }
	
	
	
	
	

}
