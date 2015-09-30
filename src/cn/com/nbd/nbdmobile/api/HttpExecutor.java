package cn.com.nbd.nbdmobile.api;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;
import cn.com.nbd.nbdmobile.bean.ResultObject;

import com.google.gson.Gson;
/**
 *  http
 * @author Dell
 *
 */
public final class HttpExecutor {

	private final static int CONNECTION_TIMEOUT = 6 * 1000;
	private final static int READ_TIMEOUT= 8 * 1000;
	protected  String openid= "" ;
	private String TAG = HttpExecutor.class.getSimpleName();
	private  Gson gson = new Gson();
	
	public boolean doPost(String url,Map<String, Object> paramter,ResultObject result){
		return doPost(url,paramter,null,result);
	}
	
	public boolean doPost(String url,Map<String, Object> paramter, Map<String, File> files,ResultObject result) {
		return doPost(url,paramter,files,READ_TIMEOUT,result) ;
	}
	
	public boolean doPost(String url,Map<String, Object> paramter, Map<String, File> files,int readTimeout,ResultObject result) {
		
		MultipartEntity multipartEntity = new MultipartEntity();
		
		try {
			
			if (paramter != null) {
				for (String name : paramter.keySet()) {
					multipartEntity.addPart(name, new StringBody(String.valueOf(paramter.get(name)), Charset.forName(HTTP.UTF_8)));
				}
			}
			
			if (files != null) {
				for (String name : files.keySet()) {
					multipartEntity.addPart(name, new FileBody(files.get(name)));
				}
			}
			
//			if(isAddToken()){
//				multipartEntity.addPart(getToken(),new StringBody(getTokenValue(),Charset.forName(HTTP.UTF_8) ));
//			}
			
		} catch (Exception e) {
			result.setCode(BaseConstants.ERROR_HTTP_PARAM) ;
			return false ;
		}
		
		HttpPost request = new HttpPost(url);
		request.setEntity(multipartEntity);

		// handle response 
		boolean noerror = doExecute(request, readTimeout, result);
		
		return noerror;
	}
	
	public boolean postJson(String url,Map<String, Object> paramter,ResultObject result,JSONObject jsonObject){
		
		HttpPost request = new HttpPost(url);
		/////////////////
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        
		// �ύ�Ĳ����б�
		if(paramter != null){
			
			Set<String> keySet = paramter.keySet();  
			
			for(String key : keySet) {  
				nvps.add(new BasicNameValuePair(key, String.valueOf(paramter.get(key))));  
			}  
		}
		
		try {
			StringEntity entity = new StringEntity(jsonObject.toString());
			request.setEntity(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return doExecute(request, READ_TIMEOUT, result);
	}
	
	public boolean doGet(String url,ResultObject result) {
		
		// execute http
		HttpGet httpGet = new HttpGet(url);
		
		httpGet.addHeader("charset", HTTP.UTF_8);
		boolean noerror= doExecute(httpGet,READ_TIMEOUT,result);
		return noerror;
	} 
	public boolean doGet(String url,String key,ResultObject result) {
		
		// execute http
		HttpGet httpGet = new HttpGet(url);
		if(key!=null){
			httpGet.addHeader("apikey", "f2307c5842859ec9e3b20e0a2852075c");
		}
		httpGet.addHeader("charset", HTTP.UTF_8);
		boolean noerror= doExecute(httpGet,READ_TIMEOUT,result);
		return noerror;
	} 
	
	private HttpParams buildHttpParams(int readTimeout){
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, readTimeout <= 0 ? READ_TIMEOUT : readTimeout);
		return httpParams ;
	}
	
	/** execute http request*/
	private boolean doExecute(HttpUriRequest httpMethod,int readTimeout,ResultObject result) {
		
		try {
			
			HttpParams httpParams = buildHttpParams(readTimeout);
			HttpClient httpClient = new DefaultHttpClient(httpParams);
//			httpMethod.setHeader("token", "ad13a2a07c");
			long enter = System.currentTimeMillis() ;
			
			// execute request
			HttpContext context 	= new BasicHttpContext();
			HttpResponse response 	= httpClient.execute(httpMethod, context);
			
//			Logger.d(TAG, "hjh ==> ��������Ӧʱ��=" + (System.currentTimeMillis()- enter));
			
			int stausCode = response.getStatusLine().getStatusCode() ;
			
//			Logger.d(TAG, "hjh ==> 1 >>statusCode= " + stausCode);
			
			if (stausCode != HttpStatus.SC_OK) {
				
				result.setCode(stausCode) ;
				result.setContent(EntityUtils.toString(response.getEntity())) ;
				
				Log.v(TAG, "hjh ==> 2 >>content= " + result.getContent());
    			Log.v(TAG, "hjh ==> ��������Ӧ�����ܹ����=" + (System.currentTimeMillis()- enter));
				
				return false ;
			}
			
			// get response
			HttpEntity entity 			= response.getEntity();
			String responseBody 		= EntityUtils.toString(entity);
			
//			Logger.d(TAG, "hjh ==> 3 >>responseBody= " + responseBody);
			
			// set body to content
			result.setContent(responseBody) ;
			
			return true ;
		} catch (Exception e) {
			e.printStackTrace() ;
			result.setCode(BaseConstants.ERROR_HTTP_EXECUTE) ;
			result.setContent(e.toString()); // for debug
			
		//Logger.d(TAG, "hjh ==> 3 >>responseBody= " + result.getContent());
			return false ;
		}
	}
	/**
	 * 以流的方式提交
	 * @param path  url
	 * @param buffer 字节流
	 * @param result
	 * @return
	 */
	public boolean executeHttp(String path,byte[] buffer,ResultObject result){
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(buffer);
			os.flush();
			os.close();
			int code = conn.getResponseCode();
			
			if(code == HttpStatus.SC_OK){
				////////////////
				InputStream is = conn.getInputStream();
				result.setContent(readString(is));
				result.setCode(code);
				
				return true;
			}else{
				result.setCode(code);
				result.setContent(readString(conn.getInputStream()));
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(BaseConstants.ERROR_HTTP_EXECUTE) ;
			result.setContent(e.toString()); // for debug
			return false;
		}
	}
	
	public boolean executeHttp(String path,String paramKey,String paramValue,byte[] buffer,ResultObject result){
		
		try {
			URL 	url = new URL(path+"?"+paramKey+"="+URLEncoder.encode(paramValue, "utf-8"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(buffer);
			os.flush();
			os.close();
			int code = conn.getResponseCode();
			
			if(code == HttpStatus.SC_OK){
				////////////////
				InputStream is = conn.getInputStream();
				result.setContent(readString(is));
				result.setCode(code);
				
				return true;
				
			}else{
				result.setCode(code);
				result.setContent(readString(conn.getInputStream()));
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(BaseConstants.ERROR_HTTP_EXECUTE) ;
			result.setContent(e.toString()); // for debug
			return false;
		} 
	
	}
	
	public static byte[] readBytes(InputStream is){
		try {
			byte[] buffer = new byte[1024];
			int len = -1 ;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while((len = is.read(buffer)) != -1){
				baos.write(buffer, 0, len);
			}
			baos.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	public static String readString(InputStream is){
		return new String(readBytes(is));
	}

	
}
