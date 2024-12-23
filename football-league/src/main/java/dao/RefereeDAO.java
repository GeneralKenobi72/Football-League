package dao;

import dto.Referee;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class RefereeDAO {
	public static final String GET_REFEREES = "select * from Referee";

	public static ObservableList<Referee> dajKlub() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<Referee> result = new ArrayList<>();

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_REFEREES);

			while(rs.next()) {
				String JMBG = rs.getString(1);
				String Name = rs.getString(2);
				String Surname = rs.getString(3);

				result.add(new Referee(JMBG, Name, Surname));
			}
		} catch (SQLException e) {
			Logger.getLogger(Referee.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}

			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					Logger.getLogger(Referee.class.getName()).log(Level.SEVERE, null, e);
				}
			}

			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getLogger(Referee.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return FXCollections.observableArrayList(result);
	}
}
