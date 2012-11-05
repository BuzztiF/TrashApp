package eu.fraune.hsb.android.trashapp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;
	private Uri fileUri;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
    	return Uri.fromFile(getOutputMediaFile(type));
    }
    
    public void startCam(View view){
    	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    	fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
    	intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
    	startActivityForResult(intent, 5);
    }

    
    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
    	File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
    			Environment.DIRECTORY_PICTURES), "TrashApp");
    	if (!mediaStorageDir.exists()){
    		if(!mediaStorageDir.mkdirs()){
    			Log.d("TrashApp", "failed to create directory");
    			return null;
    		}
    	}
    	
    	
    // Create a media file name
    String timeStamp = new SimpleDateFormat("HHmmss_ddMMyyyy").format(new Date());
    File mediaFile = null;
    if(type==MEDIA_TYPE_IMAGE){
    	mediaFile = new File(mediaStorageDir.getPath()
    			+ File.separator + "TA" +timeStamp + ".jpg");
    }else if (type==MEDIA_TYPE_VIDEO){
    	Log.d("TrashApp", "Videos nicht erlaubt");
    	return null;
    }
    return mediaFile;
    }
    
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 5;
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    	if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
    		Toast.makeText(this,"Image Saved to:\n" + data.getData(),
    				Toast.LENGTH_LONG).show();
    		
    		Intent intent = new Intent(this,ImageViewActivity.class);
    		intent.setData(data.getData());
    		startActivity(intent);
    		
    	}else if (resultCode != CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
    		Toast.makeText(this, "Canceled", Toast.LENGTH_LONG).show();
    	}
    }
}
