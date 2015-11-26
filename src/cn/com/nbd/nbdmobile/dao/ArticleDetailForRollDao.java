package cn.com.nbd.nbdmobile.dao;

import android.content.Context;

import org.hjh.db.BaseDao;

import java.util.List;

import cn.com.nbd.nbdmobile.base.DBManager;
import cn.com.nbd.nbdmobile.bean.ArticleDetailForRoll;


public final class ArticleDetailForRollDao extends BaseDao<ArticleDetailForRoll> {

	private static ArticleDetailForRollDao instance;
	private static DBManager dbManager;
	private static boolean 		isTrans;

	protected ArticleDetailForRollDao(Context context) {
		super(context, dbManager.openSystemDatabase(), isTrans);
	}

	private synchronized static void init(Context context,boolean isTransaction){
		if(instance == null){
			String databaseName = "system";
			dbManager =	DBManager.getInstance(context, databaseName);
			isTrans = isTransaction;
			instance = new ArticleDetailForRollDao(context);
		}
	}
	
	public static ArticleDetailForRollDao getInstance(Context context,boolean isTransaction){
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
	public void insert(ArticleDetailForRoll mallCore){
		
		if(mallCore == null){
			return;
		}
		
		openCurrentDataBase();
		save(mallCore);
	}
	
	public void insertList(List<ArticleDetailForRoll> list){
		if(list == null){
			return;
		}
		
		for(ArticleDetailForRoll item : list){
			insert(item);
		}
	}
	
	/**
	 * 查询信息
	 * @return
	 */
	public List<ArticleDetailForRoll> queryAllArticleDetailForRoll(){
		
		openCurrentDataBase();
		List<ArticleDetailForRoll> list = queryPageData("select * from ArticleDetailForRoll", null);
		
		return list;
	}
	
	/**
	 * 删除所有信息
	 */
	public void deleteAllArticleDetailForRoll(){
		openCurrentDataBase();
		removeBySQL("delete from ArticleDetailForRoll");
	}
}
