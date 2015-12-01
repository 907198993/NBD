package cn.com.nbd.nbdmobile.bean;

import java.io.Serializable;

/**
 * Created by Dell on 2015/12/1.
 * 滚动标签
 */
public class RollLabel implements Serializable {

	private int child_id;

	private String  child_label;


	public int getChild_id() {
		return child_id;
	}

	public void setChild_id(int child_id) {
		this.child_id = child_id;
	}

	public String getChild_label() {
		return child_label;
	}

	public void setChild_label(String child_label) {
		this.child_label = child_label;
	}
}
