package com.dpt.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hjh.tools.DateTools;
import org.hjh.tools.NumberTools;
import org.hjh.tools.StringTools;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import cn.com.nbd.nbdmobile.config.AppPresences;

import com.dpt.config.BaseConfig;
import com.dpt.config.BaseUrlConfig;
import com.google.gson.Gson;


public abstract class BaseApi {

	protected static HttpExecutor httpExecutor = new HttpExecutor();
	protected Gson gson = new Gson();
	protected final String TOKEN = "token";
	protected String token= "ad13a2a07c" ;
	protected static Context context;
	protected static BaseUrlConfig urlConfig = new BaseUrlConfig() ;
	protected static final String UTF_8 = "UTF-8";
	
	
	public static void init(Context mContext){
		context = mContext; 
	}
	
	protected String getUserKey(){
		return	AppPresences.getInstance().getString(BaseConfig.KEY_USER_KEY);
	}
	
	protected String getToken() {
		return token;
	}

	protected void setToken(String token) {
		this.token = token;
	}

	/**
	 * 团购url
	 * @param action
	 * @param apiInterface
	 * @param userKey
	 * @return
	 */
	protected String buildTuanUrl(String action,String apiInterface){
		StringBuilder builder = new StringBuilder("http://");
		builder.append(urlConfig.getHost()).append(urlConfig.getBusiness());
		builder.append(action).append("/").append(apiInterface);
		return builder.toString();
	}

	/**
	 * 
	 * @param action
	 * @param apiInterface
	 * @param userKey 
	 * @param config 
	 * @return
	 */
	protected  String buildPostUrl(String action,String apiInterface){
		StringBuilder builder = new StringBuilder();
		builder.append("http://").append(BaseConfig.MALL_WEB_HOST).append(BaseConfig.MALL_WEB_BISUNESS);
		
		builder.append("act=").append(action);
		builder.append("&op=").append(apiInterface);
		
		return builder.toString();
	}
	
	//拼装get参数
	protected String pingGetParams(StringBuilder url,Map<String, Object> params){
		// �Ƿ���ڲ���
			if(url.indexOf("?") < 0){
				url.append('?');
			}
			
			StringBuilder parampart = new StringBuilder();
			Object temp = null;
			
			try {
				for (String name : params.keySet()) {
					temp = params.get(name);
					if (temp == null) {
						continue;
					}
					
					parampart.append('&');
					parampart.append(URLEncoder.encode(String.valueOf(name), UTF_8));
					parampart.append('=');
					parampart.append(URLEncoder.encode(String.valueOf(temp), UTF_8));
				}
			} catch (UnsupportedEncodingException e) {
				throw new IllegalArgumentException(e);
			}
			
			url.append(parampart);
			
			return url.toString().replace("?&", "?");
	}
	
	/**
	 * public url
	 * @param address
	 * @param params
	 * @return
	 */
	protected String buildPublicUrl(String address,Map<String, Object> params){
		StringBuilder url = new StringBuilder();
		url.append(address);
		return pingGetParams(url, params);
	}
	
	/**
	 * geturl
	 * @param action
	 * @param apiInterface
	 * @param params
	 * @param userkey 
	 * @return
	 */
	protected String buildGetUrl(String action,String apiInterface,Map<String, Object> params,boolean userkey){
		StringBuilder url = new StringBuilder("http://");
		url.append(BaseConfig.PUBLIC_WEB_HOST).append(BaseConfig.PUBLIC_WEB_BISUNESS);
		url.append(action).append("/").append(apiInterface);
		
		if(userkey){
			params.put(BaseConfig.KEY_USER_KEY, getUserKey());
		}
		
		params.put(TOKEN, token);
		
		if (params == null || params.isEmpty()) {
			return url.toString();
		}

		return pingGetParams(url, params);
	}
	
	/**
	 * 服务器返回数据格式处理
	 * @param string
	 * @return
	 */
	protected String stringToJson(String string){
		StringBuffer buffer = new StringBuffer();
		 for (int i=0; i<string.length(); i++) {       
		     
		        char c = string.charAt(i);       
		        switch (c) {       
		        case '\"':       
		        	buffer.append("\\\"");       
		            break;       
		        case '\\':       
		        	buffer.append("\\\\");       
		            break;       
		        case '/':       
		        	buffer.append("\\/");       
		            break;       
		        case '\b':       
		        	buffer.append("\\b");       
		            break;       
		        case '\f':       
		        	buffer.append("\\f");       
		            break;       
		        case '\n':       
		        	buffer.append("\\n");       
		            break;       
		        case '\r':       
		        	buffer.append("\\r");       
		            break;       
		        case '\t':       
		        	buffer.append("\\t");       
		            break;       
		        case ' ':
		        	break;
		        default:       
		        	buffer.append(c);       
	        } 
		 }

		
		return buffer.toString();
	}
	
	protected  JSONObject createJSONObject(String json) {

		if (StringTools.isEmpty(json)) {
			return null;
		}
		
		try {
			return new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace() ;
			return null;
		}
	}
	
	protected  JSONArray createJSONArray(String json) {

		if (StringTools.isEmpty(json)) {
			return null;
		}
		
		try {
			return new JSONArray(json);
		} catch (JSONException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	protected  String getString(JSONObject json, String name) {

		try {
			String result = StringTools.toNotNullString(json.getString(name));
			if ("null".equals(result)) {
				result = null;
			}
			return result;
		} catch (JSONException e) {
			return null;
		}
	}

	protected  boolean getBoolean(JSONObject json, String name) {
		return NumberTools.toBoolean(getString(json, name));
	}

	protected  double getDouble(JSONObject json, String name) {
		return NumberTools.toDouble(getString(json, name));
	}
	
	protected  int getInt(JSONObject json, String name) {
		return NumberTools.toInt(getString(json, name));
	}

	protected  long getLong(JSONObject json, String name) {

		String source = getString(json, name);
		
		if (!StringTools.isEmpty(source)) {
			return Long.parseLong(source);
		}
		
		return -1;
	}

	protected  Date getDate(JSONObject json, String name) {
		return DateTools.toDate(getString(json, name));
	}

	protected  JSONArray getArrray(JSONObject json, String name) {

		String data = getString(json, name);
		if (StringTools.isEmpty(data)) {
			return null;
		}
		return createJSONArray(data);
	}
	
	public static final Map<String, Object> withEmptyParamterMap(){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		return map ;
	}
	
	/**
	 * 暂为静态
	 * @param action
	 * @param apiInterface
	 * @param userKey 
	 * @param config 是否使用配置中的host配置
	 * @return
	 */
	public static String buildUrl(String action,String apiInterface,String userKey,boolean config){
		StringBuilder builder = new StringBuilder();
		if(config){
			builder.append("http://").append(BaseConfig.MALL_WEB_HOST).append(BaseConfig.MALL_WEB_BISUNESS);
		}
		
		builder.append("act=").append(action);
		builder.append("&op=").append(apiInterface);
		if(!"".equals(userKey)){
			builder.append("&userkey=").append(userKey);
		}
		
		return builder.toString();
	}
	
	/**
	 * 对不需要传递userkey的处理
	 * @param action
	 * @param apiInterface
	 * @param config 是否使用配置中的host配置
	 * @return
	 */
	public static String buildUrl(String action,String apiInterface,boolean config){
		return	buildUrl(action, apiInterface, "",config);
	}
}
