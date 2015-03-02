/**
 * 
 */
package com.MIGH.hermes;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.*;
import android.content.Context;
import android.content.Intent;


/**
 * @author Kobby
 *
 */
public class SearchPage extends ActionBarActivity {

	ListView SearchResultsList;
	Context context;
	CompanyListAdapter searchListAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchpage);
		//Initializations
		context = this;
		SearchResultsList = new ListView(context);
		ActionBar actionBar= getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("Search Results");
		
		// Catching the search intent after its been sent
		Intent SearchIntent = getIntent();

		
		SearchResultsList = (ListView)findViewById(R.id.list);
		
		// Nested methods which check and retrieve information from the intent, and then search based on the query retirved
		ArrayList<CompanyModel> searchResults =  doSearch(checkIntent(SearchIntent));
		// If the result is not found, you are told so.
		if (searchResults.isEmpty()){
			String notFoundMessage = "No results found for the search on "+ checkIntent(SearchIntent);
			Toast notFoundToast = Toast.makeText(context,notFoundMessage , Toast.LENGTH_SHORT);
			notFoundToast.show();
			
		}
		else {
			searchListAdapter = new CompanyListAdapter(context, R.layout.custom_list_row, searchResults);
			SearchResultsList.setAdapter(searchListAdapter);
		}
		
		
		
		SearchResultsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
	
	private String checkIntent(Intent SearchIntent){
		//If the sent intent is the right intent, then extract the search query from it and perform the search using the query.
		String query = new String();
		
		String intentAction = SearchIntent.getAction();
		
		if (Intent.ACTION_SEARCH.equals(intentAction)){
			query = SearchIntent.getStringExtra(SearchManager.QUERY);
		}
		return query;
	}

	
	public ArrayList<CompanyModel> doSearch(String query){
		// This code is what would pass the search query to the DBAdapter
		context = getApplicationContext();
		
		// Test Toast to make sure the right information has been received by the activity
		//int duration = Toast.LENGTH_SHORT;
		//Toast workedToast = Toast.makeText(context, query, duration);
		//workedToast.show();
		
		DBAdapter searchAdapter = new DBAdapter(context);
		searchAdapter.open();
		ArrayList<CompanyModel> searchResults=null;// = new ArrayList<CompanyModel>();
		
		searchResults = searchAdapter.searchRow(query);
		return searchResults;
		
	}
	
	

	@Override
	public void onStart() {
		super.onStart();
		// TODO Auto-generated method stub

	}

}
