package si.ds.sr.demo_app.server;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import si.ds.sr.demo_app.server.api.MatchesApi;
import si.ds.sr.demo_app.utils.DataCache;

@ApplicationPath("/api")
public class RestApplication extends Application {

	private static final Logger log = LogManager.getLogger(RestApplication.class);
	
	/**
	 * The source API where we retrieve data about matches
	 */
	public static String MATCHES_ENDPOINT = "https://h49q2nbrwe.execute-api.eu-central-1.amazonaws.com/HiringTest";
	
	/**
	 * Delay for starting the scheduler after app has been loaded (in seconds)
	 */
	public static int SCHEDULER_DELAY = 5;
	/**
	 * The period between successive executions of the scheduled job (in seconds)
	 */
	public static int SCHEDULER_PERIOD = 30;
	
	static {
		log.info("Initializing scheduler. Scheduled job to be executed on a " + SCHEDULER_PERIOD + " sec period");
		
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);		
		executorService.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				log.info("Refreshing cache values...");
				DataCache.refreshCacheValues();				
			}
		}, SCHEDULER_DELAY, SCHEDULER_PERIOD, TimeUnit.SECONDS);
	}
}
