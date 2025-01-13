package dao;

import dto.MatchStats;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class MatchStatsDAO {
	public static final String GET_MATCHSTATUSES = "select * from MatchStats";

	public static ObservableList<MatchStats> dajMatchStats(int i) {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<MatchStats> result = new ArrayList<>();

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_MATCHSTATUSES);

			while(rs.next()) {
				int MatchID = rs.getInt(7);
				int NumberOfGoalsHome = rs.getInt(1);
				int NumberOfGoalsGuests = rs.getInt(2);
				int NumberOfFans = rs.getInt(3);
				int NumberOfYellowCards = rs.getInt(4);
				int NumberOfRedCards = rs.getInt(5);
				int NumberOfCorners = rs.getInt(6);

				if(MatchID == i)
					result.add(new MatchStats(MatchID, NumberOfGoalsHome, NumberOfGoalsGuests,NumberOfFans, NumberOfYellowCards, NumberOfRedCards, NumberOfCorners));
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
