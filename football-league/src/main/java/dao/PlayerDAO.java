package dao;

import dto.Player;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class PlayerDAO {
	public static final String GET_PLAYERS = "select * from Player";

	public static ObservableList<Player> dajKlub() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<Player> result = new ArrayList<>();

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_PLAYERS);

			while(rs.next()) {
				String JMBG = rs.getString(1);
				String Name = rs.getString(2);
				String Surname = rs.getString(3);
				int Age = rs.getInt(4);
				int NumberOfGames = rs.getInt(5);
				int TotalMinutes = rs.getInt(6);
				int NumberOfGoals = rs.getInt(7);
				int NumberOfAssists = rs.getInt(8);
				int NumberOfSaves = rs.getInt(9);
				int NumberOfConcededGoals = rs.getInt(10);

				result.add(new Player(JMBG, Name, Surname, Age, NumberOfGames, TotalMinutes, NumberOfGoals, NumberOfAssists, NumberOfSaves, NumberOfConcededGoals));
			}
		} catch (SQLException e) {
			Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}

			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, e);
				}
			}

			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return FXCollections.observableArrayList(result);
	}

	public static boolean AddPlayer(String name, String surname, String JMBG, int age, int noga, int tm, int nogo, int noa, int nos, int nocg) {
		Connection conn = null;
		CallableStatement cs = null;
		boolean success = false;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			conn.setAutoCommit(false);

			cs = conn.prepareCall("{call add_player(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			cs.setString(1, name);
			cs.setString(2, surname);
			cs.setString(3, JMBG);
			cs.setInt(4, age);
			cs.setInt(5, noga);
			cs.setInt(6, tm);
			cs.setInt(7, nogo);
			cs.setInt(8, noa);
			cs.setInt(9, nos);
			cs.setInt(10, nocg);
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
