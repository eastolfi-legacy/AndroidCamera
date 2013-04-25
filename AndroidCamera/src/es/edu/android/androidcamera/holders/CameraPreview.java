package es.edu.android.androidcamera.holders;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback2 {
	private SurfaceHolder mHolder;
	private Camera mCamera;
	
	public CameraPreview(Context context, Camera camera) {
		super(context);
		
		//Obtenemos la camara
		mCamera = camera;
		
		//Obtenemos el SurfaceHolder y lo modificamos
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setKeepScreenOn(true);
		//Ajuste necesario para versiones <3.0
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		try {
			//Añadimos la preview a la camara
			mCamera.setPreviewDisplay(mHolder);
			//Modificamos cualquier parametro inicial
			mCamera.setDisplayOrientation(90);	//Giramos la orientacion porque la camara es en retrato
			//Iniciamos la preview
			mCamera.startPreview();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		//Si no tenemos SurfaceHolder salimos
		if (mHolder == null) {
			return;
		}
		
		try {
			//Añadimos la preview a la camara
			mCamera.setPreviewDisplay(mHolder);
			//Iniciamos la preview
			mCamera.startPreview();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		//La camara se liberara en la actividad	TODO:Revisar ciclo de vida
		if (mCamera != null) {
			try {
				mCamera.stopPreview();
			} catch (Exception e) {
				
			}
		}
	}

	@Override
	public void surfaceRedrawNeeded(SurfaceHolder arg0) {}

}
