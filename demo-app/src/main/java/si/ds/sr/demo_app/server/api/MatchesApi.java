package si.ds.sr.demo_app.server.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import si.ds.sr.demo_app.client.HttpClient;
import si.ds.sr.demo_app.model.Match;
import si.ds.sr.demo_app.server.RestApplication;
import si.ds.sr.demo_app.utils.JsonUtil;

@Path("/matches")
public class MatchesApi {
	
	private static final Logger log = LogManager.getLogger(MatchesApi.class);

	@GET
	@Produces({ "application/json" })
	public Response getMatches(@QueryParam("team") String team) {
		
		log.info("getMatches...");
		
		String appResponse;
		try {
			
			HttpClient httpClient = new HttpClient(RestApplication.MATCHES_ENDPOINT);
			appResponse = httpClient.fetchData("/match/all");
			
			/* If there were any params passed.. */
			if(StringUtils.isNotBlank(team)){
				Match[] matches = JsonUtil.parseMatches(appResponse);
				
				Stream<Match> streamOfMatches = Arrays.stream(matches);
				streamOfMatches = streamOfMatches.filter(m -> m.getHomeTeam().contains(team) || m.getAwayTeam().contains(team));
				
				appResponse = JsonUtil.matches2json(streamOfMatches.collect(Collectors.toList()));
			}
			
		}
		catch (IOException e) {
			log.error("Error processing data: ", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
		
		return Response.ok().entity(appResponse).build();
	}
}
