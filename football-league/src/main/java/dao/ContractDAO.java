package dao;

import dto.Contract;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;

public class ContractDAO {
	public static final String GET_CONTRACTS = "select * from Contract";

	public static ObservableList<Contract> dajKlub() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<Contract> result = new ArrayList<>();

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_CONTRACTS);

			while(rs.next()) {
				int ContractID = rs.getInt(1);
				int Value = rs.getInt(2);
				//DATEs
				String ClubName = rs.getString(5);
				int SeasonYer = rs.getInt(6);
				String JMBG = rs.getString(7);

				result.add(new Contract()); // TODO Other constructor
			}
		} catch (SQLException e) {
			Logger.getLogger(Contract.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}

			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					Logger.getLogger(Contract.class.getName()).log(Level.SEVERE, null, e);
				}
			}

			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getLogger(Contract.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return FXCollections.observableArrayList(result);
	}
}
