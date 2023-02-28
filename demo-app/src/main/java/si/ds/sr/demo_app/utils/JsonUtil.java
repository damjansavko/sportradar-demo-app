package si.ds.sr.demo_app.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import si.ds.sr.demo_app.model.Match;
import si.ds.sr.demo_app.model.MatchInfo;
import si.ds.sr.demo_app.model.Sport;
import si.ds.sr.demo_app.model.Tournament;

/**
 * A class for reading strings representing json data
 * 
 * @author Damjan Šavko
 *
 */
public class JsonUtil {

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
	 * Reads a Json representing Sport entities and serializes it to an array of objects
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
	 * Reads a Json representing Sport entities and serializes it to a list of objects
	 * @param sportsAsJson
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static List<Sport> json2sports(String sportsAsJson) throws JsonMappingException, JsonProcessingException {

		return Arrays.asList(parseSports(sportsAsJson));
	}

	/**
	 * Reads a Json representing Tournament entities and serializes it to an array of objects
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
	
	/**
	 * Reads a Json representing Tournament entities and serializes it to a list of objects
	 * 
	 * @param matchesAsJson
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static List<Tournament> json2tournaments(String tournamentsAsJson) throws JsonMappingException, JsonProcessingException {
		return Arrays.asList(parseTournaments(tournamentsAsJson));
	}

	/**
	 * Reads a Json representing Match entities and serializes it to an array of objects
	 * 
	 * @param matchesAsJson
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static Match[] parseMatches(String matchesAsJson) throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(matchesAsJson, Match[].class);
	}
	
	/**
	 * Reads a Json representing Match entities and serializes it to a list of objects
	 * 
	 * @param matchesAsJson
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static List<Match> json2matches(String matchesAsJson) throws JsonMappingException, JsonProcessingException {
		return Arrays.asList(parseMatches(matchesAsJson));
	}
	
	/**
	 * Returns a stream of objects representing the input json
	 * 
	 * @param matchesAsJson
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static Stream<Match> json2stream(String matchesAsJson) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return Arrays.stream(mapper.readValue(matchesAsJson, Match[].class));
	}
	
	/**
	 * Deserializes objects to a Json string
	 * 
	 * @param matches
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String matches2json(List<Match> matches) throws JsonProcessingException{

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(matches);
	}
	
	/**
	 * Deserializes objects to a Json string
	 * 
	 * @param matches
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String matchesInfo2json(List<MatchInfo> matchesInfo) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(matchesInfo);
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
	
	/**
	 * Removes spaces from a pretty printed Json
	 * 
	 * @param json
	 * @return
	 */
	public static String prettyPrintRawJson(String json) {
		
		return json.replaceAll("\\s", "");
	}

	

}
