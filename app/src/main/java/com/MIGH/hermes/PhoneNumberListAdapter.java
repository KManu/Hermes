package com.MIGH.hermes;

import android.content.Context;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class PhoneNumberListAdapter extends ArrayAdapter<String> {
	private String[] PhoneNumbers;
	private LayoutInflater inflater= null;

	public PhoneNumberListAdapter(Context context, int resource,
			String[] objects) {
		super(context, resource, objects);
		this.PhoneNumbers = objects;
		inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	
	@Override 
	public int getCount(){
		return PhoneNumbers.length;
	}
	
	@Override
	public String getItem(int position){
		
		return PhoneNumbers[position];
	}
	
	@Override
	public long getItemId(int position){
		return position;
	}	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(v==null){
			v=inflater.inflate(R.layout.phone_list_row, parent, false); 
		}
		
		String number = PhoneNumbers[position];
		
		TextView NumberDisplay=(TextView) v.findViewById(R.id.PhoneList_PhoneNumber);
		NumberDisplay.setText(number);
		
		return v;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
