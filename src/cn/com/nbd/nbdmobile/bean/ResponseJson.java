package cn.com.nbd.nbdmobile.bean;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * 返回data为对象
 * @author hjh
 * 2015-1-8上午4:10:54
 * @param <T>
 */
public final class ResponseJson<T> implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6314495933300154521L;

	@Expose
	private int status;//״̬
	
	@Expose
	private String info;
	
	@Expose
	private  T data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public static  ResponseJson  fromJson(String json, Class  clazz) {
        Gson gson = new Gson();
        Type objectType = type(ResponseJson.class, clazz);
        return gson.fromJson(json, objectType);
    }
	
	
	/**
	 * 只导出注解
	 * @param json
	 * @param clazz
	 * @param expose
	 * @return
	 */
	public static ResponseJson fromJson(String json, Class clazz,boolean expose){
		 Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	        Type objectType = type(ResponseJson.class, clazz);
	        return gson.fromJson(json, objectType);
	}

    public String toJson(Class<T> clazz) {
        Gson gson = new Gson();
        Type objectType = type(ResponseJson.class, clazz);
        return gson.toJson(this, objectType);
    }

    private static ParameterizedType type( final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
