package provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Earthquake;
import model.enums.PropertyType;
import model.feature.Feature;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class FeatureProvider {

	private URL url;

	public FeatureProvider(String url) {
		this.url = getURL(url);
	}

	private URL getURL(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			throw new AssertionError("Could not read from url!");
		}
	}

	/**
	 * Returns features that are earthquakes
	 *
	 * @return earthquake features
	 */
	public List<Feature> getFeatures() {

		Earthquake earthQuake = getEarthquake();

		return earthQuake.getFeatures()
				.stream()
				.filter(feature -> PropertyType.EARTHQUAKE.getType().equals(feature.getProperties().getType()))
				.collect(Collectors.toList());
	}

	private Earthquake getEarthquake() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(url, Earthquake.class);
		} catch (IOException e) {
			throw new AssertionError("Could not parse data to object!");
		}
	}
}
