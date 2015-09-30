package cn.com.nbd.nbdmobile.bean;

import java.io.Serializable;
import java.util.List;

public class StockDetails  implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3931353236009608164L;
  
	
	private StockDetailsFor  market;


	public StockDetailsFor getMarket() {
		return market;
	}


	public void setMarket(StockDetailsFor market) {
		this.market = market;
	}


	   
	
}
