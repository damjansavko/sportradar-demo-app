package si.ds.sr.demo_app.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import si.ds.sr.demo_app.model.Match;
import si.ds.sr.demo_app.model.Sport;
import si.ds.sr.demo_app.model.Tournament;
import si.ds.sr.demo_app.utils.Parser;


/**
 * A class for reading HTTP resources
 * 
 * @author Damjan Šavko
 *
 */
public class HttpClient {

	private static final Logger log = LogManager.getLogger(HttpClient.class);

	/**
	 * The URL addres of the target resource
	 */
	private String endpointAddress;

	/**
	 * 
	 * @param endpointAddress
	 */
	public HttpClient(String endpointAddress) {
		this.endpointAddress = endpointAddress;
	}

	
	/**
	 * Retrieve the data  from the endpoint address as a simple String
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public String fetchData(String resource) throws ClientProtocolException, IOException {
		
		if(endpointAddress == null) {
			throw new IllegalArgumentException("Endpoint address cannot be null");
		}
		
		String url = endpointAddress + (resource == null ? "" : resource);
		
		if(!validUrl(url)) {
			throw new IllegalArgumentException("URL not valid:"  + url);
		}

		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		
		log.debug("Calling endpoint address: " + url); 
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

		log.debug("Response code: {}", httpResponse.getStatusLine().getStatusCode());

		String responseString = new BasicResponseHandler().handleResponse(httpResponse);
		
		log.trace("Response: -> {}", responseString);
		
		return responseString;		
	}

	/**
	 * Test if the given parameter is a valid URL address
	 * 
	 * @param url
	 * @return
	 */
	private boolean validUrl(String url) {
		
		
		try {
			new URL(url).toURI();
			return true;
		} 
		catch (MalformedURLException e) {
			return false;
		}
		catch (URISyntaxException e) {
			return false;
		}
	}
		
}
