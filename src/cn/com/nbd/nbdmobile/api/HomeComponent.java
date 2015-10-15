package cn.com.nbd.nbdmobile.api;

import com.google.gson.reflect.TypeToken;

import org.hjh.async.framework.AppHandler;
import org.hjh.async.framework.AsyncCacheWork;
import org.hjh.async.framework.AsyncNetWorkTask;
import org.hjh.async.framework.AsyncTaskExecutor;

import java.util.Map;

import cn.com.nbd.nbdmobile.bean.Article;
import cn.com.nbd.nbdmobile.bean.NewsPaper;
import cn.com.nbd.nbdmobile.bean.ResultObject;
import cn.com.nbd.nbdmobile.bean.StockDetail;
import cn.com.nbd.nbdmobile.dao.ArticleDetailDao;


public final class HomeComponent extends BaseComponent {

	private static HomeComponent instance;

	private HomeComponent() {

	}

	public static HomeComponent getInstance() {
		if (instance == null) {
			//init();
			synchronized (HomeComponent.class) {//Double-Check Locking
				if (instance == null) {
					instance = new HomeComponent();
				}
			}
		}

		return instance;
	}

	//释放内存
	private void resetMap(Map<String, Object> map) {
		map.clear();
		map = null;
	}


	public void queryHomeArticle(final boolean recommend, final int currentPage, final String type, final AppHandler handler) {
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


	public void addReadNumber(final long ids, final AppHandler handler) {
		AsyncTaskExecutor.executeTask(new AsyncNetWorkTask(handler) {


			@Override
			public void dispose() {
				ResultObject temp = result.clone();
				Object list = HomeApi.getInstance().addReadNumber(ids, temp);
//				if(null == list){
//					sendMessage(AppConstants.RESULT_QUERY_HOME_RECOMMEND_FAILED,temp);
//				}else{
//					sendMessage(AppConstants.RESULT_QUERY_HOME_RECOMMEND_SUCCESS,list);
//				}
			}
		});
	}

	public void queryNewsPaperList(final AppHandler handler) {
		AsyncTaskExecutor.executeTask(new AsyncNetWorkTask(handler) {

			@Override
			public void dispose() {
				ResultObject temp = result.clone();

				NewsPaper list = (NewsPaper) HomeApi.getInstance().queryNewsPaperList(temp, new TypeToken<NewsPaper>() {
				}.getType(), null);
				if (null == list) {
					sendMessage(AppConstants.RESULT_QUERY_NEWSPAPER_FAILED, temp);
				} else {
					sendMessage(AppConstants.RESULT_QUERY_NEWSPAPER_SUCCESS, list);
				}
			}
		});
	}

	public void queryStockInfo(final AppHandler handler) {
		AsyncTaskExecutor.executeTask(new AsyncNetWorkTask(handler) {

			@Override
			public void dispose() {
				ResultObject temp = result.clone();

				StockDetail list = (StockDetail) HomeApi.getInstance().queryStockInfo(temp, new TypeToken<StockDetail>() {
				}.getType(), null);
				if (null == list) {
					sendMessage(AppConstants.RESULT_QUERY_STOCK_CONTENT_FAILED, temp);
				} else {
					sendMessage(AppConstants.RESULT_QUERY_STOCK_CONTENT_SUCCESS, list);
				}
			}
		});
	}

	//文章
	public void queryArticle(final int page, final int count, final AppHandler handler) {
		AsyncTaskExecutor.executeTask(new AsyncCacheWork(handler) {

			@Override
			public void onQueryCache() {
				Article article = new Article();
				try {
					article.setArticles(ArticleDetailDao.getInstance(context, false).queryAllArticleDetails());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (article.getArticles().size() < 1) {
					sendMessage(AppConstants.RESULT_QUERY_ARTICLE_FAILED);
				} else {
					sendMessage(AppConstants.RESULT_QUERY_ARTICLE_SUCCESS, article);
				}
			}

			@Override
			public void onQueryWebApi() {
				{
					ResultObject temp = result.clone();
					Article article = (Article) HomeApi.getInstance().queryArticle(page, count, temp, new TypeToken<Article>() {
					}.getType(), null);
					//	List<ArticleDetailForQuick> ArticleDetailForQuick  = (List<ArticleDetailForQuick>) HomeApi.getInstance().queryArticle(page,count,temp,new  TypeToken<Article>(){}.getType(),null);
					if (null == article) {
						onQueryCache();//获取失败则通过本地获取
					} else {
						sendMessage(AppConstants.RESULT_QUERY_ARTICLE_SUCCESS, article);
						/////数据库操
						ArticleDetailDao.getInstance(context, false).deleteAllArticleDetails();
						//	ArticleDetailForQuickDao.getInstance(context, false).insertList(ArticleDetailForQuick);
						ArticleDetailDao.getInstance(context, false).insertList(article.getArticles());

					}
//					if(null == list){
//						sendMessage(AppConstants.RESULT_QUERY_ARTICLE_FAILED,temp);
//					}else{
//						sendMessage(AppConstants.RESULT_QUERY_ARTICLE_SUCCESS,list);
//					}
				}

			}

		});
	}
}
