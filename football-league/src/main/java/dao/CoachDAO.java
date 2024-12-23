package dao;

import dto.Coach;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class CoachDAO {
	public static final String GET_COACHES = "select * from Coach";

	public static ObservableList<Coach> dajKlub() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<Coach> result = new ArrayList<>();

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_COACHES);

			while(rs.next()) {
				String JMBG = rs.getString(1);
				String Name = rs.getString(2);
				String Surname = rs.getString(3);
				int Age = rs.getInt(4);
				int NumberOfGames = rs.getInt(5);

				result.add(new Coach(JMBG, Name, Surname, Age, NumberOfGames));
			}
		} catch (SQLException e) {
			Logger.getLogger(Coach.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}

			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					Logger.getLogger(Coach.class.getName()).log(Level.SEVERE, null, e);
				}
			}

			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getLogger(Coach.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return FXCollections.observableArrayList(result);
	}
}
