package si.ds.sr.demo_app.server.api;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import si.ds.sr.demo_app.client.HttpClient;
import si.ds.sr.demo_app.model.Match;
import si.ds.sr.demo_app.server.RestApplication;
import si.ds.sr.demo_app.utils.JsonUtil;
import si.ds.sr.demo_app.utils.MatchesUtils;

@Path("/completedMatches")
public class CompletedMatchesApi {
	
	private static final Logger log = LogManager.getLogger(CompletedMatchesApi.class);
	
	@GET
	@Produces({ "application/json" })
	public Response getCompletedMatches(@QueryParam("team") String team) {
		
		log.info("getCompletedMatches...");
		
		String appResponse;
		try {
			
			HttpClient httpClient = new HttpClient(RestApplication.MATCHES_ENDPOINT);
			appResponse = httpClient.fetchData("/match/all");
			
			List<Match> matches = JsonUtil.json2matches(appResponse);
			matches = MatchesUtils.filterCompletedOnly(matches);				
			
			appResponse = JsonUtil.matches2json(matches);
			
		}
		catch (IOException e) {
			log.error("Error processing data: ", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
		
		return Response.ok().entity(appResponse).build();
	}
}
