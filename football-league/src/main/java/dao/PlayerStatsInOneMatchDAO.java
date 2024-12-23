package dao;

import dto.PlayerStatsInOneMatch;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class PlayerStatsInOneMatchDAO {
	public static final String GET_PLAYERS_STATS_IN_MATCHES = "select * from PlayerStatsInOneMatch";

	public static ObservableList<PlayerStatsInOneMatch> dajKlub() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<PlayerStatsInOneMatch> result = new ArrayList<>();

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_PLAYERS_STATS_IN_MATCHES);

			while(rs.next()) {
				int MatchID = rs.getInt(1);
				String PlayerJMBG = rs.getString(2);
				int NumberOfAssists = rs.getInt(3);
				int NumberOfGoals = rs.getInt(4);
				boolean YellowCard = rs.getBoolean(5);
				boolean RedCard = rs.getBoolean(6);
				int NumberOfMinutes = rs.getInt(7);
				boolean StartingEleven = rs.getBoolean(8);
				String ClubName = rs.getString(1);
				String ClubAddress = rs.getString(2);

				result.add(new PlayerStatsInOneMatch(MatchID, NumberOfAssists, NumberOfGoals, YellowCard, RedCard, NumberOfMinutes, StartingEleven));
				System.out.println(ClubName + " - " + ClubAddress);
			}
		} catch (SQLException e) {
			Logger.getLogger(PlayerStatsInOneMatch.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}

			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					Logger.getLogger(PlayerStatsInOneMatch.class.getName()).log(Level.SEVERE, null, e);
				}
			}

			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getLogger(PlayerStatsInOneMatch.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return FXCollections.observableArrayList(result);
	}
}
