package model.feature;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature {

	@JsonProperty("properties")
	private Properties properties;

	@JsonProperty("geometry")
	private Geometry geometry;

	@JsonProperty("id")
	private String id;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Feature)) return false;
		Feature feature = (Feature) o;
		return geometry.equals(feature.geometry);
	}

	@Override
	public int hashCode() {
		return Objects.hash(geometry);
	}

}
