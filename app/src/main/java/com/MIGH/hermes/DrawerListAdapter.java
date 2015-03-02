package com.MIGH.hermes;

import android.content.Context;
import android.view.*;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerListAdapter extends ArrayAdapter<String> {
	
	private String[] DrawerOptions;
	private LayoutInflater inflater=null;

	public DrawerListAdapter(Context context, int resource, String[] objects) {
		super(context, resource, objects);
		this.DrawerOptions = objects;
		
		inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override 
	public int getCount(){
		return DrawerOptions.length;
	}
	
	@Override
	public String getItem(int position){
		
		return DrawerOptions[position];
	}
	
	@Override
	public long getItemId(int position){
		return position;
	}	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(v==null){
			v=inflater.inflate(R.layout.drawer_list_row, parent, false); 
		}
		
		String dOption = DrawerOptions[position];
		
		ImageView listIcon =(ImageView) v.findViewById(R.id.DrawerListRow_Icon);
		TextView optiontoDisplay=(TextView) v.findViewById(R.id.DrawerListRow_textView);
		
		if(dOption.equals("View All Companies")){
			listIcon.setImageResource(R.drawable.ic_drawer_listings);
		}
		else if(dOption.equals("Categories")){
			listIcon.setImageResource(R.drawable.ic_drawer_categories);
		}
		else if(dOption.equals("Search")){
			listIcon.setImageResource(R.drawable.ic_drawer_search);
		}
		else if(dOption.equals("Saved Companies")){
			listIcon.setImageResource(R.drawable.ic_drawer_saved);
		}
		else{
			listIcon.setImageResource(R.drawable.ic_launcher);
		}
		
		listIcon.setPadding(1, 1, 1, 1);
		optiontoDisplay.setText(dOption);
		
		return v;
	}
	

	
}
