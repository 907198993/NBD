package cn.com.nbd.nbdmobile.util;

import java.lang.ref.SoftReference;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 全局的数据管理对象
 * 
 * @author Tank
 * @version 0.4 改用{@link ConcurrentHashMap}
 * @version 0.3 添加清除数据方法
 * @version 0.2 改用{@link SoftReference}来引用缓存数据
 * @version 0.1
 */
public class AppDataManager {
	private static AppDataManager mAppDataManager;

	private ConcurrentHashMap<String, SoftReference<Object>> mDataMap;

	private AppDataManager() {
		mDataMap = new ConcurrentHashMap<String, SoftReference<Object>>();
	}

	public static AppDataManager getInstance() {
		if (mAppDataManager == null) {
			mAppDataManager = new AppDataManager();
		}
		return mAppDataManager;
	}

	/**
	 * 放入数据, 系统随机生成UUID作为标签
	 * 
	 * @param data
	 * @return 生成的标签
	 */
	public String addData(Object data) {
		String containTag = getTag(data);
		if (null != containTag) {
			return containTag;
		}
		String uuidTag = UUID.randomUUID().toString();
		addData(data, uuidTag);
		return uuidTag;
	}

	/**
	 * 自定义标签放入数据
	 * 
	 * @param data
	 * @param customizedTag
	 */
	public void addData(Object data, String customizedTag) {
		if (null == data || null == customizedTag) {
			return;
		}

		mDataMap.put(customizedTag, new SoftReference<Object>(data));
	}

	/**
	 * 根据指定的标签返回数据, 自动维护数据Map
	 * 
	 * @param CustomizedTag
	 * @return
	 */
	public Object getData(String tag) {
		if (null == tag) {
			return null;
		}
		SoftReference<Object> dataReference = mDataMap.get(tag);

		// 没有找到指定标签对应的数据
		if (null == dataReference) {
			return null;
		}

		Object data = dataReference.get();

		// 找到指定标签对应的数据, 单式数据已经被GC回收了, 移除该标签
		if (null == data) {
			mDataMap.remove(tag);
			return null;
		}

		return data;
	}

	/**
	 * 检测是否包含某个标签和对应的数据
	 * 
	 * @param tag
	 * @return
	 */
	public boolean hasData(String tag) {
		return mDataMap.containsKey(tag);
	}

	/**
	 * 清除所有数据
	 */
	public void clear() {
		mDataMap.clear();
	}

	/**
	 * 根据数据获取标签
	 * 
	 * @param data
	 * @return
	 */
	public String getTag(Object data) {
		if (data == null) {
			return null;
		}

		for (Entry<String, SoftReference<Object>> entry : mDataMap.entrySet()) {
			Object reference = entry.getValue().get();
			if (reference != null && reference == data) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * 移除数据
	 * 
	 * @param tag
	 */
	public void removeData(String tag) {
		mDataMap.remove(tag);
	}
}
