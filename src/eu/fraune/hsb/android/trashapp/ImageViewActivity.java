package eu.fraune.hsb.android.trashapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageViewActivity extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i("ImageViewActivity", "onCreate wurde aufgerufen"); 
		setContentView(R.layout.activity_imageview);
		
		Intent intent = getIntent();
		ImageView imageView = new ImageView(this);
		
		//Die Bild URI wird aus dem Intent geholt und imageView Ã¼bergeben
	
//		imageView.setImageURI(intent.getData());
		if (intent.hasExtra("Uri")){
				imageView.setImageURI((Uri)intent.getExtras().get("Uri"));
				setContentView(imageView);
		}
		
		getCurrentGPSPosition();
		
	}

	private void getCurrentGPSPosition() {
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
		    public void onLocationChanged(Location location) {
		      // Called when a new location is found by the network location provider.
		      Log.i("GPS IVA", Double.toString(location.getLatitude())+"geografische Breite"); 
		      Log.i("GPS IVA", Double.toString(location.getLongitude())+"geografische Länge");
		      Toast.makeText(getApplicationContext(), "Breite: " + Double.toString(location.getLatitude()),
	    				Toast.LENGTH_LONG).show();
		      Toast.makeText(getApplicationContext(), "Länge: " + Double.toString(location.getLongitude()),
	    				Toast.LENGTH_LONG).show();
		      
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}
		  };

		// Register the listener with the Location Manager to receive location updates
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		
	}
	

}
