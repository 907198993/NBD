package cn.com.nbd.nbdmobile.tool;

import android.app.Activity;
import android.util.DisplayMetrics;

public class BaseTools {
	
	public final static int getWindowsWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
	
	
	public final static int getWindowHight(Activity activity){
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return   dm.heightPixels;
	}
	
	
}
