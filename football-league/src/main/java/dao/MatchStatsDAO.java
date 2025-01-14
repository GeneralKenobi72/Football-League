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
	public static boolean AddMatchStats(int matchid, int nogh, int nogg, int nof, int noyc, int norc, int noc) {
		Connection conn = null;
		CallableStatement cs = null;
		boolean success = false;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			conn.setAutoCommit(false);

			cs = conn.prepareCall("{call add_matchstats(?, ?, ?, ?, ?, ?, ?)}");
			cs.setInt(1, matchid);
			cs.setInt(2, nogh);
			cs.setInt(3, nogg);
			cs.setInt(4, nof);
			cs.setInt(5, noyc);
			cs.setInt(6, norc);
			cs.setInt(7, noc);
			cs.execute();
			conn.commit();
			success = true;
		} catch (SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException rbe) {
					rbe.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			if(cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}
		}
		return success;
	}
}
