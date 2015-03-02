/**
 * 
 */
package com.MIGH.hermes;



import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.PendingIntent.OnFinished;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

/**
 * @author Kobby
 *
 */
public class MapPage extends FragmentActivity {
	private GoogleMap ghMap;
	CameraUpdate initPos;
	CameraUpdate dest;
	LatLng defLatLng = new LatLng(5.641650, -0.151963);
	LatLng destination;
	String compName;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		Intent CoordIntent = getIntent();
		String coords= new String();
		coords = CoordIntent.getStringExtra("GPS_LOCATION"); // CoordIntent is what would be passed to the mapView to locate the point on the map
		if(coords==null){
			if(ghMap==null){
				ghMap =((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.MapPage_map)).getMap();
				if(ghMap!=null){
					ghMap.setMyLocationEnabled(true);
					CameraUpdate defCamView;
					defCamView = CameraUpdateFactory.newLatLngZoom(defLatLng, 10);
					ghMap.moveCamera(defCamView);
		     	 }
				
			
			}
			return;
		}
		
		compName = CoordIntent.getStringExtra("COMPANY_NAME");
		destination = parseLatLng(coords);
		
		
		
			setInitialPosition();	
			setupMap();
		
		
		
		
		
		
		
	}

	



	private LatLng parseLatLng(String coords) {
		String [] parts = coords.split(",");
		Double lat = Double.parseDouble(parts[0]);
		Double lng =Double.parseDouble(parts[1]);
		LatLng destCoords = new LatLng(lat, lng);
		
		return destCoords;
	}





	private void setInitialPosition() {
		// The value passed to the method should be the LatLng from the intent which started the activity
		
		initPos= CameraUpdateFactory.newLatLngZoom(new LatLng(0,0),6);

		dest = CameraUpdateFactory.newLatLngZoom(destination, 13);
		
	}


	private void setupMap() {
		// TODO Auto-generated method stub'
		if(ghMap==null){
			ghMap =((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.MapPage_map)).getMap();
			if(ghMap!=null){
				ghMap.setMyLocationEnabled(true);
				startupMap();
	     	 }
			
		
		}
		
	}


	private void startupMap() {
		// TODO Auto-generated method stub
		ghMap.moveCamera(initPos);
		ghMap.animateCamera(dest);
		ghMap.addMarker(new MarkerOptions().position(destination).title(compName));
		
		
		
	}


	@Override
	public void onStart() {
		super.onStart();
		
		
	}
	
	public void onResume(){
		super.onResume();
		setupMap();
	}

}
