package dto;

public class MatchStats {
	public int MatchID, NumberOfGoalsHome, NumberOfGoalsGuests, NumberOfFans, NumberOfYellowCards, NumberOfRedCards, NumberOfCorners;

	public MatchStats() {}
	public MatchStats(int mid, int nogh, int nogg,int nof,int noyc,int norc,int noc) {
		MatchID = mid;
		NumberOfFans = nof;
		NumberOfCorners = noc;
		NumberOfRedCards = norc;
		NumberOfGoalsHome = nogh;
		NumberOfGoalsGuests = nogg;
		NumberOfYellowCards = noyc;
	}
}
