package si.ds.sr.demo_app.model.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import si.ds.sr.demo_app.model.Match;
import si.ds.sr.demo_app.model.Sport;
import si.ds.sr.demo_app.model.Tournament;

/**
 * A class for reading strings representing json data
 * 
 * @author Damjan Šavko
 *
 */
public class Parser {

	public static void main(String[] args) {

		try {

			String json = "{id: 1, name: \"Football\"}";
			System.out.println("JSON without field names quoted - " + json);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES.mappedFeature());
			JsonNode jsonNode = objectMapper.readTree(json);
			String jsonStringWithQuotes = objectMapper.writeValueAsString(jsonNode);
			System.out.println("JSON with field names quoted - " + jsonStringWithQuotes);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param sportsAsJson
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static Sport[] parseSports(String sportsAsJson) throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(sportsAsJson, Sport[].class);
	}

	/**
	 * 
	 * @param tournamentsAsJson
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static Tournament[] parseTournaments(String tournamentsAsJson)
			throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(tournamentsAsJson, Tournament[].class);
	}

	public static Match[] parseMatches(String matchesAsJson) throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(matchesAsJson, Match[].class);
	}

	/**
	 * Reads JSON values where the field names are not quoted and generates a JSON
	 * Object where field names are quoted.
	 * 
	 * @param unquotedJson
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static String convertUnquotedJson2Json(String unquotedJson)
			throws JsonMappingException, JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES.mappedFeature());
		JsonNode jsonNode = objectMapper.readTree(unquotedJson);
		return objectMapper.writeValueAsString(jsonNode);
	}
	
	public static String prettyPrintRawJson(String json) {
		
		return json.replaceAll("\\s", "");
	}

}