package com.dpt.base;

import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import cn.com.nbd.nbdmobile.config.AppPresences;

import com.dpt.bean.ResultObject;
import com.dpt.config.BaseConfig;


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
	
	protected String getUserKey(){
		return	AppPresences.getInstance().getString(BaseConfig.KEY_USER_KEY);
	}
}
