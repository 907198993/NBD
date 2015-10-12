package cn.com.nbd.nbdmobile.api;

import java.lang.reflect.Type;
import java.util.Map;

import cn.com.nbd.nbdmobile.bean.ResponseListJson;
import cn.com.nbd.nbdmobile.bean.ResultObject;


public final class HomeApi extends BaseApi {

	private static HomeApi instance;
	
	private HomeApi(){
		super();
	}
	
	private static synchronized void init(){
		if(instance == null){
			instance = new HomeApi();
		}
	}
	
	public static HomeApi getInstance(){
		if(instance == null){
			init();
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
	
	public Object queryArticle(int page,int count ,ResultObject result,Type type,Class clazz){
		String url = "http://api.nbd.com.cn/v1/columns/3/articles.json?client_type=1&app_key=f4af4864997a00ddff7e1765e643f9ec&page="+page+"&count="+count;
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
//		if(noerrer){
//			try {
//			ResponseJson<Article> response = ResponseJson.fromJson(result.getContent(), Article.class);
//			if (response.getError()!= "") {
//				result.setCode(BaseConstants.ERROR_INPUT_PARAMETER);
//				result.setError(response.getError());
//				return null;
//			}
//
//			return response.getContent();
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.setCode(BaseConstants.ERROR_API_PARSER_JSON);
//			return null;
//		}
//
//	}else{
//		result.setCode(BaseConstants.ERROR_HTTP_EXECUTE);
//		return null;
//	}
	   
	}
	
	//行情数据
	public Object queryStockInfo(ResultObject result,Type type,Class clazz){
		String url = "http://apis.baidu.com/apistore/stockservice/hkstock?stockid=00168&list=1";
		boolean noerrer =httpExecutor.doGet(url, "f4af4864997a00ddff7e1765e643f9ec", result);
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
