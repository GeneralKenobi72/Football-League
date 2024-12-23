package dto;

public class Coach {
	public String JMBG, Name, Surname;
	int Age, NumberOfGames;

	public Coach() {}
	public Coach(String jmbg, String name, String surname, int age, int nog) {
		JMBG = jmbg;
		Name = name;
		Surname = surname;
		Age = age;
		NumberOfGames = nog;
	}
}
