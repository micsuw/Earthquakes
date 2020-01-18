package model.feature;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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

}
