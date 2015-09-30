package cn.com.nbd.nbdmobile.api;

import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import cn.com.nbd.nbdmobile.bean.ResultObject;


public abstract class BaseComponent {

	protected static ResultObject result = new ResultObject();
	protected static Context context;
	
	public static void init(Context mContext){
		context = mContext; 
	}
	
	public static final Map<String, Object> withEmptyParamterMap(){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		return map ;
	}
}
