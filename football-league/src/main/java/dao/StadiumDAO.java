package dao;

import dto.Stadium;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class StadiumDAO {
	public static final String GET_STADIUMS = "select * from Stadium";

	public static ObservableList<Stadium> dajKlub() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<Stadium> result = new ArrayList<>();

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_STADIUMS);

			while(rs.next()) {
				String StadiumAddress = rs.getString(1);
				int Capacity = rs.getInt(2);
				String Name = rs.getString(3);

				result.add(new Stadium(StadiumAddress, Capacity, Name));
			}
		} catch (SQLException e) {
			Logger.getLogger(Stadium.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}

			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					Logger.getLogger(Stadium.class.getName()).log(Level.SEVERE, null, e);
				}
			}

			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getLogger(Stadium.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return FXCollections.observableArrayList(result);
	}
}
