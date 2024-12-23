package dao;

import dto.PlayerStatsInSeason;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class PlayerStatsInSeasonDAO {
	public static final String GET_PLAYERS_STATS_IN_SEASON = "select * from PlayerStatsInSeason";

	public static ObservableList<PlayerStatsInSeason> dajKlub() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<PlayerStatsInSeason> result = new ArrayList<>();

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_PLAYERS_STATS_IN_SEASON);

			while(rs.next()) {
				int SeasonYear = rs.getInt(1);
				String JMBG = rs.getString(2);
				int NumberOfAssists = rs.getInt(3);
				int NumberOfGoals = rs.getInt(4);
				int NumberOfYellowCards = rs.getInt(5);
				int NumberOfRedCards = rs.getInt(6);
				int NumberOfMinutes = rs.getInt(7);
				int NumberOfTimesInStartingEleven = rs.getInt(8);
				int NumberOfGames = rs.getInt(9);
				int NumberOfConcededGoals = rs.getInt(10);
				int NumberOfSaves = rs.getInt(11);

				result.add(new PlayerStatsInSeason(SeasonYear, JMBG, NumberOfGames, NumberOfAssists, NumberOfGoals, NumberOfYellowCards, NumberOfRedCards, NumberOfMinutes, NumberOfTimesInStartingEleven, NumberOfConcededGoals, NumberOfSaves));
			}
		} catch (SQLException e) {
			Logger.getLogger(PlayerStatsInSeason.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}

			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					Logger.getLogger(PlayerStatsInSeason.class.getName()).log(Level.SEVERE, null, e);
				}
			}

			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getLogger(PlayerStatsInSeason.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return FXCollections.observableArrayList(result);
	}
}
