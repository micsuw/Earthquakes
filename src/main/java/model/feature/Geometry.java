package model.feature;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {

	@Getter
	@JsonProperty("type")
	private String type;

	@JsonProperty("coordinates")
	private List<Float> coordinates;

	public Point getPoint() {
		return new Point(coordinates.get(0), coordinates.get(1));
	}

}
