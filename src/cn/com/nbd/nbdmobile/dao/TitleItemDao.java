package cn.com.nbd.nbdmobile.dao;

import android.content.Context;

import org.hjh.db.BaseDao;

import java.util.List;

import cn.com.nbd.nbdmobile.base.DBManager;
import cn.com.nbd.nbdmobile.bean.TitleItem;

/**
 *  栏目
 */
public final class TitleItemDao extends BaseDao<TitleItem> {

	private static TitleItemDao instance;
	private static DBManager dbManager;
	private static boolean 		isTrans;

	protected TitleItemDao(Context context) {
		super(context, dbManager.openSystemDatabase(), isTrans);
	}

	private synchronized static void init(Context context,boolean isTransaction){
		if(instance == null){
			String databaseName = "TitleItem";
			dbManager =	DBManager.getInstance(context, databaseName);
			isTrans = isTransaction;
			instance = new TitleItemDao(context);
		}
	}
	
	public static TitleItemDao getInstance(Context context,boolean isTransaction){
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
	public void insert(TitleItem titleItem){
		
		if(titleItem == null){
			return;
		}
		
		openCurrentDataBase();
		save(titleItem);
	}
	
	public void insertList(List<TitleItem> list){
		if(list == null){
			return;
		}
		
		for(TitleItem item : list){
			insert(item);
		}
	}
	
	/**
	 * 查询信息
	 * @return
	 */
	public List<TitleItem> queryAllTitleItem(){
		
		openCurrentDataBase();
		List<TitleItem> list = queryPageData("select * from TitleItem", null);
		
		return list;
	}
	
	/**
	 * 删除所有信息
	 */
	public void deleteAllTitleItem(){
		openCurrentDataBase();
		removeBySQL("delete from TitleItem");
	}
}
