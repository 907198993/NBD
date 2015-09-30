package cn.com.nbd.nbdmobile.api;

import java.util.Map;

import org.hjh.async.framework.AppHandler;
import org.hjh.async.framework.AsyncNetWorkTask;
import org.hjh.async.framework.AsyncTaskExecutor;

import cn.com.nbd.nbdmobile.bean.ArticleDetail;
import cn.com.nbd.nbdmobile.bean.ResultObject;
import cn.com.nbd.nbdmobile.bean.StockDetail;

import com.google.gson.reflect.TypeToken;



public final class HomeComponent extends BaseComponent{

	private static HomeComponent instance;
	
	private HomeComponent(){
		
	}
	
	private static synchronized void init(){
		if(instance == null){
			instance = new HomeComponent();
		}
	}
	
	public static HomeComponent getInstance(){
		if(instance == null){
			init();
		}
		
		return instance;
	}
	
	//释放内存
	private void resetMap(Map<String, Object> map){
		map.clear();
		map = null;
	}
	
	
	public void queryHomeArticle(final boolean recommend,final int currentPage,final String type,final AppHandler handler){
		AsyncTaskExecutor.executeTask(new AsyncNetWorkTask(handler) {
			
			@Override
			public void dispose() {
				ResultObject temp = result.clone();
//				List<HomeArticle> list = HomeApi.getInstance().queryHomeArticle(recommend,currentPage,type,temp);
//				if(null == list){
//					sendMessage(AppConstants.RESULT_QUERY_HOME_ARTICLE_FAILED,temp);
//				}else{
//					sendMessage(AppConstants.RESULT_QUERY_HOME_ARTICLE_SUCCESS,list);
//				}
			}
		});
	}
	
	/**
	 * 获取首页推荐
	 * @param type
	 * @param handler
	 */
	public void addReadNumber(final long ids,final AppHandler handler){
		AsyncTaskExecutor.executeTask(new AsyncNetWorkTask(handler) {
			
			
			@Override
			public void dispose() {
				ResultObject temp = result.clone();
				Object  list = HomeApi.getInstance().addReadNumber(ids, temp);
//				if(null == list){
//					sendMessage(AppConstants.RESULT_QUERY_HOME_RECOMMEND_FAILED,temp);
//				}else{
//					sendMessage(AppConstants.RESULT_QUERY_HOME_RECOMMEND_SUCCESS,list);
//				}
			}
		});
	}
	
	public void queryStockInfo(final AppHandler handler){
		AsyncTaskExecutor.executeTask(new AsyncNetWorkTask(handler) {
			
			@Override
			public void dispose() {
				ResultObject temp = result.clone();
				
				StockDetail  list = (StockDetail) HomeApi.getInstance().queryStockInfo(temp,new  TypeToken<StockDetail>(){}.getType(),null);
				if(null == list){
					sendMessage(AppConstants.RESULT_QUERY_STOCK_CONTENT_FAILED,temp);
				}else{
					sendMessage(AppConstants.RESULT_QUERY_STOCK_CONTENT_SUCCESS,list);
				}
			}
		});
	}
	
	//文章
	public void queryArticle( final int page ,final int count,final AppHandler handler){
		AsyncTaskExecutor.executeTask(new AsyncNetWorkTask(handler) {
			
			@Override
			public void dispose() {
				ResultObject temp = result.clone();
				System.err.println("query ----------------------------------------------------------------------");
				ArticleDetail  list = (ArticleDetail) HomeApi.getInstance().queryArticle(page,count,temp,new  TypeToken<ArticleDetail>(){}.getType(),null);
				if(null == list){
					sendMessage(AppConstants.RESULT_QUERY_ARTICLE_FAILED,temp);
				}else{
					sendMessage(AppConstants.RESULT_QUERY_ARTICLE_SUCCESS,list);
				}
			}
		});
	}
}
