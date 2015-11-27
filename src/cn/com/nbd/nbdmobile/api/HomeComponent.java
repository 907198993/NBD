package cn.com.nbd.nbdmobile.api;

import com.google.gson.reflect.TypeToken;

import org.hjh.async.framework.AppHandler;
import org.hjh.async.framework.AsyncCacheWork;
import org.hjh.async.framework.AsyncNetWorkTask;
import org.hjh.async.framework.AsyncTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.nbd.nbdmobile.bean.ActivityArticle;
import cn.com.nbd.nbdmobile.bean.ActivityArticleList;
import cn.com.nbd.nbdmobile.bean.ActivityArticleRollList;
import cn.com.nbd.nbdmobile.bean.Article;
import cn.com.nbd.nbdmobile.bean.ArticleForAd;
import cn.com.nbd.nbdmobile.bean.NewsPaper;
import cn.com.nbd.nbdmobile.bean.ResultObject;
import cn.com.nbd.nbdmobile.dao.ActivityArticleDao;
import cn.com.nbd.nbdmobile.dao.ArticleDetailDao;
import cn.com.nbd.nbdmobile.dao.ArticleDetailForRollDao;


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


	public void queryAdInfo(final AppHandler handler) {
		AsyncTaskExecutor.executeTask(new AsyncNetWorkTask(handler) {

			@Override
			public void dispose() {
				ResultObject temp = result.clone();

				ArticleForAd list = (ArticleForAd) HomeApi.getInstance().queryAdInfo(temp, new TypeToken<ArticleForAd>() {
				}.getType(), null);
				if (null == list) {
					sendMessage(AppConstants.RESULT_QUERY_AD_ARTICLE_FAILED, temp);
				} else {
					sendMessage(AppConstants.RESULT_QUERY_AD_ARTICLE_SUCCESS, list.getArticles());
				}
			}
		});
	}


	/**
	 *  滚动
	 *
	 * @param page
	 * @param count
	 * @param handler
	 */
	public void queryRollArticle(final int page, final int count, final AppHandler handler) {
		AsyncTaskExecutor.executeTask(new AsyncCacheWork(handler) {

			@Override
			public void onQueryCache() {
				ActivityArticleRollList article = new ActivityArticleRollList();
				try {
					article.setArticles(ArticleDetailForRollDao.getInstance(context, false).queryAllArticleDetailForRoll());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (article.getArticles().size() < 1) {
					sendMessage(AppConstants.RESULT_QUERY_ROLL_FAILED);
				} else {
					sendMessage(AppConstants.RESULT_QUERY_ROLL_SUCCESS, article);
				}
			}

			@Override
			public void onQueryWebApi() {
				{
					ResultObject temp = result.clone();
					ActivityArticleRollList article = (ActivityArticleRollList) HomeApi.getInstance().queryRollArticle(page, count, temp, new TypeToken<ActivityArticleRollList>() {
					}.getType(), null);
					//	List<ArticleDetailForQuick> ArticleDetailForQuick  = (List<ArticleDetailForQuick>) HomeApi.getInstance().queryArticle(page,count,temp,new  TypeToken<Article>(){}.getType(),null);
					if (null == article) {
						onQueryCache();//获取失败则通过本地获取
					} else {
						sendMessage(AppConstants.RESULT_QUERY_ROLL_SUCCESS, article);
						/////数据库操
						ArticleDetailForRollDao.getInstance(context, false).deleteAllArticleDetailForRoll();
						//	ArticleDetailForQuickDao.getInstance(context, false).insertList(ArticleDetailForQuick);
						ArticleDetailForRollDao.getInstance(context, false).insertList(article.getArticles());

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

	//活动栏目
	public void queryNewsActivityList(final int page, final int count, final AppHandler handler) {
		AsyncTaskExecutor.executeTask(new AsyncCacheWork(handler) {

			@Override
			public void onQueryCache() {
				List<ActivityArticle> ActivityArticle = new ArrayList<ActivityArticle>();
			//	ActivityArticle article = new ActivityArticle();
//				try {
//					ActivityArticleDao.getInstance(context, false).queryAllActivityArticle();
//					ActivityArticle.add();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				if (article.getArticles().size() < 1) {
//					sendMessage(AppConstants.RESULT_QUERY_ARTICLE_FAILED);
//				} else {
//					sendMessage(AppConstants.RESULT_QUERY_ARTICLE_SUCCESS, article);
//				}
			}

			@Override
			public void onQueryWebApi() {
				{
					ResultObject temp = result.clone();

					ActivityArticleList article = (ActivityArticleList) HomeApi.getInstance().queryNewsActivityList(page, count, temp, new TypeToken<ActivityArticleList>() {
					}.getType(), null);
					//	List<ArticleDetailForQuick> ArticleDetailForQuick  = (List<ArticleDetailForQuick>) HomeApi.getInstance().queryArticle(page,count,temp,new  TypeToken<Article>(){}.getType(),null);
					if (null == article) {
						onQueryCache();//获取失败则通过本地获取
					} else {
						sendMessage(AppConstants.RESULT_QUERY_ACTIVITY_ARTICLE_SUCCESS, article.getData());
						/////数据库操
						ActivityArticleDao.getInstance(context, false).deleteAllActivityArticle();
						//	ArticleDetailForQuickDao.getInstance(context, false).insertList(ArticleDetailForQuick);
						ActivityArticleDao.getInstance(context, false).insertList(article.getData());

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
