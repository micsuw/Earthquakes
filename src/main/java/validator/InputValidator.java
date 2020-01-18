package validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import validator.model.ValidationResult;
import validator.model.ValidationType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputValidator {

	public static ValidationResult validateInput(final String input) {
		String[] coords = input.split(" ");
		if (coords.length != 2) {
			return new ValidationResult(ValidationType.ERROR, "Please provide two values separated with space");
		}
		float longitude = Float.parseFloat(coords[0]);
		float latitude = Float.parseFloat(coords[1]);
		if (longitude < -180 || longitude > 180) {
			return new ValidationResult(ValidationType.ERROR, "Longitude has to be within range [-180,180]");
		}
		if (latitude < -90 || latitude > 90) {
			return new ValidationResult(ValidationType.ERROR, "Latitude has to be within range [-90,90]");
		}

		return ValidationResult.ok();
	}

}
