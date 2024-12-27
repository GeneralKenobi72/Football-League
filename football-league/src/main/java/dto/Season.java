package dto;

public class Season {
	public int Year;

	public Season() {}
	public Season(int year) {
		Year = year;
	}

	@Override
	public String toString() {
		return String.valueOf(Year);
	}
}
