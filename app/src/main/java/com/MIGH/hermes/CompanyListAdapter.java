package com.MIGH.hermes;

import java.util.ArrayList;






import com.bumptech.glide.Glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CompanyListAdapter extends ArrayAdapter<CompanyModel> {
 	
	private ArrayList<CompanyModel> Companies;
	private LayoutInflater inflater = null;
	Context here;
	public CompanyListAdapter(Context context, int resourceID, ArrayList<CompanyModel> companies) {
		super(context, resourceID);
		here = context;
		this.Companies = companies;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Companies.size();
	}

	@Override
	public CompanyModel getItem(int position) {
		// TODO Auto-generated method stub
		return Companies.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		Typeface calibri = Typeface.createFromAsset(here.getAssets(), "fonts/calibril.ttf");	
		
		if (v == null)
			 v= inflater.inflate(R.layout.custom_list_row,parent, false);
		
		CompanyModel current = Companies.get(position);
				
		
			
			//Despite the Listings prefix to the ids, these are not located on the Listings layout. They are at the custom_list_row.xml
			ImageView CompanyLogo = (ImageView) v.findViewById(R.id.Listings_CompanyLogo);
			TextView CompanyName = (TextView) v.findViewById(R.id.Listings_CompanyName);
			TextView CompanyDescription = (TextView) v.findViewById(R.id.Listings_CompanyDescription);
			TextView CompanyLocation= (TextView)v.findViewById(R.id.Listings_CompanyLocation);
			
				
				Glide.load(current.getLogo())
					.placeholder(R.drawable.comp_placeholder)
					.animate(R.anim.abc_fade_in)
					.error(R.drawable.comp_placeholder)
					.into(CompanyLogo);
			
			
				CompanyName.setText(current.getName());
				CompanyName.setTypeface(calibri);
				CompanyName.setTextAppearance(here, R.id.Listings_CompanyName);
				
				
				CompanyDescription.setText(current.getDescription());
				CompanyDescription.setTypeface(calibri);
				CompanyDescription.setTextAppearance(here, R.id.Listings_CompanyDescription);
				
				CompanyLocation.setText(current.getLocation());
				//CompanyLocation.setTypeface(calibri);
				//CompanyLocation.setTextAppearance(here, R.id.Listings_CompanyLocation);
		
		return v;
	}
	
	

}
