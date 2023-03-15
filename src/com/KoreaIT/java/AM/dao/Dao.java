package com.KoreaIT.java.AM.dao;

public abstract class Dao {
	protected int lastId;
	
	public abstract int getLastId();
	
	public abstract int setNewId();
}
