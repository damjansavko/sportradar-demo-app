package si.ds.sr.demo_app.server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {

	/**
	 * The source API where we retrieve data about matches
	 */
	public static String MATCHES_ENDPOINT = "https://h49q2nbrwe.execute-api.eu-central-1.amazonaws.com/HiringTest";
}
