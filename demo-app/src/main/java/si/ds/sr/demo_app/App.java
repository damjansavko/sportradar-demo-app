package si.ds.sr.demo_app;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import si.ds.sr.demo_app.client.HttpClient;
import si.ds.sr.demo_app.model.Match;
import si.ds.sr.demo_app.model.Sport;
import si.ds.sr.demo_app.model.Tournament;
import si.ds.sr.demo_app.model.utils.Parser;

/**
 * Hello world!
 *
 */
public class App {
	
	private static final Logger log = LogManager.getLogger(App.class);
	
	public static void main(String[] args) {
		
//		System.out.println("Test 1");
//		log.info("Test loging 2");
//		log.debug("Test loging  3");
//		log.warn("Test loging  4");
//		log.error("Test loging 5");
//		System.exit(0);
		
		try {
			String endpointAddress = "https://h49q2nbrwe.execute-api.eu-central-1.amazonaws.com/HiringTest";

			HttpClient httpClient = new HttpClient(endpointAddress);

			String response = httpClient.fetchData("/sport/all");
			log.debug(Parser.prettyPrintRawJson(response));
			
			Sport[] sports = Parser.parseSports(response);

			for (Sport sport : sports) {
				log.debug(sport.getName());
			}

			String response2 = httpClient.fetchData("/tournament/all");
			log.debug(Parser.prettyPrintRawJson(response2));
			
			Tournament[] tournaments = Parser.parseTournaments(response2);

			for (Tournament tournament : tournaments) {
				log.debug(tournament.getName());
			}

			String response3 = httpClient.fetchData("/match/all");
			log.debug(Parser.prettyPrintRawJson(response3));
			
			Match[] matches = Parser.parseMatches(response3);

			for (Match match : matches) {
				log.info(match.getHomeTeam() + " : " + match.getAwayTeam());
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
