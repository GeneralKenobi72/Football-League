package dao;

import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;
import dto.*;

public class Club_has_MatchDAO {
	public static final String GET_CONTRACTS = "select * from Club_has_Match";

	public static ObservableList<Club_has_Match> dajClubhasMatches(int i) {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<Club_has_Match> result = new ArrayList<>();

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_CONTRACTS);

			while(rs.next()) {
				String Club_ClubName = rs.getString(1);
				int Match_MatchID = rs.getInt(2);
				//DATEs
				String Role = rs.getString(3);

				if(Match_MatchID == i)
				result.add(new Club_has_Match(Club_ClubName, Role, Match_MatchID)); // TODO Other constructor
			}
		} catch (SQLException e) {
			Logger.getLogger(Club_has_Match.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}

			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					Logger.getLogger(Club_has_Match.class.getName()).log(Level.SEVERE, null, e);
				}
			}

			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getLogger(Club_has_Match.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return FXCollections.observableArrayList(result);
	}
	public static boolean AddClub_has_Match(String Club, int matchid, String role) {
		Connection conn = null;
		CallableStatement cs = null;
		boolean success = false;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			conn.setAutoCommit(false);

			cs = conn.prepareCall("{call add_c_has_m(?, ?, ?)}");
			cs.setInt(2, matchid);
			cs.setString(1, Club);
			cs.setString(3, role);
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
