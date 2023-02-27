package si.ds.sr.demo_app.server.api;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/liveMatches")
public class LiveMatchesApi {

	@GET
	@Produces({ "application/json" })
	public Response getLiveMatches(@QueryParam("team") String team) {
		
		return Response.ok().entity("magic!").build();
	}
}
