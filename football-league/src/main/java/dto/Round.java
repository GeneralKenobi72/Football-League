package dto;

public class Round {
	int RoundNumber, SeasonYear;

	public Round() {}
	public Round(int rn, int sy) {
		RoundNumber = rn;
		SeasonYear = sy;
	}

	@Override
	public String toString() {
		return RoundNumber + " " + SeasonYear;
	}
}
