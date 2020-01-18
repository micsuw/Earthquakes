package main;

import model.EarthquakeWithData;
import model.feature.Feature;
import model.feature.Point;
import provider.EarthquakeProvider;
import provider.FeatureProvider;
import validator.InputValidator;
import validator.model.ValidationResult;
import validator.model.ValidationType;

import java.util.List;
import java.util.Scanner;

public class Main {
	private static final String URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson";

	public static void main(String[] args) {
		FeatureProvider featureProvider = new FeatureProvider(URL);
		List<Feature> features = featureProvider.getFeatures();
		EarthquakeProvider earthquakeProvider = new EarthquakeProvider(features);
		final Scanner in = new Scanner(System.in);
		String input;

		ValidationResult validationResult;
		do {
			System.out.println("Please provide two coordinates, for example: 10 10");
			input = in.nextLine();
			validationResult = InputValidator.validateInput(input);
			if (validationResult.getMessage() != null) {
				System.out.println(validationResult.getMessage());
			}
		} while (ValidationType.ERROR.equals(validationResult.getType()));

		List<EarthquakeWithData> earthquakes = earthquakeProvider.getEarthquakesForPoint(parseInputToPoint(input));
		System.out.println(earthquakes);
	}


	private static Point parseInputToPoint(String input) {
		String[] coords = input.split(" ");
		return new Point(Float.valueOf(coords[0]), Float.valueOf(coords[1]));
	}

}
