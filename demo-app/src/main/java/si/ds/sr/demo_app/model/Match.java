package si.ds.sr.demo_app.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The Match data entity
 * 
 * @author Damjan Šavko
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "tournamentId", "start_time", "status", "home_team", "away_team", "home_score","away_score" })
public class Match {
		
	public enum STATUS{ COMPLETED, SCHEDULED, LIVE} 

	@JsonProperty("id")
	private Integer id;
	@JsonProperty("tournamentId")
	private Integer tournamentId;
	@JsonProperty("start_time")
	private String startTime;
	@JsonProperty("status")
	private String status;
	@JsonProperty("home_team")
	private String homeTeam;
	@JsonProperty("away_team")
	private String awayTeam;
	@JsonProperty("home_score")
	private String homeScore;
	@JsonProperty("away_score")
	private String awayScore;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("tournamentId")
	public Integer getTournamentId() {
		return tournamentId;
	}

	@JsonProperty("tournamentId")
	public void setTournamentId(Integer tournamentId) {
		this.tournamentId = tournamentId;
	}

	@JsonProperty("start_time")
	public String getStartTime() {
		return startTime;
	}

	@JsonProperty("start_time")
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Is the match live
	 * @return
	 */
	@JsonIgnore
	public boolean isLive() {
		return STATUS.LIVE.name().equalsIgnoreCase(this.status);
	}
	
	/**
	 * Has the match finished/completed.
	 * @return
	 */
	@JsonIgnore
	public boolean hasCompleted() {
		return STATUS.COMPLETED.name().equalsIgnoreCase(this.status);
	}

	@JsonProperty("home_team")
	public String getHomeTeam() {
		return homeTeam;
	}

	@JsonProperty("home_team")
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	@JsonProperty("away_team")
	public String getAwayTeam() {
		return awayTeam;
	}

	@JsonProperty("away_team")
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	@JsonProperty("home_score")
	public String getHomeScore() {
		return homeScore;
	}

	@JsonProperty("home_score")
	public void setHomeScore(String homeScore) {
		this.homeScore = homeScore;
	}

	@JsonProperty("away_score")
	public String getAwayScore() {
		return awayScore;
	}

	@JsonProperty("away_score")
	public void setAwayScore(String awayScore) {
		this.awayScore = awayScore;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	

}
