package cn.com.nbd.nbdmobile.api;

/**
 *  数据返回字段定义类型
 * @author Dell
 *
 */
public final class AppConstants {
	
	public static final int RESULT_MODIFY_USER_PHOTO_SUCCESS							= -501;
	public static final int RESULT_MODIFY_USER_PHOTO_FAILED								= RESULT_MODIFY_USER_PHOTO_SUCCESS +1;
	
	public static final int RESULT_QUERY_STOCK_CONTENT_SUCCESS							= RESULT_MODIFY_USER_PHOTO_FAILED +1;
	public static final int RESULT_QUERY_STOCK_CONTENT_FAILED								= RESULT_QUERY_STOCK_CONTENT_SUCCESS +1;
	//文章
	public static final int RESULT_QUERY_ARTICLE_SUCCESS							= RESULT_QUERY_STOCK_CONTENT_FAILED +1;
	public static final int RESULT_QUERY_ARTICLE_FAILED								= RESULT_QUERY_ARTICLE_SUCCESS +1;

}