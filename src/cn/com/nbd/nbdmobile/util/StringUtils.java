package cn.com.nbd.nbdmobile.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	private final static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	private final static SimpleDateFormat dateFormatUserId = new SimpleDateFormat("yyMMddkkmmss");

	private static Pattern p = null;
	private static Matcher m = null;

	/**
	 * ���ַ�תλ��������
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater.parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * ���ַ�תλ��������
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate2(String sdate) {
		try {
			return dateFormater2.parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * ������ת��Ϊ�ַ�
	 * 
	 * @param date
	 * @return
	 */

	public static String date2Time(Date date) {
		String time = sqlDateFormat.format(date);
		return time;
	}

	/**
	 * ������ת��Ϊ ������ ��ʽ�ַ�
	 * 
	 * @param date
	 * @return
	 */
	public static String date2String(Date date) {
		String datestring = null;
		if (date == null) {
			datestring = "";
		} else {
			datestring = dateFormater2.format(date);
		}
		return datestring;
	}

	/**
	 * ���Ѻõķ�ʽ��ʾʱ��
	 * 
	 * @param sdate
	 * @return
	 */
	public static String friendly_time(String sdate) {
		Date time = toDate(sdate);
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// �ж��Ƿ���ͬһ��
		String curDate = dateFormater2.format(cal.getTime());
		String paramDate = dateFormater2.format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "����ǰ";
			else
				ftime = hour + "Сʱǰ";
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "����ǰ";
			else
				ftime = hour + "Сʱǰ";
		} else if (days == 1) {
			ftime = "����";
		} else if (days == 2) {
			ftime = "ǰ��";
		} else if (days > 2 && days <= 10) {
			ftime = days + "��ǰ";
		} else if (days > 10) {
			ftime = dateFormater2.format(time);
		}
		return ftime;
	}

	/**
	 * �жϸ��ַ�ʱ���Ƿ�Ϊ����
	 * 
	 * @param sdate
	 * @return boolean
	 */
	public static boolean isToday(String sdate) {
		boolean b = false;
		Date time = toDate(sdate);
		Date today = new Date();
		if (time != null) {
			String nowDate = dateFormater2.format(today);
			String timeDate = dateFormater2.format(time);
			if (nowDate.equals(timeDate)) {
				b = true;
			}
		}
		return b;
	}

	/**
	 * �жϸ��ַ��Ƿ�հ״��� �հ״���ָ�ɿո��Ʊ��س����з���ɵ��ַ� �������ַ�Ϊnull����ַ�����true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	/**
	 * �ж��ǲ���һ���Ϸ��ĵ����ʼ���ַ
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return emailer.matcher(email).matches();
	}

	/**
	 * �ַ�ת����
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * ����ת����
	 * 
	 * @param obj
	 * @return ת���쳣���� 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * ����ת����
	 * 
	 * @param obj
	 * @return ת���쳣���� 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * �ַ�ת����ֵ
	 * 
	 * @param b
	 * @return ת���쳣���� false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * ��ʽ��soap��������date+time���
	 * 
	 * @param soapDateTime
	 * @return
	 */
	public static String formatSoapDateTime(String soapDateTime) {
		String returnString = soapDateTime.substring(0, 19).replace("T", " ");
		return returnString;
	}

	/**
	 * 
	 * @param anyType
	 * @return
	 */
	public static String formatSoapNullString(String anyType) {
		String returnString = anyType.equals("anyType{}") ? "" : anyType;
		return returnString;
	}

	/**
	 * ������������ʽ�Ƿ���ȷ
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmailInput(String email) {
		p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
		m = p.matcher(email);
		return m.matches();
	}

	/**
	 * ###����˺��Ƿ��� ���֡���ĸ���»��� ��϶�� ���򷵻�true�����򷵻�false## �˺ſ�Ϊ�����ַ��������
	 * 
	 * @param username
	 * @return
	 */
	public static boolean checkUsernameInput(String username) {
		// p = Pattern.compile("^\\w+$");
		// m = p.matcher(username);
		return 1 > 0;
	}

	/**
	 * �����������������Ƿ�һ��
	 * 
	 * @param password
	 * @param password2
	 * @return
	 */
	public static boolean check2Password(String password, String password2) {
		return password.equals(password2);
	}

	/**
	 * ����û�id����ʱ�����
	 * 
	 * @return
	 */
	public static String date2UserId() {
		String time = dateFormatUserId.format(new Date());
		return time;
	}

	/**
	 * ��ʽ����ɵ�ǰʱ��
	 * 
	 * @return
	 */
	public static Date genCurrentDate() {
		Date date = null;
		try {
			String now = dateFormater.format(new Date());
			// DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = dateFormater.parse(now);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

    /**
     * ��ʽ��boolean�ַ�
     * 
     * @param booleanStr
     * @return
     */
    public static boolean formatBoolean(String booleanStr) {
        if ("true".equalsIgnoreCase(booleanStr)) {
            return true;
        } else {
            return false;
        }
    }
}
