package cn.com.nbd.nbdmobile.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;


/**
 *    app 管理
 * @author Dell
 *
 */
public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager(){}
    /**
     * ��һʵ��
     */
    public static AppManager getAppManager(){
        if(instance==null){
            instance=new AppManager();
        }
        return instance;
    }
    /**
     * ���Activity����ջ
     */
    public void addActivity(Activity activity){
        if(activityStack==null){
            activityStack=new Stack<Activity>();
        }
        activityStack.add(activity);
    }
    /**
     * ��ȡ��ǰActivity����ջ�����һ��ѹ��ģ�
     */
    public Activity currentActivity(){
        Activity activity=activityStack.lastElement();
        return activity;
    }
    /**
     * ����ǰActivity����ջ�����һ��ѹ��ģ�
     */
    public void finishActivity(){
        Activity activity=activityStack.lastElement();
        finishActivity(activity);
    }
    /**
     * ����ָ����Activity
     */
    public void finishActivity(Activity activity){
        if(activity!=null){
            activityStack.remove(activity);
            activity.finish();
            activity=null;
        }
    }
    /**
     * ����ָ�������Activity
     */
    public void finishActivity(Class<?> cls){
        for (Activity activity : activityStack) {
            if(activity.getClass().equals(cls) ){
                finishActivity(activity);
            }
        }
    }
    /**
     * ��������Activity
     */
    public void finishAllActivity(){
        for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
//    /**
//     * �������IdeaCodeActivity
//     * @return
//     */
//    public List<IdeaCodeActivity> getAllActivity(){
//        ArrayList<IdeaCodeActivity> listActivity = new ArrayList<IdeaCodeActivity>();
//        for (Activity activity : activityStack) {
//            listActivity.add((IdeaCodeActivity)activity);
//        }
//        return listActivity;
//    }
//    /**
//     * ���Activity��Ʒ���ָ����Activity
//     * @param name
//     * @return
//     */
//    public IdeaCodeActivity getActivityByName(String name){
//        for (Activity ia : activityStack) {
//            if (ia.getClass().getName().indexOf(name) >= 0) {
//                return (IdeaCodeActivity)ia;
//            }
//        }
//        return null;
//    }
    /**
     * �˳�Ӧ�ó���
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            // System.exit(0);
        } catch (Exception e) { }
    }
}
