package cn.com.nbd.nbdmobile.config;

public final class BaseConfig {

		public static int PRIVATE_DB_VERSION	= 1;//私有数据库
		public static int SYSTEM_DB_VERSION 	= 1;//公用数据库
		public static int SCREEN_WIDTH			= 480;
		public static int SCREEN_HEIGHT			= 800;
		
		
		public static final String PATH_AUDIO 	 	     = "audio/";
		public static final String PATH_IMAGE 	 		 = "image/";
		public static final String PATH_TEMP 	  		 = "nbd/";
		public static final String PATH_ERROR 	 		 = "error/";
		public static final String PATH_VERSION	     = "version/";
		public static final String PATH_LOG			 	 = "cache/";
		
		public static String PUBLIC_WEB_HOST					= "www.zhsq360.com";//www.315xfzsc.net//192.168.254.37
		public static String PUBLIC_WEB_BISUNESS				= "/index.php/Webserver/";// /index.php/Webserver/,/mall/Webserver/index.php
		
		public static String NEWS_ACTIVITY_HOST			 			 = "api.nbd.com.cn/3/columns/183/articles";
		public static String NEWS_ACTIVITY_WEB_BISUNESS     			 = "/mall/Webserver/index.php?";
		
		public static String TUAN_WEB_HOST   					 = "192.168.254.37";//"192.168.9.149";
		public static int WEB_PORT	              		     			 = 9997;
		
		public static String TUAN_WEB_BISUNESS				 = "/index.php/Webserver/";
		
		public static String KEY_LOGIN_NAME						 = "key_login_name";
		public static String KEY_LOGIN_PWD							 = "key_login_pwd";
		public static String KEY_LOGIN_TRUE_NAME			 = "key_login_true_name";
		public static String KEY_USER_KEY							 = "userkey";
		public static String KEY_LAST_LOGIN_TIME				 = "key_last_login_time";
		public static String KEY_USER_SEX							 = "key_user_sex";
		public static String KEY_USER_BIRTHDAY				 	 = "key_user_birthday";
		public static String KEY_USER_QQ				 			     = "key_user_qq";
		public static String KEY_USER_WW				 			 = "key_user_ww";
		public static String KEY_USER_REGISTER_TIME		 = "key_user_register_time";
		public static String KEY_USER_ICON							 = "key_user_icon";
		public static String KEY_LAST_LOGIN_ACCOUNT		 = "key_last_login_account";
		public static String KEY_LAST_LOGIN_PWD				 = "key_last_login_pwd";
		
		
		
		public static String ACTION_RECEIVE_WIFI				 = "action_receive_wifi";
		public static String IMAGE_HOST_PRE						 = "http://www.zhsq360.com";
		
		public static int  TAB_HEIGHT = 20;
		public static int  STATUS_BAR_HEIGHT = 20;
}
