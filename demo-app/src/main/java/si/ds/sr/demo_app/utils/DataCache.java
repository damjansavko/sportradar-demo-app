package si.ds.sr.demo_app.utils;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import si.ds.sr.demo_app.client.HttpClient;
import si.ds.sr.demo_app.server.RestApplication;

/**
 * A singleton instance adapter for initialising a Guava Loading Cache
 * 
 * @author Damjan Šavko
 *
 */
public class DataCache {
	
	/* Cache eviction parameters */
	private static final long DURATION = 30;
	private static TimeUnit TIME_UNIT = TimeUnit.SECONDS;
	
	private static LoadingCache<String, String> INSTANCE;
	
	public static enum KEYS{MATCHES, SPORTS, TOURNAMENTS}
	
	public static synchronized LoadingCache<String, String> getInstance() {
		
		if(INSTANCE == null) {
			INSTANCE = CacheBuilder.newBuilder()
							.expireAfterAccess(DURATION, TIME_UNIT)
					  		.build(new CacheLoader<String, String>() {
							    @Override
							    public String load(final String key) throws Exception {
							      
							    	if(KEYS.MATCHES.name().equalsIgnoreCase(key)) {
							    		HttpClient httpClient = new HttpClient(RestApplication.MATCHES_ENDPOINT);
								    	return httpClient.fetchMatches();	
							    	}
							    	else if(KEYS.TOURNAMENTS.name().equalsIgnoreCase(key)) {
							    		HttpClient httpClient = new HttpClient(RestApplication.MATCHES_ENDPOINT);
								    	return httpClient.fetchTournaments();	
							    	}
							    	else if(KEYS.SPORTS.name().equalsIgnoreCase(key)) {
							    		HttpClient httpClient = new HttpClient(RestApplication.MATCHES_ENDPOINT);
								    	return httpClient.fetchSports();	
							    	}
							    	else{
							    		return null;
							    	}
							    	
							    }
							});
		}
		
		return INSTANCE;
	}
	
	/**
	 * Loads values into the cache if they are not already present
	 */
	public static void refreshCacheValues() {
		
		getInstance().getUnchecked(DataCache.KEYS.MATCHES.name());
		getInstance().getUnchecked(DataCache.KEYS.SPORTS.name());
		getInstance().getUnchecked(DataCache.KEYS.TOURNAMENTS.name());
	}

}
