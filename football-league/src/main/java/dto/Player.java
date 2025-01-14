package dto;

public class Player {
	public String JMBG, Name, Surname;
	public int Age, NumberOfGames, TotalMinutes, NumberOfGoals, NumberOfAssists, NumberOfConcededGoals, NumberOfSaves;

	public Player() {}
	public Player(String jmbg, String name, String surname, int age, int noga, int tm, int nogo, int noa, int nos, int nocg) {
		JMBG = jmbg;
		Name = name;
		Surname = surname;
		Age = age;
		NumberOfGames = noga;
		NumberOfGoals = nogo;
		NumberOfAssists = noa;
		NumberOfSaves = nos;
		NumberOfConcededGoals = nocg;
		TotalMinutes = tm;
	}

	public String getJMBG() {
		return JMBG;
	}

	public String getName() {
		return Name;
	}

	public String getSurname() {
		return Surname;
	}

	public int getAge() {
		return Age;
	}

	public int getNumberOfGames() {
		return NumberOfGames;
	}

	public int getNumberOfGoals() {
		return NumberOfGoals;
	}

	public int getTotalMinutes() {
		return TotalMinutes;
	}
	
	public int getNumberOfAssists() {
		return NumberOfAssists;
	}

	public int getNumberOfConcededGoals() {
		return NumberOfConcededGoals;
	}

	public int getNumberOfSaves() {
		return NumberOfSaves;
	}

	public void setJMBG(String JMBG) {
		this.JMBG = JMBG;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public void setSurname(String Surname) {
		this.Surname = Surname;
	}

	public void setAge(int Age) {
		this.Age = Age;
	}

	public void setNumberOfGames(int NumberOfGames) {
		this.NumberOfGames = NumberOfGames;
	}

	public void setNumberOfGoals(int NumberOfGoals) {
		this.NumberOfGoals = NumberOfGoals;
	}

	public void setTotalMinutes(int TotalMinutes) {
		this.TotalMinutes = TotalMinutes;
	}
	
	public void setNumberOfAssists(int NumberOfAssists) {
		this.NumberOfAssists = NumberOfAssists;
	}

	public void setNumberOfConcededGoals(int NumberOfConcededGoals) {
		this.NumberOfConcededGoals = NumberOfConcededGoals;
	}

	public void setNumberOfSaves(int NumberOfSaves) {
		this.NumberOfSaves = NumberOfSaves;
	}

	@Override
	public String toString() {
		return Name + " " + Surname;
	}
}
