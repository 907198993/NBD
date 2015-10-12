package cn.com.nbd.nbdmobile.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import org.hjh.inject.InjectCore;

import java.util.List;

public final class AppPublicAdapter extends AppBaseAdapter {

	private Class mClass;
	private IFillValue callback;
	
	public AppPublicAdapter(Context context,List list,Class zClass,IFillValue iFillValue) {
		super(context);
		mClass = zClass;
		callback = iFillValue;
		setList(list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Object holder = null;
		if(convertView == null){
			try {
				holder = mClass.newInstance();
				convertView = InjectCore.injectObject(holder, mContext, false);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}else{
			holder = convertView.getTag();
		}
		
		if(callback != null){
			callback.fillData(position,holder);
		}
		
		return convertView;
	}
	
	public interface IFillValue{
		void fillData(int position, Object object);
	}
	
}
