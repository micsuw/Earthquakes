package controller;

import model.EarthquakeWithData;
import model.feature.Feature;
import model.feature.Geometry;
import model.feature.Point;
import model.feature.Properties;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import provider.EarthquakeProvider;
import provider.FeatureProvider;

import java.util.List;

public class EarthquakeProviderTest {

	private EarthquakeProvider earthquakeProvider;

	@Before
	public void initialize() {
		FeatureProvider featureProvider = new FeatureProvider("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson");
		List<Feature> features = featureProvider.getFeatures();
		earthquakeProvider = new EarthquakeProvider(features);
	}

	@Test
	public void shouldRemoveDuplicates() {
		Feature radom1 = createFeature("Germany", "Berlin", List.of(21.14F, 51.40F));
		Feature radom2 = createFeature("Poland", "Radom", List.of(21.14F, 51.40F));

		EarthquakeProvider earthquakeProvider = new EarthquakeProvider(List.of(radom1, radom2));
		List<EarthquakeWithData> earthquakes = earthquakeProvider.getEarthquakesForPoint(new Point(25F, 55F));

		Assertions.assertThat(earthquakes.size()).isEqualTo(1);
	}

	@Test
	public void getEarthquakesForPositivePoint() {
		Point point = new Point(10F, 10F);
		List<EarthquakeWithData> earthquakes = earthquakeProvider.getEarthquakesForPoint(point);

		Assertions.assertThat(earthquakes.size()).isEqualTo(10);
	}

	@Test
	public void getEarthquakesForNegativePoint() {
		Point point = new Point(-170F, 80F);
		List<EarthquakeWithData> earthquakes = earthquakeProvider.getEarthquakesForPoint(point);

		Assertions.assertThat(earthquakes.size()).isEqualTo(10);
	}

	private Feature createFeature(String title, String place, List<Float> coordinates) {
		return Feature.builder()
				.properties(Properties.builder()
						.title(title)
						.place(place)
						.build())
				.geometry(Geometry.builder()
						.coordinates(coordinates)
						.build())
				.build();
	}

}