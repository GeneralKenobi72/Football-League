package dto;

public class Stadium {
	public String StadiumAddress, Name;
	int Capacity;

	public Stadium() {}
	public Stadium(String sa, int c, String name) {
		StadiumAddress = sa;
		Capacity = c;
		Name = name;
	}

	public String getName() {
		return Name;
	}

	public String getStadiumAddress() {
		return StadiumAddress;
	}

	public int getCapacity() {
		return Capacity;
	}

	public void setCapacity(int Capacity) {
		this.Capacity = Capacity;
	}

	public void setStadiumAddress(String sa) {
		StadiumAddress = sa;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {
		return "Name: " + Name + ", Capacity: " + Capacity;
	}
}
