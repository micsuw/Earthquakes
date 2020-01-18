package model;

import lombok.Getter;

public class EarthquakeWithData {

	private Integer distance;
	@Getter
	private String description;

	public EarthquakeWithData(Integer distance, String description) {
		this.distance = distance;
		this.description = description;
	}

	@Override
	public String toString() {
		return description + " || " + distance + "\n";
	}

}
