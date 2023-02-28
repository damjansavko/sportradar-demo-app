package si.ds.sr.demo_app.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import si.ds.sr.demo_app.model.Match;
import si.ds.sr.demo_app.model.MatchInfo;
import si.ds.sr.demo_app.model.Sport;
import si.ds.sr.demo_app.model.Tournament;

public class MatchesUtils {

	/**
	 * Filter by a team name
	 * 
	 * @param matches
	 * @param team
	 * @return
	 */
	public static List<Match> filterByTeam(List<Match> matches, String team) {
		
		return matches.stream()
					.filter(m -> m.getHomeTeam().contains(team) || m.getAwayTeam().contains(team))
					.collect(Collectors.toList());
	}
	
	/**
	 * Return only live matches
	 * 
	 * @param matches
	 * @param team
	 * @return
	 */
	public static List<Match> filterLiveOnly(List<Match> matches) {
		
		return matches.stream()
					.filter(m -> m.isLive())
					.collect(Collectors.toList());
	}
	
	/**
	 * Return only completed matches
	 * 
	 * @param matches
	 * @param team
	 * @return
	 */
	public static List<Match> filterCompletedOnly(List<Match> matches) {
		
		return matches.stream()
					.filter(m -> m.hasCompleted())
					.collect(Collectors.toList());
	}

	/**
	 * Reads mathes additional info and creates objects with full match data
	 * @param matches
	 * @return
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	public static List<MatchInfo> convertMatches(List<Match> matches) throws JsonMappingException, JsonProcessingException {
		
		List<MatchInfo> matchInfos = new ArrayList<>();
		for (Match match : matches) {
			
			String tournaments = DataCache.getInstance().getUnchecked(DataCache.KEYS.TOURNAMENTS.name());			
			Tournament tournament = getTournament(tournaments, match.getTournamentId());
			
			String sports = DataCache.getInstance().getUnchecked(DataCache.KEYS.SPORTS.name());
			Sport sport = getSport(sports, tournament.getSportId());
			
			MatchInfo matchInfo = new MatchInfo();
			matchInfo.setId(match.getId());
			matchInfo.setTournament(tournament.getName());
			matchInfo.setSport(sport.getName());
			matchInfo.setHomeTeam(match.getHomeTeam());
			matchInfo.setAwayTeam(match.getAwayTeam());
			matchInfo.setHomeScore(match.getHomeScore());
			matchInfo.setAwayScore(match.getAwayScore());
			matchInfo.setStartTime(match.getStartTime());
			matchInfo.setStatus(match.getStatus());
			
			matchInfos.add(matchInfo);
		}
		
		return matchInfos;
	}

	/**
	 * Parses the tournament registry saved in Json format and finds the tournament 
	 * corresponding to the given id.
	 * 
	 * @param tournaments
	 * @param tournamentId
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static Tournament getTournament(String tournaments, Integer tournamentId) throws JsonMappingException, JsonProcessingException {
		
		List<Tournament> tournamentsList = JsonUtil.json2tournaments(tournaments);
		return tournamentsList.stream()
			.filter(t -> tournamentId == t.getId())
			.findAny()
			.orElse(null);
	}
	
	/**
	 * Parses the sports registry saved in Json format and finds the sport 
	 * corresponding to the given id.
	 * 
	 * @param sports
	 * @param sportId
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static Sport getSport(String sports, Integer sportId) throws JsonMappingException, JsonProcessingException {
		
		List<Sport> sportsList = JsonUtil.json2sports(sports);
		return sportsList.stream()
			.filter(s -> sportId == s.getId())
			.findAny()
			.orElse(null);
	}

}
