package com.shop.entity;

public class Goods {
	private String name;
	private Integer store;
	private Double price;

	public Goods() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Goods(String name, Integer store, Double price) {
		super();
		this.name = name;
		this.store = store;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Goods [name=" + name + ", store=" + store + ", price=" + price + "]";
	}

}
