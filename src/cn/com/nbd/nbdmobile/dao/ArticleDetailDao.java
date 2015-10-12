package cn.com.nbd.nbdmobile.dao;

import android.content.Context;

import org.hjh.db.BaseDao;

import java.util.List;

import cn.com.nbd.nbdmobile.base.DBManager;
import cn.com.nbd.nbdmobile.bean.ArticleDetail;


public final class ArticleDetailDao extends BaseDao<ArticleDetail> {

	private static ArticleDetailDao 	instance;
	private static DBManager dbManager;
	private static boolean 		isTrans;
	
	protected ArticleDetailDao(Context context) {
		super(context, dbManager.openSystemDatabase(), isTrans);
	}

	private synchronized static void init(Context context,boolean isTransaction){
		if(instance == null){
			String databaseName = "system";
			dbManager =	DBManager.getInstance(context, databaseName);
			isTrans = isTransaction;
			instance = new ArticleDetailDao(context);
		}
	}
	
	public static ArticleDetailDao getInstance(Context context,boolean isTransaction){
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
	public void insert(ArticleDetail mallCore){
		
		if(mallCore == null){
			return;
		}
		
		openCurrentDataBase();
		save(mallCore);
	}
	
	public void insertList(List<ArticleDetail> list){
		if(list == null){
			return;
		}
		
		for(ArticleDetail item : list){
			insert(item);
		}
	}
	
	/**
	 * 查询信息
	 * @return
	 */
	public List<ArticleDetail> queryAllArticleDetails(){
		
		openCurrentDataBase();
		List<ArticleDetail> list = queryPageData("select * from ArticleDetail", null);
		
		return list;
	}
	
	/**
	 * 删除所有信息
	 */
	public void deleteAllArticleDetails(){
		openCurrentDataBase();
		removeBySQL("delete from ArticleDetail");
	}
}
