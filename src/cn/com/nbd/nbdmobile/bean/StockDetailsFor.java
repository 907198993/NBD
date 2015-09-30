package cn.com.nbd.nbdmobile.bean;

import java.io.Serializable;

public class StockDetailsFor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 761096887908558288L;
  
	
	private StockDetailForDetail  shanghai ;
	private StockDetailForDetail  shenzhen ;
	private StockDetailForDetail  HSI ;
	public StockDetailForDetail getShanghai() {
		return shanghai;
	}
	public void setShanghai(StockDetailForDetail shanghai) {
		this.shanghai = shanghai;
	}
	public StockDetailForDetail getShenzhen() {
		return shenzhen;
	}
	public void setShenzhen(StockDetailForDetail shenzhen) {
		this.shenzhen = shenzhen;
	}
	public StockDetailForDetail getHSI() {
		return HSI;
	}
	public void setHSI(StockDetailForDetail hSI) {
		HSI = hSI;
	}
	
}
