package cn.com.nbd.nbdmobile.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.hjh.image.display.ImageLoader;
import org.hjh.image.display.SyncImageLoader.OnImageLoadListener;
import org.hjh.tools.MemoryTools;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.com.nbd.nbdmobile.api.HomeComponent;
import cn.com.nbd.nbdmobile.config.BaseConfig;
import cn.com.nbd.nbdmobile.listener.ITitleClickListener;
import cn.com.nbd.nbdmobile.util.AppTools;
import cn.com.nbd.nbdmobile.view.PullToRefreshListView;
/**
 * 
 * @author Dell
 *
 */
public abstract class BaseActivity extends AppBaseActivity implements ITitleClickListener{

	protected Gson gson  = new Gson();
	private SparseArray<TextView> array = new SparseArray<TextView>();
	private View.OnClickListener onClickListener;
	protected HomeComponent component = HomeComponent.getInstance();
	protected OnImageLoadListener roundImageListener = new OnImageLoadListener() {
		
		@Override
		public void onImageLoad(Drawable drawable, ImageView view) {
			try {
				Bitmap bm = ((BitmapDrawable)drawable).getBitmap();
				bm = Bitmap.createScaledBitmap(bm, 120,120, false);
				Drawable roundDrawable = new BitmapDrawable(ImageLoader.toRoundCorner(bm, 20));
//				Drawable roundDrawable =	new BitmapDrawable(ImageLoader.toRoundCorner(((BitmapDrawable)drawable).getBitmap(), 40));
				view.setBackgroundDrawable(roundDrawable);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void onError(int resid, ImageView view) {
			view.setBackgroundResource(resid);
		}
		
		public void callBackSize(int width, int height,View view) {
			
			
		}
	};
	
	private boolean noOperate = true;
	private Timer timer;
	private TimerTask task;
	private final long PERIOD = 1000 * 60 * 2;
	private int count = 0;
	
	public boolean isNoOperate() {
		return noOperate;
	}

	public void setNoOperate(boolean noOperate) {
		this.noOperate = noOperate;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void onUserInteraction() {
		setNoOperate(false);
		setCount(0);
		super.onUserInteraction();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getStatusBarHeight();
	}
	
	
	/**
	 * @return
	 */
	protected int getStatusBarHeight(){
		Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        } 
        BaseConfig.STATUS_BAR_HEIGHT = statusBarHeight;
        return statusBarHeight;
	}
	
	
	@Override
	protected void onDestroy() {
		MemoryTools.reset();
		super.onDestroy();
	}
	
	protected void resetView(int position){
//		for(int index = 0;index < array.size();index ++){
//			if(index == position){
//				array.get(index).setCompoundDrawablesWithIntrinsicBounds(0,selectRes[index],0,0);
//				switchView(index);
//			}else {
//				array.get(index).setCompoundDrawablesWithIntrinsicBounds(0,normalRes[index],0,0);
//			}
//		}
	}

	@Override
	public void onClickLeftView() {
		
	}

	@Override
	public void onClickRightView() {
		
	}
	
	

	@Override
	public void onClickNearRightView() {
		
	}
	
	/**
	 * listתstring
	 * @param list
	 * @return
	 */
	protected String listToStr(List<String> list){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i < list.size() - 1) {
				sb.append(list.get(i) + ",");
			} else {
				sb.append(list.get(i));
			}
		}		
		
		return sb.toString();
	}
	
	
	
	/**
     * 
     * unicode ת���� ����
     * @param theString
     * @return
     */
 
    protected  String decodeUnicode(String theString) {//%3Cimg%20src%3D%22http%3A//files.duopingtong.cn/face/%5B%u5472%u7259%5D.png%22%3E
 
        char aChar;
        theString = tmptrans(theString);
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '%') {//aChar == '\\' || 
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException(
                                    "Malformed   \\uxxxx   encoding.");
                        }
 
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't'){
                        aChar = '\t';
                    } else if (aChar == 'r'){
                        aChar = '\r';
                    }else if (aChar == 'n'){
                        aChar = '\n';
                    }else if (aChar == 'f'){
                        aChar = '\f';
                    }
                    
                    outBuffer.append(aChar);
                }
            } else{
                outBuffer.append(aChar);
            }
        }
 
        return outBuffer.toString();
    }
    
    protected  String ascii2native(String ascii) {  
        int n = ascii.length() / 6;  
        StringBuilder sb = new StringBuilder(n);  
        for (int i = 0, j = 2; i < n; i++, j += 6) {  
            String code = ascii.substring(j, j + 4);  
            char ch = (char) Integer.parseInt(code, 16);  
            sb.append(ch);  
        }  
        return sb.toString();  
    }  
    
	protected void setLastUpdateTime(PullToRefreshListView refreshListView) {
        String text = AppTools.formatDateTime(System.currentTimeMillis());
        refreshListView.setLastUpdatedLabel(text);
	}
	
    protected String unicodeToString(String s) { 
    	if(s == null) return null;
    	StringBuffer result = new StringBuffer(); 
    	int tempI,i,ch;
    	for(i=0;i<s.length();i++) {
    	//�����Unicode����ʼ��' \\u '�� ����ת���ɶ�Ӧ�ĺ��� 
	    	if((ch = s.charAt(i)) == '\\') {
		    	tempI = i; 
		    	i+=2;
		    	while(i<s.length()&&s.charAt(i) == 'u') { 
		    		i++; 
		    	} 
		    	
		    	if(i+4<=s.length()) { //��Unicode��ʮ�������ת����ʮ������ 
			    	ch = Integer.parseInt(s.substring(i,i+4),16);
			    	i+= 3; 
		    	}else { 
		    		i = tempI;
		    	}
	    	} //���ں��֣�������������ת�����ַ�󣬸���result�󣬶����Ӣ���ַ�ֱ��ʹ�ü��� 
	    
	    	result.append((char)ch) ;
    	} 
    	return result.toString(); 
	}

    /**
     * ������ת��
     * @param s
     * @return
     */
	protected String tmptrans(String s){
    	int i=s.length();
    	int j=0;
    	String s5="";
    	String s6="";
    	for(;j<i;){
    		String s2 = s.substring(j, j + 1);
	    	if(s2.equals("%")){
	    		String s4 = s.substring(j, j + 3);
		    	if(s4.equals("%5C")){
		    		s5 = "\\";
		    	}else if(s4.equals("%22")){
		    		s5 = "\"";
		    	}else if(s4.equals("%3C")){
		    		s5 = "<";
		    	}else if(s4.equals("%3E")){
		    		s5 = ">";
		    	}else if(s4.equals("%3A")){
		    		s5 = ":";
		    	}else if(s4.equals("%3B")){
		    		s5 = ";";
		    	}else if(s4.equals("%27")){
		    		s5 = "'";
		    	}else if(s4.equals("%21")){
		    		s5 = "!";
		    	}else if(s4.equals("%23")){
		    		s5 = "#";
		    	}else if(s4.equals("%24")){
		    		s5 = "$";
		    	}else if(s4.equals("%25")){
		    		s5 = "%";
		    	}else if(s4.equals("%5E")){
		    		s5 = "^";
		    	}else if(s4.equals("%26")){
		    		s5 = "&";
		    	}else if(s4.equals("%28")){
		    		s5 = "(";
		    	}else if(s4.equals("%29")){
		    		s5 = ")";
		    	}else if(s4.equals("%3D")){
		    		s5 = "=";
		    	}else if(s4.equals("%7B")){
		    		s5 = "{";
		    	}else if(s4.equals("%7D")){
		    		s5 = "}";
		    	}else if(s4.equals("%5B")){
		    		s5 = "[";
		    	}else if(s4.equals("%5D")){
		    		s5 = "]";
		    	}else if(s4.equals("%20")){
		    		s5 = " ";
		    	}else if(s4.equals("%2C")){
		    		s5 = ",";
		    	}else if(s4.equals("%3F")){
		    		s5 = "?";
		    	}else if(s4.equals("%7C")){
		    		s5 = "|";
		    	}else if(s4.equals("%60")){
		    		s5 = "`";
		    	}else if(s4.equals("%7E")){
		    		s5 = "~";
		    	}else if(s4.equals("%0A")){
		    		s5 = " ";
		    	}else if(s4.equals("%0D")){
		    		s5 = " ";
		    	}else if(s4.equals("%09")){
		    		s5 = " ";
		    	}else{
		    		s5 = s4;
		    	}
		    	
		    	s6 += s5;
		    	j += 3;
	    	}else{
		    	s6 += s2;
		    	j++;
	    	}
    	}
    	
    	return s6;
	}
}
