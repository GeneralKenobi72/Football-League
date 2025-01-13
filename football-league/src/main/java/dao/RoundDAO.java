package dao;

import dto.Round;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class RoundDAO {
	public static final String GET_ROUNDS = "select * from Round";

	public static ObservableList<Round> dajRunde() {
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
}
