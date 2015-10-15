package cn.com.nbd.nbdmobile.tool;

import android.app.Activity;
import android.util.DisplayMetrics;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.InflaterInputStream;

public class BaseTools {
	private static final int BUFFER_SIZE = 2048;
	private static final int EOF = -1;

	public final static int getWindowsWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}


	public final static int getWindowHight(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	public static byte[] loadOriginalDataFromURL(String u) throws Exception {
		URL url = new URL(u);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		InputStream is = conn.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int c;
		byte[] buf = new byte[BUFFER_SIZE];

		while (true) {
			c = bis.read(buf);
			if (c == EOF)
				break;

			baos.write(buf, 0, c);
		}

		conn.disconnect();
		is.close();

		byte[] data = baos.toByteArray();
		baos.flush();

		return data;
	}
















	public static byte[] decompress(byte[]  compress) throws Exception {

		ByteArrayInputStream bais = new ByteArrayInputStream(compress);
		InflaterInputStream iis = new InflaterInputStream(bais);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int c = 0;
		byte[] buf = new byte[BUFFER_SIZE];
		while (true) {
			c = iis.read(buf);

			if (c == EOF)
				break;

			baos.write(buf, 0, c);
		}



		baos.flush();

		return baos.toByteArray();
	}

}
