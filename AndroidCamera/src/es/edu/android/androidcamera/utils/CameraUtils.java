package es.edu.android.androidcamera.utils;

import android.content.Context;
import android.content.pm.PackageManager;

public class CameraUtils {
	
	public static boolean hasCamera(Context context) {
		if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
}
