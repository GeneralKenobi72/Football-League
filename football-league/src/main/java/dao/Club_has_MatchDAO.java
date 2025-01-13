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
}
