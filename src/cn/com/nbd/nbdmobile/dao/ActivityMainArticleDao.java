package cn.com.nbd.nbdmobile.dao;

import android.content.Context;

import org.hjh.db.BaseDao;

import java.util.List;

import cn.com.nbd.nbdmobile.base.DBManager;
import cn.com.nbd.nbdmobile.bean.ActivityMainArticle;

/**
 * 活动首页
 */
public final class ActivityMainArticleDao extends BaseDao<ActivityMainArticle> {

	private static ActivityMainArticleDao instance;
	private static DBManager dbManager;
	private static boolean 		isTrans;

	protected ActivityMainArticleDao(Context context) {
		super(context, dbManager.openSystemDatabase(), isTrans);
	}

	private synchronized static void init(Context context,boolean isTransaction){
		if(instance == null){
			String databaseName = "system";
			dbManager =	DBManager.getInstance(context, databaseName);
			isTrans = isTransaction;
			instance = new ActivityMainArticleDao(context);
		}
	}
	
	public static ActivityMainArticleDao getInstance(Context context,boolean isTransaction){
		if(instance == null){
			init(context,isTransaction);
		}
		
		return instance;
	}
	
	/**
	 * 使用前需要打开数据库
	 */
	public  void openCurrentDataBase () {
		openDataBase(dbManager.openSystemDatabase(), isTrans);
	}
	
	/**
	 * 插入数据
	 * @param
	 */
	public void insert(ActivityMainArticle ActivityMainArticle){
		
		if(ActivityMainArticle == null){
			return;
		}
		
		openCurrentDataBase();
		save(ActivityMainArticle);
	}
	
	public void insertList(List<ActivityMainArticle> list){
		if(list == null){
			return;
		}
		
		for(ActivityMainArticle item : list){
			insert(item);
		}
	}
	
	/**
	 * 查询信息
	 * @return
	 */
	public List<ActivityMainArticle> queryAllActivityMainArticle(){
		
		openCurrentDataBase();
		List<ActivityMainArticle> list = queryPageData("select * from ActivityMainArticle", null);
		
		return list;
	}
	
	/**
	 * 删除所有信息
	 */


	public void deleteRepeatActivityMainArticle(){
		openCurrentDataBase();

		removeBySQL("delete from ActivityMainArticle where rowid not in(select max(rowid) from ActivityMainArticle group by id)");
	}

}
