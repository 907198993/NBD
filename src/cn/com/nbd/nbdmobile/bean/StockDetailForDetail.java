package cn.com.nbd.nbdmobile.bean;

import java.io.Serializable;

public class StockDetailForDetail  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5609172189939844540L;
   
	private String  name;
	private  float curdot;
	private float curprice;
	private float rate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getCurprice() {
		return curprice;
	}
	public float getCurdot() {
		return curdot;
	}
	public void setCurdot(float curdot) {
		this.curdot = curdot;
	}
	public void setCurprice(float curprice) {
		this.curprice = curprice;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}

}
