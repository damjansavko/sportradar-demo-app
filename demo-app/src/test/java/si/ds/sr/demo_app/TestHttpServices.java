package si.ds.sr.demo_app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import si.ds.sr.demo_app.client.HttpClient;
import si.ds.sr.demo_app.server.RestApplication;

class TestHttpServices {

	@Test
	void testSportsResource() {
		
		try {
			HttpClient httpClient = new HttpClient(RestApplication.MATCHES_ENDPOINT);
			httpClient.fetchSports();
		}
		catch (IOException e) {
			fail();
		}
	}
	
	@Test
	void testTournamentsResource() {
		
		try {
			HttpClient httpClient = new HttpClient(RestApplication.MATCHES_ENDPOINT);
			httpClient.fetchTournaments();
		}
		catch (IOException e) {
			fail();
		}
	}
	
	@Test
	void testMatchesResource() {
		
		try {
			HttpClient httpClient = new HttpClient(RestApplication.MATCHES_ENDPOINT);
			httpClient.fetchMatches();
		}
		catch (IOException e) {
			fail();
		}
	}

}
