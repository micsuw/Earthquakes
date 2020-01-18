package model.feature;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Point {

	private Float longitude;
	private Float latitude;

	public Point(Float longitude, Float latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	/**
	 * Negative values for western longitudes.
	 *
	 * @return longitude in decimal degrees within range [-180.0, 180.0]
	 */
	public Float getLongitude() {
		return longitude;
	}

	/**
	 * Negative values for southern latitudes.
	 *
	 * @return latitude in decimal degrees within range range [-90.0, 90.0]
	 */
	public Float getLatitude() {
		return latitude;
	}

}
