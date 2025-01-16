package dao;

import dto.Match;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;
import java.time.*;

public class MatchDAO {
	public static final String GET_MATCHES = "select * from `Match`";

	public static ObservableList<Match> dajMeceve(int i, int j) {
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
				LocalDateTime ldt = rs.getTimestamp(2).toLocalDateTime();
				int RoundNumber = rs.getInt(3);
				int SeasonYear = rs.getInt(4);

				if(RoundNumber == i && SeasonYear == j)
					result.add(new Match(MatchID, RoundNumber, SeasonYear, ldt)); // TODO Other constructor
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

	public static ObservableList<Match> dajMeceve() {
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
				LocalDateTime ldt = rs.getTimestamp(2).toLocalDateTime();
				int RoundNumber = rs.getInt(3);
				int SeasonYear = rs.getInt(4);

				result.add(new Match(MatchID, RoundNumber, SeasonYear, ldt));
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

	public static boolean AddMatch(int matchid, LocalDateTime ldt, int round, int season) {
		Connection conn = null;
		CallableStatement cs = null;
		boolean success = false;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			conn.setAutoCommit(false);

			cs = conn.prepareCall("{call add_match(?, ?, ?, ?)}");
			cs.setInt(1, matchid);
			cs.setTimestamp(2, Timestamp.valueOf(ldt));
			cs.setInt(3, round);
			cs.setInt(4, season);
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

	public static boolean AddMatch(int matchid, LocalDateTime ldt, int round, int season, int nogh, int nogg, int nof, int noyc, int norc, int noc, String home, String guest) {
		Connection conn = null;
		CallableStatement cs = null;
		boolean success = false;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			conn.setAutoCommit(false);

			cs = conn.prepareCall("{call add_full_match(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			cs.setInt(1, matchid);
			cs.setTimestamp(2, Timestamp.valueOf(ldt));
			cs.setInt(3, round);
			cs.setInt(4, season);
			cs.setInt(5, nogh);
			cs.setInt(6, nogg);
			cs.setInt(7, nof);
			cs.setInt(8, noyc);
			cs.setInt(9, norc);
			cs.setInt(10, noc);
			cs.setString(11, home);
			cs.setString(12, guest);
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
