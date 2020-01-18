package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import model.feature.Feature;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Earthquake {

	@JsonProperty("features")
	private List<Feature> features;

}
