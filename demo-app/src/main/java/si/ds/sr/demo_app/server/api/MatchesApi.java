package si.ds.sr.demo_app.server.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
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
import si.ds.sr.demo_app.model.MatchInfo;
import si.ds.sr.demo_app.server.RestApplication;
import si.ds.sr.demo_app.utils.DataCache;
import si.ds.sr.demo_app.utils.JsonUtil;
import si.ds.sr.demo_app.utils.MatchesUtils;

@Path("/matches")
public class MatchesApi {
	
	private static final Logger log = LogManager.getLogger(MatchesApi.class);

	@GET
	@Produces({ "application/json" })
	public Response getMatches(@QueryParam("team") String team) {
		
		log.info("getMatches...");
		
		String appResponse;
		try {

			/* Computes and loads the value into the cache if it doesn't already exist */
			appResponse = DataCache.getInstance().getUnchecked(DataCache.KEYS.MATCHES.name());
			List<Match> matches = JsonUtil.json2matches(appResponse);
			
			/* If there were any params passed.. */
			if(StringUtils.isNotBlank(team)){				
				matches = MatchesUtils.filterByTeam(matches, team);
			}
			
			List<MatchInfo> matchesInfo = MatchesUtils.convertMatches(matches);
			appResponse = JsonUtil.matchesInfo2json(matchesInfo);
		}
		catch (IOException e) {
			log.error("Error processing data: ", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
		
		return Response.ok().entity(appResponse).build();
	}
}
