package dao;

import dto.Season;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class SeasonDAO {
	public static final String GET_SEASONS = "select * from Season";

	public static ObservableList<Season> dajKlub() {
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
}
