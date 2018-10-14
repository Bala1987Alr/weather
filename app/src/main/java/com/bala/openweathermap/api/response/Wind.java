package com.bala.openweathermap.api.response;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Wind extends RealmObject {

	@SerializedName("deg")
	private int deg;

	@SerializedName("speed")
	private double speed;

	public void setDeg(int deg){
		this.deg = deg;
	}

	public int getDeg(){
		return deg;
	}

	public void setSpeed(double speed){
		this.speed = speed;
	}

	public double getSpeed(){
		return speed;
	}
}