package cn.com.nbd.nbdmobile.model;

public class Constants {
	public final static String STANDARD_DATE_FORMATE = "dd MMM yyyy HH:mm:ss z";

	public static final String WEIBO_KEY = "2934169357";

	public static final String QQWEIBO_KEY = "0b0f377fc2e642c8866f7d2458f0ac3a";

	public static final int NUMBER_OF_PAGE = 10;

	public static final String APP_KEY = "f4af4864997a00ddff7e1765e643f9ec";
	
	
	//把原先的：	CACHE_DIRECTORY = ".nbd/cache";用户不可见
	//改变成：	CACHE_DIRECTORY = "nbd/cache";用户可见
	public static final String CACHE_DIRECTORY = ".nbd/cache";

	
	public static final String ROLLING_NEWS_WEB_SERVICE = "http://api.nbd.com.cn/v1/articles/rolling_news.json?client_type=1&app_key=f4af4864997a00ddff7e1765e643f9ec&column_id=%s&count=%s&page=%s%s";
	public static final String COLUMN_NEWS_WEB_SERVICE = "http://api.nbd.com.cn/v1/columns/%s/articles.json?client_type=1&app_key=f4af4864997a00ddff7e1765e643f9ec&count=%s&page=%s%s";
	public static final String LIVE_LIST_WEB_SERVICE = "http://api.nbd.com.cn/v1/stock_lives.json?client_type=1&app_key=f4af4864997a00ddff7e1765e643f9ec&count=%s&page=%s%s";
	public static final String LIVE_DETAIL_WEB_SERVICE = "http://api.nbd.com.cn/v1/stock_lives/%s.json?client_type=1&app_key=f4af4864997a00ddff7e1765e643f9ec&count=%s%s";
	public static final String LIVE_CHECK_NEW_TALK_SERVICE = "http://api.nbd.com.cn/v1/stock_lives/%s/check_new.json?client_type=1&app_key=f4af4864997a00ddff7e1765e643f9ec&timeline=%s";
	public static final String COLUMNISTS_LAST_UPDATE_SERVICE = "http://api.nbd.com.cn/v1/columnists/last_update.json?client_type=1&app_key=f4af4864997a00ddff7e1765e643f9ec&page=%s";
    public static final String ROLLING_NEW_ADD_READ_NUMBER ="http://api.nbd.com.cn/1/columns/mobile_click_count?ids=%s&app_key=f4af4864997a00ddff7e1765e643f9ec&client_key=android";
	
	public static final String MOBILE_AD_SITE = "http://www.nbd.com.cn/mobiles/android";

	
//	public static final String EVENT_HEADLINES = "Headlines";
//	public static final String EVENT_HEADLINES_REFRESH = "Headlines-Refresh";
//	public static final String EVENT_HEADLINES_LOAD_MORE = "Headlines-LoadMore";
//
//	public static final String EVENT_ROLLING = "Rolling";
//	public static final String EVENT_ROLLING_GENERAL = "Rolling-General";
//	public static final String EVENT_ROLLING_GENERAL_REFRESH = "Rolling-General-Refresh";
//	public static final String EVENT_ROLLING_GENERAL_LOAD_MORE = "Rolling-General-LoadMore";
//	public static final String EVENT_ROLLING_MARKET = "Rolling-Market";
//	public static final String EVENT_ROLLING_MARKET_REFRESH = "Rolling-Market-Refresh";
//	public static final String EVENT_ROLLING_MARKET_LOAD_MORE = "Rolling-Market-LoadMore";
//	public static final String EVENT_ROLLING_BUSINESS = "Rolling-Business";
//	public static final String EVENT_ROLLING_BUSINESS_REFRESH = "Rolling-Business-Refresh";
//	public static final String EVENT_ROLLING_BUSINESS_LOAD_MORE = "Rolling-Business-LoadMore";
//	public static final String EVENT_ROLLING_GLOBAL = "Rolling-Global";
//	public static final String EVENT_ROLLING_GLOBAL_REFRESH = "Rolling-Global-Refresh";
//	public static final String EVENT_ROLLING_GLOBAL_LOAD_MORE = "Rolling-Global-LoadMore";
//
//	public static final String EVENT_MARKET = "Market";
//	public static final String EVENT_MARKET_NEWS = "Market-News";
//	public static final String EVENT_MARKET_NEWS_REFRESH = "Market-News-Refresh";
//	public static final String EVENT_MARKET_NEWS_LOAD_MORE = "Market-News-LoadMore";
//	public static final String EVENT_MARKET_ANALYSIS = "Market-Analysis";
//	public static final String EVENT_MARKET_ANALYSIS_REFRESH = "Market-Analysis-Refresh";
//	public static final String EVENT_MARKET_ANALYSIS_LOAD_MORE = "Market-Analysis-LoadMore";
//	public static final String EVENT_MARKET_IPO = "Market-IPO";
//	public static final String EVENT_MARKET_IPO_REFRESH = "Market-IPO-Refresh";
//	public static final String EVENT_MARKET_IPO_LOAD_MORE = "Market-IPO-LoadMore";
//	public static final String EVENT_MARKET_SURVEY = "Market-Survey";
//	public static final String EVENT_MARKET_SURVEY_REFRESH = "Market-Survey-Refresh";
//	public static final String EVENT_MARKET_SURVEY_LOAD_MORE = "Market-Survey-LoadMore";
//
//	public static final String EVENT_NTT = "NTT";
//	public static final String EVENT_NTT_HEADLINES = "NTT-Headlines";
//	public static final String EVENT_NTT_HEADLINES_REFRESH = "NTT-Headlines-Refresh";
//	public static final String EVENT_NTT_HEADLINES_LOAD_MORE = "NTT-Headlines-LoadMore";
//	public static final String EVENT_NTT_UPDATE = "NTT-Update";
//	public static final String EVENT_NTT_UPDATE_REFRESH = "NTT-Update-Refresh";
//	public static final String EVENT_NTT_UPDATE_LOAD_MORE = "NTT-Update-LoadMore";
//	public static final String EVENT_NTT_YE_TAN = "NTT-YeTan";
//	public static final String EVENT_NTT_YE_TAN_REFRESH = "NTT-YeTan-Refresh";
//	public static final String EVENT_NTT_YE_TAN_LOAD_MORE = "NTT-YeTan-LoadMore";
//
//	public static final String EVENT_LIVE = "Live";
//	public static final String EVENT_LIVE_REFRESH = "Live-Refresh";
//	public static final String EVENT_LIVE_LOAD_MORE = "Live-LoadMore";
//
	
//	public static final String EVENT_SHARE = "Share";
//	public static final String EVENT_FAVORITE = "Favorite";
//	public static final String EVENT_OFFLINE = "Offline";
	
			public static final String EVENT_ROLLING = "Rolling";  //ok
			public static final String EVENT_ROLLING_REFRESH ="Rolling-Refresh"; //ok
			public static final String EVENT_ROLLING_MORE= "Rolling-Load-more"; //ok
			
			public static final String EVENT_HEAVY= "Heavy"; //ok
			public static final String EVENT_HEAVY_REFRESH= "Heavy-Refresh";
			public static final String EVENT_HEAVY_MORE ="Heavy-Load-more";
			
			public static final String EVENT_MAKE_MONEY= "Make-money";//ok
			public static final String EVENT_MAKE_MONEY_REFRESH = "Make-money-Refresh";
			public static final String EVENT_MAKE_MONEY_MORE="Make-money-Load-more";
			
			public static final String EVENT_MAKE_MONEY_FOCUS_NEWS = "Make-money-Focus-News";
			public static final String EVENT_MAKE_MONEY_FOCUS_NEWS_REFRESH = "Make-money-Focus-News-Refresh";
			public static final String EVENT_MAKE_MONEY_FOCUS_NEWS_MORE ="Make-money-Focus-News-Load-more";
			
			public static final String EVENT_MAKE_MONEY_HEAVY_RECOMMENDED = "Make-money-Heavy-recommended";
			public static final String EVENT_MAKE_MONEY_HEAVY_RECOMMENDED_REFRESH = "Make-money-Heavy-recommended-Refresh";
			public static final String EVENT_MAKE_MONEY_HEAVY_RECOMMENDED_MORE ="Make-money-Heavy-recommended-Load-more";
			
			public static final String EVENT_MAKE_MONEY_A_DYNAMICS = "Make-money-A-dynamics";
			public static final String EVENT_MAKE_MONEY_A_DYNAMICS_REFRESH= "Make-money-A-dynamics-Refresh";
			public static final String EVENT_MAKE_MONEY_A_DYNAMICS_MORE ="Make-money-A-dynamics-Load-more";
			
			public static final String EVENT_MAKE_MONEY_ANNOUNCEMENT_EXPRESS = "Make-money-Announcement-Express";
			public static final String EVENT_MAKE_MONEY_ANNOUNCEMENT_EXPRESS_REFRESH = "Make-money-Announcement-Express-Refresh";
			public static final String EVENT_MAKE_MONEY_ANNOUNCEMENT_EXPRESS_MORE = "Make-money-Announcement-Express-Load-more";
			
			public static final String EVENT_BE_REALLY_SOMETHING = "Be-really-something";
			public static final String EVENT_BE_REALLY_SOMETHING_REFRESH = "Be-really-something-Refresh";
			public static final String EVENT_BE_REALLY_SOMETHING_MORE= "Be-really-something-Load-more";
			
			public static final String EVENT_INSIGHT = "Insight";
			public static final String EVENT_INSIGHT_REFRESH = "Insight-Refresh";
			public static final String EVENT_INSIGHT_MORE ="Insight-Load-more";
			
			public static final String EVENT_APP_HEADER= "APP-header";
			public static final String EVENT_APP_HEADER_REFRESH =  "APP-header-Refresh";
			public static final String EVENT_APP_HEADER_MORE ="APP-header-Load-more";
			
			public static final String EVENT_NEWSPAPER = "Newspaper";
			public static final String EVENT_FAVORITE = "Favorite";
			public static final String EVENT_PREV = "Prev";
			public static final String EVENT_NEXT = "Next";
			public static final String EVENT_OFFLINE_READING = "Offline-reading";
			public static final String EVENT_SETUP = "Setup";
	
}
