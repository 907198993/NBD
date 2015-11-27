package cn.com.nbd.nbdmobile.dao;

import android.content.Context;

import org.hjh.db.BaseDao;

import java.util.List;

import cn.com.nbd.nbdmobile.base.DBManager;
import cn.com.nbd.nbdmobile.bean.ADArticle;


public final class ADArticleDao extends BaseDao<ADArticle> {

	private static ADArticleDao instance;
	private static DBManager dbManager;
	private static boolean 		isTrans;

	protected ADArticleDao(Context context) {
		super(context, dbManager.openSystemDatabase(), isTrans);
	}

	private synchronized static void init(Context context,boolean isTransaction){
		if(instance == null){
			String databaseName = "ADArticle";
			dbManager =	DBManager.getInstance(context, databaseName);
			isTrans = isTransaction;
			instance = new ADArticleDao(context);
		}
	}
	
	public static ADArticleDao getInstance(Context context,boolean isTransaction){
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
	public void insert(ADArticle mallCore){
		
		if(mallCore == null){
			return;
		}
		
		openCurrentDataBase();
		save(mallCore);
	}
	
	public void insertList(List<ADArticle> list){
		if(list == null){
			return;
		}
		
		for(ADArticle item : list){
			insert(item);
		}
	}
	
	/**
	 * 查询信息
	 * @return
	 */
	public List<ADArticle> queryAllADArticle(){
		
		openCurrentDataBase();
		List<ADArticle> list = queryPageData("select * from ADArticle", null);
		
		return list;
	}
	
	/**
	 * 删除所有信息
	 */
	public void deleteAllADArticle(){
		openCurrentDataBase();
		removeBySQL("delete from ADArticle");
	}
}
