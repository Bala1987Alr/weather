package com.bala.openweathermap.api.response;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Clouds extends RealmObject {

	@SerializedName("all")
	private int all;

	public void setAll(int all){
		this.all = all;
	}

	public int getAll(){
		return all;
	}
}