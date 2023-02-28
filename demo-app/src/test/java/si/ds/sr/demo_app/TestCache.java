package si.ds.sr.demo_app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.cache.CacheLoader.InvalidCacheLoadException;

import si.ds.sr.demo_app.utils.DataCache;
import si.ds.sr.demo_app.utils.JsonUtil;

class TestCache {

	@Test
	void testCacheEmpty() {		
		assertTrue(DataCache.getInstance().size() == 0);
	}
	
	@Test
	void testCacheNullForUnknownValues() {				
		assertThrows(InvalidCacheLoadException.class, () -> {
			DataCache.getInstance().getUnchecked("unknown-key");
	    });		
	}
	
	@Test
	void testCacheLoader() {
		
		String matches = DataCache.getInstance().getUnchecked(DataCache.KEYS.MATCHES.name());
		assertNotNull(matches);		
		assertTrue(DataCache.getInstance().size() == 1);
	}
	
}
