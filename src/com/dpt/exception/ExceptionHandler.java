package com.dpt.exception;

import java.lang.Thread.UncaughtExceptionHandler;

import org.hjh.tools.AppLogger;

import com.dpt.config.BaseConfig;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;


public class ExceptionHandler implements UncaughtExceptionHandler{

	private Context mContext;
	
	public ExceptionHandler(Context context){
		mContext = context;
	}
	
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		new Thread(new Runnable() {
				
				@Override
				public void run() {
					Looper.prepare();
					Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();
					Looper.loop();
				}
			}).start();
		
		AppLogger logger =	AppLogger.getInstance();
		if(logger != null){
			String path  = logger.createErrorPath(mContext,BaseConfig.PATH_ERROR);
			if(path != null){
				logger.saveErrorFile(thread, ex, path);
			}
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(10);
		
	}

}
