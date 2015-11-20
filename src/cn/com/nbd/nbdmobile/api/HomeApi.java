package cn.com.nbd.nbdmobile.api;

import java.lang.reflect.Type;
import java.util.Map;

import cn.com.nbd.nbdmobile.bean.ResponseListJson;
import cn.com.nbd.nbdmobile.bean.ResultObject;
import cn.com.nbd.nbdmobile.tool.BaseTools;


public final class HomeApi extends BaseApi {

	private static HomeApi instance;
	
	private HomeApi(){
		super();
	}
	

	public static HomeApi getInstance(){
		if(instance == null){
			synchronized (HomeComponent.class) {//Double-Check Locking
				if (instance == null) {
					instance= new HomeApi();
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * 获取数据对返回数据的解析，返回对象
	 * @param action
	 * @param method
	 * @param paramter
	 * @param clazz 对象的字节码
	 * @param result
	 * @return
	 */
//	public Object queryData(String action,String method,Map<String, Object> paramter,Class clazz,ResultObject result){
//		String url = buildTuanUrl(action,method);
//		boolean noerrer = httpExecutor.doPost(url, paramter, result);
//		if(noerrer){
//			try {
//				ResponseJson<Object> response = (ResponseJson<Object>) ResponseJson.fromJson(result.getContent(),clazz);
//				if(response.getStatus() != 1){
//					result.setError(response.getInfo());
//					result.setCode(BaseConstants.ERROR_INPUT_PARAMETER);//一般为参数错误
//					return null;
//				}else{
//					return response.getData();//code 为-1
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				result.setCode(BaseConstants.ERROR_API_PARSER_JSON);//解析错误
//				return null;
//			}
//
//		}else{
//			result.setCode(BaseConstants.ERROR_HTTP_EXECUTE);
//			return null;//连接超时
//		}
//	}
	
	/**
	 * 返回list
	 * @param action
	 * @param method
	 * @param paramter
	 * @param clazz
	 * @param result
	 * @return
	 */
	public Object queryList(String action,String method,Map<String, Object> paramter,Class clazz,ResultObject result){
		String url = buildTuanUrl(action,method);
		boolean noerrer = httpExecutor.doPost(url, paramter, result);
		if(noerrer){
			try {
				ResponseListJson<Object> response = ResponseListJson.fromJson(result.getContent(),clazz);
				if(response.getStatus() != 1){
					result.setError(response.getInfo());
					result.setCode(BaseConstants.ERROR_INPUT_PARAMETER);//一般为参数错误
					return null;
				}else{
					return response.getData();//code 为-1
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setCode(BaseConstants.ERROR_API_PARSER_JSON);//解析错误
				return null;
			}
			
		}else{
			result.setCode(BaseConstants.ERROR_HTTP_EXECUTE);
			return null;//连接超时
		}
	}
	
	   /**
	    *  滚动栏点击增加阅读数
	    * http://api.nbd.com.cn/1/columns/mobile_click_count?ids=935908&app_key=f4af4864997a00ddff7e1765e643f9ec&client_key=iPhone
	    */
	public Object addReadNumber(long  ids ,ResultObject result){
		String url = buildTuanUrl(ApiAction.ADD_READ_NUM.getValue(), ApiInterface.COLUMNS_MOBILE_CILCK.getValue());
		Map<String, Object> paramter = withEmptyParamterMap();
		paramter.put("ids", ids);
		paramter.put("app_key", "f4af4864997a00ddff7e1765e643f9ec");
		paramter.put("client_key", "android");
		boolean noerrer = httpExecutor.doPost(url, paramter, result);
		return noerrer;
	   
	}

	/**
	 *   查询报纸
	 * @param result
	 * @return
	 */
	public Object queryNewsPaperList(ResultObject result,Type type,Class clazz){
		String url = buildTuanUrl(ApiAction.NEWS_PAPER.getValue(), ApiInterface.ARTICLE_JSON.getValue());
		Map<String, Object> paramter = withEmptyParamterMap();
		paramter.put("app_key", "f4af4864997a00ddff7e1765e643f9ec");
		paramter.put("client_key", "android");
		String urls = buildPublicUrl(url,paramter);
//		boolean noerrer = httpExecutor.doGet(urls, result);
//		try {
//			String content=new String(BaseTools.decompress(result.getContent()),"UTF-8");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		try {
			byte[] data = BaseTools.loadOriginalDataFromURL(urls);
			String content=new String( BaseTools.decompress(data),"UTF-8");

			return  gson.fromJson(content,clazz == null ? type : clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return   null;

	}

	public Object queryArticle(int page,int count ,ResultObject result,Type type,Class clazz){
		//String url = "http://api.nbd.com.cn/v1/columns/3/articles.json?client_type=1&app_key=f4af4864997a00ddff7e1765e643f9ec&page="+page+"&count="+count;
		String url="http://api.nbd.com.cn/2/columns/3/articles.json?client_type=1&app_key=f4af4864997a00ddff7e1765e643f9ec&count=10&page=0";
		Map<String, Object> paramter = withEmptyParamterMap();
		boolean noerrer = httpExecutor.doGet(url, result);
		if(noerrer){
			try {
				return gson.fromJson(result.getContent(),clazz == null ? type : clazz);
			} catch (Exception e) {
				e.printStackTrace();
				result.setCode(BaseConstants.ERROR_API_PARSER_JSON);//解析错误
				return null;
			}
		}else{
			result.setCode(BaseConstants.ERROR_HTTP_EXECUTE);
			return null;
		}

	}
	public Object queryNewsActivityList(int page,int count ,ResultObject result,Type type,Class clazz){
		//String url=  BA "http://api.nbd.com.cn/2/columns/3/articles.json?client_type=1&app_key=f4af4864997a00ddff7e1765e643f9ec&count=10&page=0";
		String url= "http://api.nbd.com.cn/3/columns/183/articles";
		Map<String, Object> paramter = withEmptyParamterMap();
		boolean noerrer = httpExecutor.doGet(url, result);
		if(noerrer){
			try {
				return gson.fromJson(result.getContent(),clazz == null ? type : clazz);
			} catch (Exception e) {
				e.printStackTrace();
				result.setCode(BaseConstants.ERROR_API_PARSER_JSON);//解析错误
				return null;
			}
		}else{
			result.setCode(BaseConstants.ERROR_HTTP_EXECUTE);
			return null;
		}

	}
	//资讯头图轮播广告数据
	public Object queryAdInfo(ResultObject result,Type type,Class clazz){
		//String url = "http://apis.baidu.com/apistore/stockservice/hkstock?stockid=00168&list=1";
		String url="http://api.nbd.com.cn/2/columns/3/articles.json?client_type=1&app_key=f4af4864997a00ddff7e1765e643f9ec&count=10&page=0";
	//	String url = "http://api.nbd.com.cn/v1/columns/2/articles.json?client_type=1&app_key=f4af4864997a00ddff7e1765e643f9ec&page=1&count=3";
		boolean noerrer =httpExecutor.doGet(url, result);
		if(noerrer){
			try {
				return gson.fromJson(result.getContent(),clazz == null ? type : clazz);
			} catch (Exception e) {
				e.printStackTrace();
				result.setCode(BaseConstants.ERROR_API_PARSER_JSON);//解析错误
				return null;
			}
		}else{
			result.setCode(BaseConstants.ERROR_HTTP_EXECUTE);
			return null;
		}
	}
	
	
}
