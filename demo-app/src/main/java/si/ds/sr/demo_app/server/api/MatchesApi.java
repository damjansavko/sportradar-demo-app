package si.ds.sr.demo_app.server.api;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import si.ds.sr.demo_app.App;
import si.ds.sr.demo_app.client.HttpClient;
import si.ds.sr.demo_app.client.model.Match;
import si.ds.sr.demo_app.client.utils.Parser;

@Path("/matches")
public class MatchesApi {
	
	private static final Logger log = LogManager.getLogger(MatchesApi.class);

	@GET
	@Produces({ "application/json" })
	public Response getMatches(@QueryParam("team") String team) {
		
		return Response.ok().entity("magic!").build();
	}
}
