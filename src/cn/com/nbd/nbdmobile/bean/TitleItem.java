package cn.com.nbd.nbdmobile.bean;

import com.google.gson.annotations.Expose;

import org.hjh.annomation.ColumnInt;
import org.hjh.annomation.ColumnPrimaryKey;
import org.hjh.annomation.ColumnString;
import org.hjh.annomation.PrimaryKeyType;
import org.hjh.annomation.Table;

import java.io.Serializable;

@Table(TableName = "TitleItem")
public final class TitleItem implements Serializable{


	@ColumnPrimaryKey(TYPE= PrimaryKeyType.DEFINE)
	@ColumnInt
    private  int idKey;

	@ColumnInt
	private int id;

    @ColumnString(length = 256)
    @Expose
	private  String name;

	@ColumnInt
	private int orderId;

	@ColumnInt
	private int selected;

	public TitleItem(int id, String name, int orderId, int selected) {
		this.id = id;
		this.name = name;
		this.orderId = orderId;
		this.selected = selected;
	}

	public int getIdKey() {
		return idKey;
	}

	public void setIdKey(int idKey) {
		this.idKey = idKey;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}
}
