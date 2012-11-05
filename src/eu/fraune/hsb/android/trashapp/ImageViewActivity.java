package eu.fraune.hsb.android.trashapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewActivity extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imageview);
		
		Intent intent = getIntent();
		ImageView imageView = new ImageView(this);
		
		//Die Bild URI wird aus dem Intent geholt und imageView übergeben
		imageView.setImageURI(intent.getData());
		setContentView(imageView);
		
	}
	

}
