package eu.fraune.hsb.android.trashapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageViewActivity extends Activity implements LocationListener {
	
	
	private LocationManager lm;
	private Uri uri;
	
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i("ImageViewActivity", "onCreate wurde aufgerufen"); 
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		
		Intent intent = getIntent();
//		ImageView imageView = new ImageView(this);
		
		setContentView(R.layout.activity_imageview);
		ImageView imageView = (ImageView) findViewById(R.id.imageView1);
		
		//Die Bild URI wird aus dem Intent geholt und imageView übergeben
	
//		imageView.setImageURI(intent.getData());
		if (intent.hasExtra("Uri")){
				uri=(Uri)intent.getExtras().get("Uri");
				
				
				Bitmap myBitmap = BitmapFactory.decodeFile(uri.getPath());
				Log.i("BITMAPXXXXXXXXXXXXXXXX", myBitmap.getConfig().toString());
				
				Bitmap rotatedBitmap = rotate(myBitmap, 90);
				
		        imageView.setImageBitmap(rotatedBitmap);
				
				
//				imageView.setImageURI(uri);
				
				
//				setContentView(imageView);
		}
		
		
//		getCurrentGPSPosition();
		
		
		
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
		
	}
	
	
	private Bitmap rotate(Bitmap b, int degrees) {
	    if (degrees != 0 && b != null) {
	        Matrix m = new Matrix();

	        m.setRotate(degrees, (float) b.getWidth() / 2, (float) b.getHeight() / 2);
	        try {
	            Bitmap b2 = Bitmap.createBitmap(
	                    b, 0, 0, b.getWidth(), b.getHeight(), m, true);
	            if (b != b2) {
	                b.recycle();
	                b = b2;
	            }
	        } catch (OutOfMemoryError ex) {
	           throw ex;
	        }
	    }
	    return b;
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, this);
		Log.i("GPS", "GPSListener registriert");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		lm.removeUpdates(this);
		Log.i("GPS", "GPSListener wieder nicht mehr registriert");
		
	}
	

	public void onLocationChanged(Location location) {
		Toast.makeText(getApplicationContext(), "IF Breite: " + Double.toString(location.getLatitude()),
				Toast.LENGTH_LONG).show();
      Toast.makeText(getApplicationContext(), " IF L�nge: " + Double.toString(location.getLongitude()),
				Toast.LENGTH_LONG).show();
      
      
		
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	

}
