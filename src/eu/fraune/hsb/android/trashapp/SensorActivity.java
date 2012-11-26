package eu.fraune.hsb.android.trashapp;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class SensorActivity extends Activity implements SensorEventListener {
	  private SensorManager mSensorManager;
	  private Sensor mLight;

	  @Override
	  public final void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

	    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	  }

	  public final void onAccuracyChanged(Sensor sensor, int accuracy) {
	    // Do something here if sensor accuracy changes.
	  }

	  public final void onSensorChanged(SensorEvent event) {
	    // The light sensor returns a single value.
	    // Many sensors return 3 values, one for each axis.
	   Toast.makeText(this,"changed", Toast.LENGTH_LONG).show();
	    // Do something with this sensor value.
	  }

	  @Override
	  protected void onResume() {
	    super.onResume();
	    mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
	  }

	  @Override
	  protected void onPause() {
	    super.onPause();
	    mSensorManager.unregisterListener(this);
	  }
	}
