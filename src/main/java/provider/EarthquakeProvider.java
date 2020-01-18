package provider;

import model.EarthquakeWithData;
import model.feature.Feature;
import model.feature.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EarthquakeProvider {

	private List<Feature> features;

	public EarthquakeProvider(List<Feature> features) {
		this.features = features;
	}

	public List<EarthquakeWithData> getEarthquakesForPoint(final Point checkedPoint) {
		Map<Feature, Double> distances = new HashMap<>();

		features.forEach(feature -> distances.put(feature, distance(checkedPoint, feature.getGeometry().getPoint())));
		return distances.entrySet()
				.stream()
				.distinct()
				.sorted(Map.Entry.comparingByValue())
				.limit(10)
				.map(this::mapToEarthquake)
				.collect(Collectors.toList());
	}

	private EarthquakeWithData mapToEarthquake(Map.Entry<Feature, Double> featuresWithDistance) {
		return new EarthquakeWithData(BigDecimal.valueOf(featuresWithDistance.getValue()).setScale(0, RoundingMode.HALF_UP).intValueExact(),
				featuresWithDistance.getKey().getProperties().getTitle());
	}

	/**
	 * Calculate distance between two points in latitude and longitude
	 * Uses Haversine method as its base.
	 *
	 * @return Distance in kilometers
	 */
	private double distance(final Point first, final Point second) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(second.getLatitude() - first.getLatitude());
		double lonDistance = Math.toRadians(second.getLongitude() - first.getLongitude());
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(first.getLatitude())) * Math.cos(Math.toRadians(second.getLatitude()))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c;

		return Math.abs(distance);
	}

}
