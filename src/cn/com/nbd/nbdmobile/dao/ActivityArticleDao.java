package cn.com.nbd.nbdmobile.dao;

import android.content.Context;

import org.hjh.db.BaseDao;

import java.util.List;

import cn.com.nbd.nbdmobile.base.DBManager;
import cn.com.nbd.nbdmobile.bean.ActivityArticle;


public final class ActivityArticleDao extends BaseDao<ActivityArticle> {

	private static ActivityArticleDao instance;
	private static DBManager dbManager;
	private static boolean 		isTrans;

	protected ActivityArticleDao(Context context) {
		super(context, dbManager.openPrivateDatabase(), isTrans);
	}

	private synchronized static void init(Context context,boolean isTransaction){
		if(instance == null){
			String databaseName = "xyp";
			dbManager =	DBManager.getInstance(context, databaseName);
			isTrans = isTransaction;
			instance = new ActivityArticleDao(context);
		}
	}
	
	public static ActivityArticleDao getInstance(Context context,boolean isTransaction){
		if(instance == null){
			init(context,isTransaction);
		}
		
		return instance;
	}
	
	/**
	 * 使用前需要打开数据库
	 */
	public  void openCurrentDataBase () {
		openDataBase(dbManager.openPrivateDatabase(), isTrans);
	}
	
	/**
	 * 插入数据
	 * @param
	 */
	public void insert(ActivityArticle ActivityArticle){
		
		if(ActivityArticle == null){
			return;
		}
		
		openCurrentDataBase();
		save(ActivityArticle);
	}
	
	public void insertList(List<ActivityArticle> list){
		if(list == null){
			return;
		}
		
		for(ActivityArticle item : list){
			insert(item);
		}
	}
	
	/**
	 * 查询信息
	 * @return
	 */
	public List<ActivityArticle> queryAllActivityArticle(){
		
		openCurrentDataBase();
		List<ActivityArticle> list = queryPageData("select * from ActivityArticle", null);
		
		return list;
	}
	
	/**
	 * 删除所有信息
	 */
	public void deleteAllActivityArticle(){
		openCurrentDataBase();
		removeBySQL("delete from ActivityArticle");
	}
}
