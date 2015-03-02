/**
 * 
 */
package com.MIGH.hermes;


import java.util.ArrayList;








//import android.app.Activity;
//import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
//import android.net.Uri;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
//import android.widget.AdapterView.OnItemClickListener;
import android.support.v7.app.*;
import android.view.*;

import com.MIGH.hermes.CompanyModel;
import com.MIGH.hermes.CompanyListAdapter;
import com.MIGH.hermes.DrawerListAdapter;


public class Listings extends ActionBarActivity {	
	
	ListView listingsPage;
	Context context;
	DBAdapter DBListingsAdapter;
	CompanyListAdapter companyListAdapter;
	DrawerLayout drawerLayout;
	ListView drawerList;
	ActionBarDrawerToggle actionBarDrawerToggle;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listings);
		
		
		// Initializations
		String []drawerOptions={"Search","Categories","Saved Companies"};
		listingsPage = new ListView(this);
		context = this;	
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    actionBar.setTitle("All Companies");
		
		//Create a DBAdapter object when the activity is started and open the database.
		 DBListingsAdapter = new DBAdapter(this);
		 DBListingsAdapter.open();
		 ArrayList<CompanyModel> companies= DBListingsAdapter.getAllRows();		
		 
		
		 //Setting up the list view
		listingsPage = (ListView) findViewById(R.id.list);
		companyListAdapter = new CompanyListAdapter(context, R.layout.custom_list_row, companies);
		listingsPage.setAdapter(companyListAdapter);

		//Navigation Drawer bisshh
		drawerLayout = (DrawerLayout) findViewById(R.id.ListingsPage_drawer_layout);
		drawerList =(ListView) findViewById(R.id.ListingsPage_Drawerlist);
		
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
		
		
		
		// Code to handle onCLick events of list items
		listingsPage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
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

	
	
	
	@Override
	public void onStart() {
		super.onStart();
		// TODO Auto-generated method stub

	}
	
	public void onDestroy(){
		DBListingsAdapter.close();
		super.onDestroy();
		
	}
	
	//Drawer on click shiii
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
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
	
//	public ArrayList<CompanyModel> getTestData(){
//		ArrayList <CompanyModel> testCompanies= new ArrayList<CompanyModel>();	
//		
//		// Turning Kitteh 1 into a byte array
//		Resources res = getResources();
//		Drawable img= res.getDrawable(R.drawable.test_kitteh_1);
//		Bitmap bit=((BitmapDrawable)img).getBitmap();
//		ByteArrayOutputStream stream = new ByteArrayOutputStream();
//		bit.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//		byte [] kitteh1= stream.toByteArray();
//		
//		// Kitteh 2 creation
//		img = res.getDrawable(R.drawable.test_kitteh_2);
//		bit = ((BitmapDrawable)img).getBitmap();
//		stream = new ByteArrayOutputStream();
//		bit.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//		byte [] kitteh2= stream.toByteArray();
//		
//		//Advert creation
//		img = res.getDrawable(R.drawable.main_searchicon);
//		bit = ((BitmapDrawable)img).getBitmap();
//		stream = new ByteArrayOutputStream();
//		bit.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//		byte [] advertKitteh = stream.toByteArray();
//		
//		
//		CompanyModel Comp1= new CompanyModel(
//				1,
//				"KojoKrom",
//				"025064087 021512770",
//				"Legon",
//				kitteh1,
//				"www.DoMore.com",
//				"donothing@domore.com",
//				"Sports",
//				"None Available",
//				"A top local sports brand bringing the best in male and female sports wear. Quality siano sandals",
//				"None Available",
//				"Sports Clothing Siano Shoes",
//				advertKitteh,
//				"None Available",
//				"None Available",
//				"None Available");
//		testCompanies.add(Comp1);
//		
//		CompanyModel Comp2= new CompanyModel(
//				2,
//				"Little Woodland Critters",
//				"0987456327 8768543009",
//				"Tema",
//				kitteh2,
//				"www.DoMore.com",
//				"donothing@domore.com",
//				"Sports",
//				"None Available",
//				"A top local sports brand bringing the best in male and female sports wear. Quality siano sandals",
//				"None Available",
//				"Sports Clothing Siano Shoes",
//				advertKitteh,
//				"None Available",
//				"None Available",
//				"None Available");
//		testCompanies.add(Comp2);
//		
//		return testCompanies;
//	}

}
