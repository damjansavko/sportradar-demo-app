package si.ds.sr.demo_app.server;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import si.ds.sr.demo_app.server.api.MatchesApi;

@ApplicationPath("/api")
public class RestApplication extends Application {

	
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(MatchesApi.class);
        return classes;
    }
}
