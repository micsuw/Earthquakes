package model.feature;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {

	@JsonProperty("type")
	private String type;

	@JsonProperty("title")
	private String title;

	@JsonProperty("mag")
	private Double magnitude;

	@JsonProperty("place")
	private String place;

}
