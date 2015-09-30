package com.dpt.base;

import org.hjh.async.framework.AppHandler;
import org.hjh.async.framework.AsyncNetWorkTask;
import org.hjh.async.framework.AsyncTaskExecutor;

import com.dpt.bean.ResultObject;
import com.dpt.bean.StutasInfo;
import com.dpt.config.BaseConstants;


public class PublicComponent extends BaseComponent{

	private static PublicComponent instance;
	
	private PublicComponent(){
		
	}
	
	private synchronized static void syncInit(){
		if(instance == null){
			instance = new PublicComponent();
		}
	}
	
	public static PublicComponent getInstance(){
		if(instance == null){
			syncInit();
		}
		
		return instance;
	}
	
	/**
	 * 登陆
	 * @param userName
	 * @param pwd
	 * @param handler
	 */
	public void loginIn(final String userName,final String pwd,final AppHandler handler){
		AsyncTaskExecutor.executeTask(new AsyncNetWorkTask(handler) {
			
			@Override
			public void dispose() {
				ResultObject temp = result.clone();
				boolean noerror = PublicApi.getInstance().loginIn(userName, pwd,temp);
				if(noerror){
					sendMessage(BaseConstants.RESULT_LOGIN_SUCCESS);
				}else{
					sendMessage(BaseConstants.RESULT_LOGIN_FAILED,temp);
				}
			}
		});
	}
	
	/**
	 * 注册
	 * @param userName
	 * @param pwd
	 * @param handler
	 */
	public void register(final String userName,final String email,final String pwd, final String pwd2,final AppHandler handler){
		AsyncTaskExecutor.executeTask(new AsyncNetWorkTask(handler) {
		
			@Override
			public void dispose() {
				ResultObject temp = result.clone();
				boolean noerror = PublicApi.getInstance().register(userName, email,pwd,pwd2,temp);
				if(noerror){
					sendMessage(BaseConstants.RESULT_REGISTER_SUCCESS);
				}else{
					sendMessage(BaseConstants.RESULT_REGISTER_FAILED,temp);
				}
			}
		});
	}
	
	/**
	 * 退出
	 * @param handler
	 */
	public void loginOut(final AppHandler handler){
		AsyncTaskExecutor.executeTask(new AsyncNetWorkTask(handler) {
			
			@Override
			public void dispose() {
				ResultObject temp = result.clone();
				boolean noerror = PublicApi.getInstance().loginOut(temp);
				if(noerror){
					sendMessage(BaseConstants.RESULT_LOGINOUT_SUCCESS);
				}else {
					sendMessage(BaseConstants.RESULT_LOGINOUT_FAILED,temp);
				}
			}
		});
	}
}
