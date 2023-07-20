package com.nagarro.remotelearning.classes;

public class Train {
	int trainNumber;
	String trainType;
	int numberOfWagons;

	public Train(int trainNumber, String trainType, int numberOfWagons) {
		this.trainNumber = trainNumber;
		this.trainType = trainType;
		this.numberOfWagons = numberOfWagons;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Train)) {
			return false;
		}
		Train train = (Train) obj;

		return train.trainNumber == this.trainNumber;
	}

	public int getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public int getNumberOfWagons() {
		return numberOfWagons;
	}

	public void setNumberOfWagons(int numberOfWagons) {
		this.numberOfWagons = numberOfWagons;
	}
}