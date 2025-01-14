package dao;

import dto.Round;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class RoundDAO {
	public static final String GET_ROUNDS = "select * from Round";

	public static ObservableList<Round> dajRunde(int i) {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<Round> result = new ArrayList<>();

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_ROUNDS);

			while(rs.next()) {
				int RoundNumber = rs.getInt(1);
				int SeasonYear = rs.getInt(2);

				if(SeasonYear == i)
					result.add(new Round(RoundNumber, SeasonYear));
			}
		} catch (SQLException e) {
			Logger.getLogger(Round.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}

			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					Logger.getLogger(Round.class.getName()).log(Level.SEVERE, null, e);
				}
			}

			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getLogger(Round.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return FXCollections.observableArrayList(result);
	}

	public static boolean AddRound(int round, int season) {
		Connection conn = null;
		CallableStatement cs = null;
		boolean success = false;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			conn.setAutoCommit(false);

			cs = conn.prepareCall("{call add_round(?, ?)}");
			cs.setInt(1, round);
			cs.setInt(2, season);
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
