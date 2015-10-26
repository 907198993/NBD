package cn.com.nbd.nbdmobile.api;

public final class BaseConstants {

		// http
		public static final int ERROR_HTTP_EXECUTE									= -1000 ;//连接超时
		public static final int ERROR_HTTP_PARAM										= ERROR_HTTP_EXECUTE + 1 ;
		
		// 输入
		public static final int ERROR_INPUT_PARAMETER							= ERROR_HTTP_PARAM + 1 ;
		
		//服务器无数据
		public static final int ERROR_NO_DATA									= ERROR_INPUT_PARAMETER +1 ;
		
		// api 错误
		public static final int ERROR_API_RESULT						     	=  ERROR_NO_DATA + 1;
		public static final int ERROR_API_PARSER_JSON							= ERROR_API_RESULT + 1 ;
		
		public static final int RESULT_LOGIN_SUCCESS			= ERROR_API_PARSER_JSON +1;
		public static final int RESULT_LOGIN_FAILED				= RESULT_LOGIN_SUCCESS +1;
		public static final int RESULT_LOGIN_FAILED_NULL				= RESULT_LOGIN_SUCCESS +99;
		
		public static final int RESULT_LOGINOUT_SUCCESS  = RESULT_LOGIN_FAILED + 1;
		public static final int RESULT_LOGINOUT_FAILED  		= RESULT_LOGINOUT_SUCCESS + 1;
		
		public static final int RESULT_REGISTER_SUCCESS  = RESULT_LOGINOUT_FAILED + 1;
		public static final int RESULT_REGISTER_FAILED  		= RESULT_LOGINOUT_SUCCESS + 1;
		public static final int RESULT_REGISTER_FAILED_NULL  		= RESULT_LOGINOUT_SUCCESS + 55;
		



		public static final String ACTION_SHARE_EVEVT = "action_share_event";
		
		public static final String ACTION_BROAD_CAST_RECEIVER = "action_broad_cast_receiver";
}
