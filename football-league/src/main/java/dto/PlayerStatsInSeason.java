package dto;

public class PlayerStatsInSeason {
	int SeasonYear, NumberOfAssists, NumberOfGoals, NumberOfYellowCards, NumberOfRedCards, NumberOfMinutes, NumberOfTimesInStartingEleven, NumberOfGames, NumberOfSaves, NumberOfConcededGoals;
	String JMBG;

	public PlayerStatsInSeason() {}
	public PlayerStatsInSeason(int sy, String jmbg, int noga, int noa, int nogo, int noyc, int norc, int nom, int notise, int nos, int nocg) {
		SeasonYear = sy;
		JMBG = jmbg;
		NumberOfGames = noga;
		NumberOfAssists = noa;
		NumberOfGoals = nogo;
		NumberOfMinutes = nom;
		NumberOfRedCards = norc;
		NumberOfYellowCards = noyc;
		NumberOfTimesInStartingEleven = notise;
		NumberOfSaves = nos;
		NumberOfConcededGoals = nocg;
	}
}
