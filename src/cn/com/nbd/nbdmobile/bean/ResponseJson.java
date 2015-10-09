package cn.com.nbd.nbdmobile.bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 返回data为对象
 *
 * @param <T>
 * @author hjh
 *         2015-1-8上午4:10:54
 */
public final class ResponseJson<T> implements Serializable {

    @Expose
    private int status;//״̬

    @Expose
    private String error;

    @Expose
    private T content;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getError() {

        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static ResponseJson fromJson(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(ResponseJson.class, clazz);
        return gson.fromJson(json, objectType);
    }


    /**
     * 只导出注解
     *
     * @param json
     * @param clazz
     * @param expose
     * @return
     */
    public static ResponseJson fromJson(String json, Class clazz, boolean expose) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Type objectType = type(ResponseJson.class, clazz);
        return gson.fromJson(json, objectType);
    }

    public String toJson(Class<T> clazz) {
        Gson gson = new Gson();
        Type objectType = type(ResponseJson.class, clazz);
        return gson.toJson(this, objectType);
    }

    private static ParameterizedType type(final Class raw, final Type... args) {
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
