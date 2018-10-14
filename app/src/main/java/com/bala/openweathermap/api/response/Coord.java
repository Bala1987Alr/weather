package com.bala.openweathermap.api.response;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Coord extends RealmObject {

	@SerializedName("lon")
	private double lon;

	@SerializedName("lat")
	private double lat;

	public void setLon(double lon){
		this.lon = lon;
	}

	public double getLon(){
		return lon;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}
}