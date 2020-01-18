package validator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationResult {
	private ValidationType type;
	private String message;

	private ValidationResult(ValidationType type) {
		this.type = type;
	}

	public static ValidationResult ok() {
		return new ValidationResult(ValidationType.OK);
	}

}
