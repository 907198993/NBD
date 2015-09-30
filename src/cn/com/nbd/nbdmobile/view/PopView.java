package cn.com.nbd.nbdmobile.view;

import android.app.Activity;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

public abstract class PopView extends PopupWindow {

	protected Activity 			mOwner ;
	protected LayoutInflater 	mInfalter ;
	
	public PopView(Activity owner,int width,int height){
		super(new FrameLayout(owner),width,height);
		
		mOwner		= owner ;
		mInfalter	= LayoutInflater.from(owner);
	}
	
	protected  int getIdByName(String className, String name) {  
        String packageName = mOwner.getPackageName();  
        Class r = null;  
        int id = 0;  
        try {  
            r = Class.forName(packageName + ".R");  
  
            Class[] classes = r.getClasses();  
            Class desireClass = null;  
  
            for (int i = 0; i < classes.length; ++i) {  
                if (classes[i].getName().split("\\$")[1].equals(className)) {  
                    desireClass = classes[i];  
                    break;  
                }  
            }  
  
            if (desireClass != null)  
                id = desireClass.getField(name).getInt(desireClass);  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (IllegalArgumentException e) {  
            e.printStackTrace();  
        } catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        } catch (NoSuchFieldException e) {  
            e.printStackTrace();  
        }  
  
        return id;  
    }  
}
