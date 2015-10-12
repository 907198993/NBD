package cn.com.nbd.nbdmobile.dao;

import android.content.Context;

import org.hjh.db.BaseDao;

import java.util.List;

import cn.com.nbd.nbdmobile.base.DBManager;
import cn.com.nbd.nbdmobile.bean.ArticleDetailForQuick;


public final class ArticleDetailForQuickDao extends BaseDao<ArticleDetailForQuick> {

	private static ArticleDetailForQuickDao instance;
	private static DBManager dbManager;
	private static boolean 		isTrans;

	protected ArticleDetailForQuickDao(Context context) {
		super(context, dbManager.openSystemDatabase(), isTrans);
	}

	private synchronized static void init(Context context,boolean isTransaction){
		if(instance == null){
			String databaseName = "system";
			dbManager =	DBManager.getInstance(context, databaseName);
			isTrans = isTransaction;
			instance = new ArticleDetailForQuickDao(context);
		}
	}
	
	public static ArticleDetailForQuickDao getInstance(Context context,boolean isTransaction){
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
	public void insert(ArticleDetailForQuick mallCore){
		
		if(mallCore == null){
			return;
		}
		
		openCurrentDataBase();
		save(mallCore);
	}
	
	public void insertList(List<ArticleDetailForQuick> list){
		if(list == null){
			return;
		}
		
		for(ArticleDetailForQuick item : list){
			insert(item);
		}
	}
	
	/**
	 * 查询信息
	 * @return
	 */
	public List<ArticleDetailForQuick> queryAllArticleDetailForQuick(){
		
		openCurrentDataBase();
		List<ArticleDetailForQuick> list = queryPageData("select * from ArticleDetailForQuick", null);
		
		return list;
	}
	
	/**
	 * 删除所有信息
	 */
	public void deleteAllArticleDetailForQuick(){
		openCurrentDataBase();
		removeBySQL("delete from ArticleDetailForQuick");
	}
}
