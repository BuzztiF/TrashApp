package eu.fraune.hsb.android.trashapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class GPSService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the gps location provider.
				Log.i("GPS IVA", Double.toString(location.getLatitude())+"geografische Breite"); 
				Log.i("GPS IVA", Double.toString(location.getLongitude())+"geografische Länge");
				Toast.makeText(getApplicationContext(), "Breite: " + Double.toString(location.getLatitude()),
			    				Toast.LENGTH_LONG).show();
				Toast.makeText(getApplicationContext(), "Länge: " + Double.toString(location.getLongitude()),
			    				Toast.LENGTH_LONG).show();
				Intent dataIntent = new Intent("GOT_GPS_POSITION");
				dataIntent.putExtra("latitude", location.getLatitude());
				dataIntent.putExtra("longitude", location.getLongitude());
				sendBroadcast(dataIntent);
			}
			
			public void onStatusChanged(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}
		};		      
		
		
		// Register the listener with the Location Manager to receive location updates
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
	}
	
	

}
