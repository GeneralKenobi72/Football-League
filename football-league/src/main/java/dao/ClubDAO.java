package dao;

import dto.Club;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class ClubDAO {
	public static final String GET_CLUBS = "select * from Club";

	public static ObservableList<Club> dajKlub() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<Club> result = new ArrayList<>();

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_CLUBS);

			while(rs.next()) {
				String ClubName = rs.getString(1);
				String ClubAddress = rs.getString(2);

				result.add(new Club(ClubName, ClubAddress));
				System.out.println(ClubName + " - " + ClubAddress);
			}
		} catch (SQLException e) {
			Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}

			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, e);
				}
			}

			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return FXCollections.observableArrayList(result);
	}
}
