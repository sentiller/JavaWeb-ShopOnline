package com.shop.entity;

public class Buy {
	private String type;
	private String customer;
	private String goodsname;
	private String time;

	public Buy(String type, String customer, String goodsname, String time) {
		super();
		this.type = type;
		this.customer = customer;
		this.goodsname = goodsname;
		this.time = time;
	}

	public Buy() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Buy [type=" + type + ", customer=" + customer + ", goodsname=" + goodsname + ", time=" + time + "]";
	}

}
