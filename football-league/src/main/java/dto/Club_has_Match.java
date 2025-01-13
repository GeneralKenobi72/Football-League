package dto;

public class Club_has_Match {
	public String Club_ClubName, Role;
	int Match_MatchID;

	public Club_has_Match() {}
	public Club_has_Match(String ccn, String r, int mmid) {
		Club_ClubName = ccn;
		Match_MatchID = mmid;
		Role = r;
	}

	public String toString() {
		return Club_ClubName + " - " + Role + " - " + Match_MatchID;
	}
}
