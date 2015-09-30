package cn.com.nbd.nbdmobile.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hjh.tools.FileTools;
import org.hjh.tools.StringTools;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class AppTools {

	static final String TAG = "TotooleUtils";

	public static final SimpleDateFormat PUBLISHFORMAT = new SimpleDateFormat(
			"yyyy-M-dd");
	public static final SimpleDateFormat NAMEFORMAT = new SimpleDateFormat(
			"yyyyMMdd_HHmmss");
	public static final SimpleDateFormat SHORTFORMAT = new SimpleDateFormat(
			"HH:mm");
	public static final SimpleDateFormat IMGNAMEFORMAT = new SimpleDateFormat(
			"HHmmss");
	public static final SimpleDateFormat FORMAT_5 = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");
	public static SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");

	public static String formatDateTime(long time) {
        if (0 == time) {
            return "";
        }
        
        return mDateFormat.format(new Date(time));
	}
	
	public static String getRecordName() {

		Date curDate = new Date(System.currentTimeMillis());

		return "rc" + NAMEFORMAT.format(curDate) + ".3gp";
	}

	public static String getImgFrontName() {

		Date curDate = new Date(System.currentTimeMillis());
		return "img" + IMGNAMEFORMAT.format(curDate) + "_";
	}

	public static File getImgFilePath(Context context, String fileName) {

		File file = null;
		String path = getBitmapTempDirectory(context);
		
		File dir = new File(path);
		
		if(!dir.exists()){
			dir.mkdir() ;
		}

		if (!StringTools.isEmpty(path)) {
			file = new File(path + fileName + ".jpg");
		}

		return file;
	}

	/** 创建压缩图片 */
	public final static File createCompressBitmap(String path,Context context) {
		return FileTools.createCompressBitmap(context, path);
	}

	public static final String createBitmapTempFile(Context context) {

		String dir = getBitmapTempDirectory(context);

		if (dir == null) {
			return null;
		}

		return dir + String.valueOf(System.currentTimeMillis());
	}

	public static final String getBitmapTempDirectory(Context context) {
		return FileTools.getBitmapTempDirectory(context);
	}

	public static final void clearTempFile(Context context) {
		FileTools.clearTempFile(context);
	}

	// 分享图片路径
	public static String getShareImagePath(Context context) {

		String dir = FileTools.createLocalDevicePath(context,
				"");

		File file = new File(dir, "");

		if (!file.exists()) {
			return null;
		}

		return file.getAbsolutePath();
	}

	// 重构文件
	public static final void copyDrawableToSdcard(final Context context,
			final int resid) {

		if (context == null) {
			return;
		}

		new Thread(new Runnable() {

			@Override
			public void run() {

				String path = getShareImagePath(context);

				if (!StringTools.isEmpty(path)) {
					return;
				}

				Bitmap bitmap = BitmapFactory.decodeResource(
						context.getResources(), resid);

//				FileTools.writeBitmapToFile(context,
//						TotooleConfig.SHARED_IMAGE_NAME, bitmap);

			}
		}).start();
	}

	public static Date toDate(long milliseconds) {

		try {
			return new Date(milliseconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getTotooleMsg(String from, String sendUser) {

		// message 1
		StringBuilder message = new StringBuilder();
		message.append("Totoole号:");
		message.append(sendUser);
		message.append(" 邀请参加");
		message.append(from);

		message.append("途图乐下载地址:");
		message.append("http://www.totoole.cn:8080/Totoole.apk");

		return message.toString();
	}

	public static int getHeight(View view) {

		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);

		view.measure(w, h);

		return view.getMeasuredHeight();
	}

	public static String parseMS(Long ms) {

		long curms = System.currentTimeMillis();
		long result = curms - ms;

		if (result < 60000) {
			return "刚刚";
		} else if (result < 3600000) {
			result /= 60000;
			return result + " 分钟前";
		} else if (result < 86400000) {
			if (result > curms % 86400000) {
				return "昨天";
			}
			result /= 3600000;
			return result + " 小时前";
		} else if (result < 608400000) {
			result /= 86400000;
			return result + " 天前";
		} else {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(ms);
			return SHORTFORMAT.format(c.getTime());
		}
	}

	public static String getPublishFrontTime(Date publishDate) {

		long curms = System.currentTimeMillis();
		long result = curms - publishDate.getTime();

		if (result < 86400000) {

			if (result > curms % 86400000) {
				return PUBLISHFORMAT.format(publishDate);
			}

			return SHORTFORMAT.format(publishDate);
		} else {
			return PUBLISHFORMAT.format(publishDate);
		}

	}

	public static String getShortTime(Date date) {
		return date != null ? SHORTFORMAT.format(date) : "";
	}

	/** 获得年月日 */
	public static String getYearMonthDay(Date date) {
		return date != null ? PUBLISHFORMAT.format(date) : "";
	}

	public static int findIndex(String[] content, String element) {

		int count = 0;

		for (String data : content) {

			if (element.equals(data)) {
				return count;
			}

			count++;
		}

		return -1;
	}

	public static int toInt(Object obj) {

		if (obj == null)

			return 0;

		return toInt(obj.toString(), 0);
	}

	public static int toInt(String str, int defValue) {

		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}

		return defValue;
	}

	public static String getDistance(double lat1, double lot1, double lat2,
			double lot2) {

		try {
			double radLat1 = lat1 * Math.PI / 180.0;
			double radLat2 = lat2 * Math.PI / 180.0;
			double a = radLat1 - radLat2;
			double b = lot1 * Math.PI / 180.0 - lot2 * Math.PI / 180.0;
			double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2),
					2)
					+ Math.cos(radLat1)
					* Math.cos(radLat2)
					* Math.pow(Math.sin(b / 2), 2)));
			distance = distance * 6378.137;
			distance = Math.round(distance * 10000);
			// if (distance / 1000 > 0) {
			return (distance / 1000) + "km";
			// }
			// return distance + "m";
		} catch (Exception e) {
			return "data errer";
		}
	}
}
