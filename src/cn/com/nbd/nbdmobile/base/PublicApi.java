//package cn.com.nbd.nbdmobile.base;
//
//import java.util.Map;
//
//import cn.com.nbd.nbdmobile.api.BaseApi;
//import cn.com.nbd.nbdmobile.api.BaseConstants;
//import cn.com.nbd.nbdmobile.bean.ResponseJson;
//import cn.com.nbd.nbdmobile.bean.ResultObject;
//import cn.com.nbd.nbdmobile.bean.StutasInfo;
//import cn.com.nbd.nbdmobile.config.AppPresences;
//import cn.com.nbd.nbdmobile.config.BaseConfig;
//
//
///**
// * app公用
// * @author hjh
// * 2015-1-22����2:19:11
// *
// */
//public final class PublicApi extends BaseApi {
//
//	private static PublicApi instance;
//
//	private PublicApi(){
//
//	}
//
//	private synchronized static void syncInit(){
//		if(instance == null){
//			instance = new PublicApi();
//		}
//	}
//
//	public static PublicApi getInstance(){
//		if(instance == null){
//			syncInit();
//		}
//
//		return instance;
//	}
//
//	/**
//	 * 登陆
//	 * @param userName
//	 * @param pwd
//	 * @param result
//	 */
//	public boolean loginIn(String userName,String pwd,ResultObject result){
//		String url = buildPostUrl(cn.com.nbd.nbdmobile.base.ApiBaseAction.LOGIN.getName(), ApiBaseInterface.INDEX.getName());
//		Map<String, Object> paramter = withEmptyParamterMap();
//		paramter.put("client", "android");
//		paramter.put("username", userName);
//		paramter.put("password", pwd);
//		StutasInfo stutasInfo = new StutasInfo();
//		boolean noerror =  httpExecutor.doPost(url, paramter, result);
//		if(noerror){
//			try {
//				ResponseJson<LoginResult> response = ResponseJson.fromJson(result.getContent(), LoginResult.class);
//				stutasInfo.setStutas(response.getStatus());
//				if(response.getStatus() != 1){
//					result.setCode(BaseConstants.ERROR_INPUT_PARAMETER);
//					result.setError(response.getInfo());
//					return false;
//				}else{
//					User user = response.getData().getMember_info();
//					AppPresences.getInstance().putLong(BaseConfig.KEY_LAST_LOGIN_TIME, user.getMember_old_login_time());
//					AppPresences.getInstance().putString(BaseConfig.KEY_USER_KEY, response.getData().getUserkey());
//					AppPresences.getInstance().putInt(BaseConfig.KEY_USER_SEX, user.getMember_sex());
//					AppPresences.getInstance().putString(BaseConfig.KEY_USER_BIRTHDAY, user.getMember_birthday());
//					AppPresences.getInstance().putString(BaseConfig.KEY_USER_QQ, user.getMember_qq());
//					AppPresences.getInstance().putString(BaseConfig.KEY_USER_WW, user.getMember_ww());
//					AppPresences.getInstance().putLong(BaseConfig.KEY_USER_REGISTER_TIME, user.getMember_time());
//					AppPresences.getInstance().putString(BaseConfig.KEY_USER_ICON, user.getPhoto());
//					AppPresences.getInstance().putString(BaseConfig.KEY_LOGIN_TRUE_NAME, user.getMember_truename());
//					return true;
//				}
//			} catch (Exception e) {
//				ResponseJson<Object> response = ResponseJson.fromJson(result.getContent(),Object.class);
//				stutasInfo.setStutas(response.getStatus());
//				result.setCode(BaseConstants.ERROR_INPUT_PARAMETER);
//				result.setError(response.getInfo());
//				if(response.getStatus() != 1){
//					return false;
//				}
//				return false;
//			}
//
//
//		}else{
//			result.setCode(BaseConstants.ERROR_HTTP_EXECUTE);
//			return false;
//		}
//
//	}
//
//	/**
//	 * 注册
//	 * @param userName
//	 * @param pwd
//	 * @param result
//	 */
//	public boolean register(String userName,String email,String pwd,String pwd2,ResultObject result){
//		String url = buildPostUrl(com.dpt.base.ApiBaseAction.LOGIN.getName(), ApiBaseInterface.REGISTER.getName());
//		Map<String, Object> paramter = withEmptyParamterMap();
//		paramter.put("client", "android");
//		paramter.put("username", userName);
//		paramter.put("email", email);
//		paramter.put("password_confirm", pwd2);
//		paramter.put("password", pwd);
//		StutasInfo stutasInfo = new StutasInfo();
//		boolean noerror =  httpExecutor.doPost(url, paramter, result);
//		if(noerror){
//			try {
//				ResponseJson<LoginResult> response = ResponseJson.fromJson(result.getContent(),LoginResult.class);
//				stutasInfo.setStutas(response.getStatus());
//				if(response.getStatus() != 1){
//					result.setCode(BaseConstants.ERROR_INPUT_PARAMETER);
//					result.setError(response.getInfo());
//					return false;
//				}else{
//					return true;
//				}
//			} catch (Exception e) {
//				ResponseJson<Object> response = ResponseJson.fromJson(result.getContent(),Object.class);
//				stutasInfo.setStutas(response.getStatus());
//				result.setCode(BaseConstants.ERROR_INPUT_PARAMETER);
//				result.setError(response.getInfo());
//				if(response.getStatus() != 1){
//					return false;
//				}else{
//					return false;
//				}
//			}
//
//		}else{
//			result.setCode(BaseConstants.ERROR_HTTP_EXECUTE);
//			return false;
//		}
//
//	}
//	/**
//	 * 退出
//	 * @param result
//	 * @return
//	 */
//	public boolean loginOut(ResultObject result){
//		String url = buildPostUrl(ApiBaseAction.LOGOUT.getName(),ApiBaseInterface.INDEX.getName());
//		Map<String, Object> paramter = withEmptyParamterMap();
//		paramter.put("client", "android");
//		paramter.put(BaseConfig.KEY_USER_KEY, getUserKey());
//		paramter.put("username",AppPresences.getInstance().getString(BaseConfig.KEY_LOGIN_NAME));
//		boolean noerror =  httpExecutor.doPost(url, paramter, result);
//		if(noerror){
//			try {
//				ResponseJson<Integer> response = ResponseJson.fromJson(result.getContent(), Integer.class);
//				if(response.getStatus() == 1){
//					return true;
//				}else {
//					result.setCode(BaseConstants.ERROR_INPUT_PARAMETER);
//					result.setError(response.getInfo());
//					return false;
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				result.setCode(BaseConstants.ERROR_API_PARSER_JSON);
//				return false;
//			}
//
//		}else {
//			result.setCode(BaseConstants.ERROR_HTTP_EXECUTE);
//			return false;
//		}
//
//	}
//}
