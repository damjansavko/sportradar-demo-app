package si.ds.sr.demo_app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import si.ds.sr.demo_app.client.HttpClient;
import si.ds.sr.demo_app.model.Match;
import si.ds.sr.demo_app.model.Sport;
import si.ds.sr.demo_app.model.Tournament;
import si.ds.sr.demo_app.utils.JsonUtil;

/**
 * Class for testing purposes
 *
 */
public class App {
	
	private static final Logger log = LogManager.getLogger(App.class);
	
	public static void main(String[] args) {
				
		try {
			
			
			String endpointAddress = "https://h49q2nbrwe.execute-api.eu-central-1.amazonaws.com/HiringTest";

			HttpClient httpClient = new HttpClient(endpointAddress);

//			String response = httpClient.fetchData("/sport/all");
			String response = httpClient.fetchSports();
			log.debug(JsonUtil.prettyPrintRawJson(response));
			
			Sport[] sports = JsonUtil.parseSports(response);

			for (Sport sport : sports) {
				log.debug(sport.getName());
			}

//			String response2 = httpClient.fetchData("/tournament/all");
			String response2 = httpClient.fetchTournaments();
			log.debug(JsonUtil.prettyPrintRawJson(response2));
			
			Tournament[] tournaments = JsonUtil.parseTournaments(response2);

			for (Tournament tournament : tournaments) {
				log.debug(tournament.getName());
			}

//			String response3 = httpClient.fetchData("/match/all");
			String response3 = httpClient.fetchMatches();
			log.debug(JsonUtil.prettyPrintRawJson(response3));
			
			Match[] matches = JsonUtil.parseMatches(response3);

			for (Match match : matches) {
				log.info(match.getHomeTeam() + " : " + match.getAwayTeam());
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
