package com.nagarro.remotelearning.classes;

public class Aircraft extends Vehicle {
	private int altitude;
	int numberOfPropellers;

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public int getNumberOfPropellers() {
		return numberOfPropellers;
	}

	public void setNumberOfPropellers(int numberOfPropellers) {
		this.numberOfPropellers = numberOfPropellers;
	}

}
