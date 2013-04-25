package es.edu.android.androidcamera.activities;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.FrameLayout;
import es.edu.android.androidcamera.R;
import es.edu.android.androidcamera.holders.CameraPreview;
import es.edu.android.androidcamera.utils.CameraUtils;
import es.edu.android.androidcamera.widget.Panel;

public class CameraActivity extends Activity {
	CameraActivity mParent;
	Panel panelOptions;
	Button panelHandler;
	Camera mCamera;
	CameraPreview cameraPreview;
	FrameLayout preview;
	Integer cameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		
		mParent = this;
		
		panelOptions = (Panel) findViewById(R.id.panelOptions);
		panelHandler = (Button) findViewById(R.id.panelHandle);
		
		mCamera = openCamera();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera, menu);
		return true;
	}
	
	protected void onPause() {
		if (mCamera != null) {
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}
		super.onPause();
	}
	
	/***********************************/
	private Camera openCamera() {
		Camera c = mCamera;
//		if (mCamera != null) c = mCamera;
		if (CameraUtils.hasCamera(mParent)) {
			try {
				if (c != null) {
					c.stopPreview();
					c.release();
					c = null;
				}
				if (cameraId != null) {
					c = Camera.open(cameraId);
				}
				else {
					c = Camera.open();
				}
				
				Camera.Parameters params = c.getParameters();
				params.setPictureSize(600, 480);
				c.setParameters(params);
				
				preview = (FrameLayout) findViewById(R.id.cameraPreview);
				if (cameraPreview != null) {
					preview.removeView(cameraPreview);
				}
				cameraPreview = new CameraPreview(mParent, c);
				preview.addView(cameraPreview);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		prepareScreen();
		
		return c;
	}
	
	private void prepareScreen() {
		//Comprobar zoom
		//Comprobar focus
		//Mover elementos al frente
//		btSwichCamera.bringToFront();
//		btCameraOptions.bringToFront();
		panelOptions.bringToFront();
		panelHandler.bringToFront();
	}

}
