package dao;

import dto.MatchStats;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class MatchStatsDAO {
	public static final String GET_MATCHSTATUSES = "select * from MatchStats";

	public static ObservableList<MatchStats> dajKlub() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<MatchStats> result = new ArrayList<>();

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_MATCHSTATUSES);

			while(rs.next()) {
				int MatchId = rs.getInt(1);
				int NumberOfGoalsHome = rs.getInt(2);
				int NumberOfGoalsGuests = rs.getInt(3);
				int NumberOfFans = rs.getInt(4);
				int NumberOfYellowCards = rs.getInt(5);
				int NumberOfRedCards = rs.getInt(6);
				int NumberOfCorners = rs.getInt(7);

				result.add(new MatchStats(MatchId, NumberOfGoalsHome, NumberOfGoalsGuests,NumberOfFans, NumberOfYellowCards, NumberOfRedCards, NumberOfCorners));
			}
		} catch (SQLException e) {
			Logger.getLogger(MatchStats.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}

			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					Logger.getLogger(MatchStats.class.getName()).log(Level.SEVERE, null, e);
				}
			}

			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getLogger(MatchStats.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return FXCollections.observableArrayList(result);
	}
}
