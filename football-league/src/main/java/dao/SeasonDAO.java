package dao;

import dto.Season;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class SeasonDAO {
	public static final String GET_SEASONS = "select * from Season";

	public static ObservableList<Season> dajSezone() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<Season> result = new ArrayList<>();

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_SEASONS);

			while(rs.next()) {
				int Year = rs.getInt(1);

				result.add(new Season(Year));
			}
		} catch (SQLException e) {
			Logger.getLogger(Season.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}

			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					Logger.getLogger(Season.class.getName()).log(Level.SEVERE, null, e);
				}
			}

			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getLogger(Season.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return FXCollections.observableArrayList(result);
	}

	public static boolean AddSeason(int year) {
		Connection conn = null;
		CallableStatement cs = null;
		boolean success = false;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			conn.setAutoCommit(false);

			cs = conn.prepareCall("{call add_season(?)}");
			cs.setInt(1, year);
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
