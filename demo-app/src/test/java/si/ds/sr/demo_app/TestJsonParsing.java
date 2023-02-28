package si.ds.sr.demo_app;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import si.ds.sr.demo_app.model.Match;
import si.ds.sr.demo_app.utils.JsonUtil;

public class TestJsonParsing {


	@Test
	public void testJsonParsing() {
		
		String json = "[{\"id\":1,\"tournamentId\":2,\"start_time\":\"2022-02-06T03:10:38Z\",\"status\":\"COMPLETED\",\"home_team\":\"SacramentoKings\",\"away_team\":\"OklahomaCityThunder\",\"home_score\":\"113\",\"away_score\":\"103\"},{\"id\":2,\"tournamentId\":2,\"start_time\":\"2022-02-06T03:11:26Z\",\"status\":\"COMPLETED\",\"home_team\":\"PortlandTrailBlazers\",\"away_team\":\"MilwaukeeBucks\",\"home_score\":\"108\",\"away_score\":\"137\"},{\"id\":3,\"tournamentId\":2,\"start_time\":\"2023-04-06T20:41:04Z\",\"status\":\"SCHEDULED\",\"home_team\":\"DenverNuggets\",\"away_team\":\"BrooklynNets\"},{\"id\":4,\"tournamentId\":5,\"start_time\":\"2023-02-06T20:41:47Z\",\"status\":\"Live\",\"home_team\":\"DetroitRedwings\",\"away_team\":\"LosAngelesKings\",\"home_score\":\"1\",\"away_score\":\"1\"},{\"id\":5,\"tournamentId\":5,\"start_time\":\"2022-02-06T20:42:13Z\",\"status\":\"COMPLETED\",\"home_team\":\"ChicagoBlackhawks\",\"away_team\":\"PhiladelphiaFlyers\",\"home_score\":\"3\",\"away_score\":\"5\"},{\"id\":6,\"tournamentId\":6,\"start_time\":\"2022-02-08T20:42:13Z\",\"status\":\"COMPLETED\",\"home_team\":\"LosAngelesKings\",\"away_team\":\"ChicagoBlackhawks\",\"home_score\":\"2\",\"away_score\":\"3\"}]";
		              

		try {
			List<Match> matches = JsonUtil.json2matches(json);
			String deserializedJson = JsonUtil.matches2json(matches);
			
			assertTrue(json.equalsIgnoreCase(deserializedJson));
			
		} catch (JsonProcessingException e) {
			
		}
	}
	
	@Test
	public void testJsonParsingException() {
		
		/* Leading bracket is missing */
		String json = "{\"id\":1,\"tournamentId\":2,\"start_time\":\"2022-02-06T03:10:38Z\",\"status\":\"COMPLETED\",\"home_team\":\"SacramentoKings\",\"away_team\":\"OklahomaCityThunder\",\"home_score\":\"113\",\"away_score\":\"103\"},{\"id\":2,\"tournamentId\":2,\"start_time\":\"2022-02-06T03:11:26Z\",\"status\":\"COMPLETED\",\"home_team\":\"PortlandTrailBlazers\",\"away_team\":\"MilwaukeeBucks\",\"home_score\":\"108\",\"away_score\":\"137\"},{\"id\":3,\"tournamentId\":2,\"start_time\":\"2023-04-06T20:41:04Z\",\"status\":\"SCHEDULED\",\"home_team\":\"DenverNuggets\",\"away_team\":\"BrooklynNets\"},{\"id\":4,\"tournamentId\":5,\"start_time\":\"2023-02-06T20:41:47Z\",\"status\":\"Live\",\"home_team\":\"DetroitRedwings\",\"away_team\":\"LosAngelesKings\",\"home_score\":\"1\",\"away_score\":\"1\"},{\"id\":5,\"tournamentId\":5,\"start_time\":\"2022-02-06T20:42:13Z\",\"status\":\"COMPLETED\",\"home_team\":\"ChicagoBlackhawks\",\"away_team\":\"PhiladelphiaFlyers\",\"home_score\":\"3\",\"away_score\":\"5\"},{\"id\":6,\"tournamentId\":6,\"start_time\":\"2022-02-08T20:42:13Z\",\"status\":\"COMPLETED\",\"home_team\":\"LosAngelesKings\",\"away_team\":\"ChicagoBlackhawks\",\"home_score\":\"2\",\"away_score\":\"3\"}]";

		assertThrows(JsonProcessingException.class, () -> {
			JsonUtil.json2matches(json);
	    });
	}
	
	

}
