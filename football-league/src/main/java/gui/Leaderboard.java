package gui;

import dao.*;
import dto.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import main.*;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import java.io.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.shape.Line;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import javafx.scene.layout.AnchorPane;
import java.time.*;
import java.util.logging.*;

public class Leaderboard {

	@FXML
    private Text Date;

    @FXML
    private Text RoundNum;

    @FXML
    private Text Score1;

    @FXML
    private Text Score2;

    @FXML
    private Text Team1;

    @FXML
    private Text Team2;

    @FXML
    private Text Time;

    @FXML
    private Label Season;

	@FXML
    private Button BackButton2;

	int maxround = 0;
	int currentY;

	@FXML
    private Button RefreshButton;

	@FXML
    private AnchorPane Pane1;

	int spaceRound = 28, roundX = 22, spaceBoldLine = 4, spaceTeam = 14, teamX = 100, dateX = 27, timeX = 29, scoreX = 240, lineStartX = 20, lineEndX = 280;

	@FXML
	public void initialize() {
		Connection conn = null;
		CallableStatement cs = null;
		boolean success = false;
		ResultSet rs = null;	

		try {
			conn = ConnectionPool.getInstance().checkOut();
			conn.setAutoCommit(false);

			cs = conn.prepareCall("{call leaderboard(?)}");
			cs.setInt(1, Integer.parseInt(SeasonsScreen.choosenSeason));
			rs = cs.executeQuery();
			while(rs.next()) {
				String ClubName = rs.getString(1);
				int numOfMatches = rs.getInt(2);
				int points = rs.getInt(3);
				System.out.println(ClubName + " " + points);
			}
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
	}

	@FXML
    void BackPressed2(MouseEvent event) {
		try {
			String pathToFXML = "src" + File.separator + "main" + File.separator + "java" + File.separator + "gui" + File.separator + "main_screen.fxml";
			FXMLLoader loader = new FXMLLoader(new File(pathToFXML).toURI().toURL());
			Parent root = loader.load();

			Stage primaryStage = new Stage();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Football League");
			primaryStage.show();

			// Main.GuiInstance = loader.getController();

			Stage currentStage = (Stage) BackButton2.getScene().getWindow();
			currentStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@FXML
    void RefreshPressed(MouseEvent event) {
		initialize();
    }
}

