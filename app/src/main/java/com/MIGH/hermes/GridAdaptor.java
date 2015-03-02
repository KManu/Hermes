package com.MIGH.hermes;

import java.util.ArrayList;

import com.bumptech.glide.Glide;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridAdaptor extends BaseAdapter{
	Context here;
	ArrayList <CompanyModel>arrayList;
	
	public GridAdaptor(Context context, ArrayList<CompanyModel> chosen) {
		// TODO Auto-generated constructor stub
		here= context;
		arrayList= chosen;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();
	}

	@Override
	public CompanyModel getItem(int position) {
		// TODO Auto-generated method stub
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(here);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(2, 2, 2, 2);
        } else {
            imageView = (ImageView) convertView;
        }
        
        CompanyModel current = arrayList.get(position);


        Glide.load(current.getLogo())
        	.animate(R.anim.abc_fade_in)
        	.placeholder(R.drawable.comp_placeholder)
        	.error(R.drawable.comp_placeholder)
        	.into(imageView);
        
        return imageView;


	}

}
