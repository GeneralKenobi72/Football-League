package dto;

public class Club {
	public String ClubName, ClubAddress;

	public Club() {}
	public Club(String cn, String ca) {
		ClubName = cn;
		ClubAddress = ca;
	}

	public String getClubName() {
		return ClubName;
	}

	public String getClubAddress() {
		return ClubAddress;
	}

	public void setClubName(String ClubName) {
		this.ClubName = ClubName;
	}

	public void setClubAddress(String ClubAddress) {
		this.ClubAddress = ClubAddress;
	}

	@Override
	public String toString() {
		return "Club name: " + ClubName;
	}
}
