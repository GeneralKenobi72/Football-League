package dao;

import dto.Match;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class MatchDAO {
	public static final String GET_MATCHES = "select * from Match";

	public static ObservableList<Match> dajKlub() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<Match> result = new ArrayList<>();

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_MATCHES);

			while(rs.next()) {
				int MatchID = rs.getInt(1);
				//DATETIME
				int RoundNumber = rs.getInt(3);
				int SeasonYear = rs.getInt(4);

				result.add(new Match()); // TODO Other constructor
			}
		} catch (SQLException e) {
			Logger.getLogger(Match.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}

			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					Logger.getLogger(Match.class.getName()).log(Level.SEVERE, null, e);
				}
			}

			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getLogger(Match.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return FXCollections.observableArrayList(result);
	}
}
