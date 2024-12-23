package dto;

public class Match {
	public int MatchID, RoundNumber, SeasonYear;

	public Match() {}
	public Match(int mid, int rn, int sy) {
		MatchID = mid;
		RoundNumber = rn;
		SeasonYear = sy;
	}

	public int getMatchId() {
		return MatchID;
	}

	public int getRoundNumber() {
		return RoundNumber;
	}

	public int getSeasonYear() {
		return SeasonYear;
	}

	public void setSeasonYear(int sy) {
		SeasonYear = sy;
	}

	public void setRoundNumber(int rn) {
		RoundNumber = rn;
	}

	public void setMatchID(int mid) {
		MatchID = mid;
	}

	@Override
	public String toString() {
		return MatchID + " - " + RoundNumber + " - " + SeasonYear;
	}
}
