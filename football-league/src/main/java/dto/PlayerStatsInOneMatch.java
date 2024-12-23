package dto;

public class PlayerStatsInOneMatch {
	public int MatchID, NumberOfAssists, NumberOfGoals, NumberOfMinutes;
	boolean YellowCard, RedCard, StartingEleven;

	public PlayerStatsInOneMatch() {}
	public PlayerStatsInOneMatch(int mid, int noa, int nog, boolean yc, boolean rc, int nom, boolean se) {
		MatchID = mid;
		NumberOfGoals = nog;
		NumberOfAssists = noa;
		NumberOfMinutes = nom;
		YellowCard = yc;
		RedCard = rc;
		StartingEleven = se;
	}
}
