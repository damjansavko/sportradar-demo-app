package si.ds.sr.demo_app.utils;

import java.util.List;
import java.util.stream.Collectors;

import si.ds.sr.demo_app.model.Match;

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

}
