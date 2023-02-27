package si.ds.sr.demo_app;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import si.ds.sr.demo_app.client.HttpClient;
import si.ds.sr.demo_app.model.Match;
import si.ds.sr.demo_app.server.RestApplication;
import si.ds.sr.demo_app.utils.JsonUtil;
import si.ds.sr.demo_app.utils.MatchesUtils;

public class TestMatchFiltering {

	public List<Match> matches;
	
	@BeforeAll
	public void setUp() throws Exception {
		
		HttpClient httpClient = new HttpClient(RestApplication.MATCHES_ENDPOINT);
		String appResponse = httpClient.fetchData("/match/all");
		matches = JsonUtil.json2matches(appResponse);
	}

	@Test
	public void testFilterByTeam() {
		
		String team = "Portland";
		matches = MatchesUtils.filterByTeam(matches, team);
		
		for (Match match : matches) {			
			assertTrue(match.getHomeTeam().contains(team) || match.getAwayTeam().contains(team));
		}
	}
	
	@Test
	public void testFilterByCompleted() {
		
		matches = MatchesUtils.filterCompletedOnly(matches);		
		for (Match match : matches) {			
			assertTrue(match.hasCompleted());
		}
	}
	
	@Test
	public void testFilterByLiveOnly() {
		
		matches = MatchesUtils.filterLiveOnly(matches);		
		for (Match match : matches) {			
			assertTrue(match.isLive());
		}
	}

}
