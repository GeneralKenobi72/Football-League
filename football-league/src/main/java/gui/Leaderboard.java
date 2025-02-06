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

	@FXML
    private Button RefreshButton;

	@FXML
    private AnchorPane Pane1;

	int placeX = 13, TeamX = 39, PointsX = 205, WLDX = 257, spaceY = 16;
	int currentY;
	int spaceBoldLine = 4, lineStartX = 20, lineEndX = 280;

	@FXML
	public void initialize() {
		Season.setText(SeasonsScreen.choosenSeason);
		Connection conn = null;
		CallableStatement cs = null;
		boolean success = false;
		ResultSet rs = null;	

		Line line2 = new Line();
		line2.setStartX(lineStartX-10);
		line2.setEndX(lineEndX+15);
		line2.setStartY(24);
		line2.setEndY(24);

		Pane1.getChildren().add(line2);

		try {
			conn = ConnectionPool.getInstance().checkOut();
			conn.setAutoCommit(false);

			cs = conn.prepareCall("{call leaderboard(?)}");
			cs.setInt(1, Integer.parseInt(SeasonsScreen.choosenSeason));
			rs = cs.executeQuery();

			currentY = 26;
			int i=0;
			while(rs.next()) {
				i++;
				String ClubName = rs.getString(1);
				int points = rs.getInt(3);
				int numOfWins = rs.getInt(4);
				int numOfLosses = rs.getInt(5);
				int numOfDraws = rs.getInt(6);
				System.out.println(ClubName + " " + points + " " + numOfWins + " " + numOfLosses + " " + numOfDraws);
				currentY += spaceY;

				Text place = new Text(new Integer(i).toString());
				Text ClubNameText = new Text(ClubName);
				Text numOfPointsText = new Text(new Integer(points).toString());
				Text numOfWinsText = new Text(new Integer(numOfWins).toString());
				Text numOfLossesText = new Text(new Integer(numOfLosses).toString());
				Text numOfDrawsText = new Text(new Integer(numOfDraws).toString());

				place.setX(placeX);
				place.setY(currentY);
				ClubNameText.setX(TeamX);
				ClubNameText.setY(currentY);
				numOfPointsText.setX(PointsX);
				numOfPointsText.setY(currentY);
				numOfWinsText.setX(WLDX);
				numOfWinsText.setY(currentY);
				numOfLossesText.setX(WLDX+15);
				numOfLossesText.setY(currentY);
				numOfDrawsText.setX(WLDX+30);
				numOfDrawsText.setY(currentY);

				Pane1.getChildren().add(place);
				Pane1.getChildren().add(ClubNameText);
				Pane1.getChildren().add(numOfPointsText);
				Pane1.getChildren().add(numOfWinsText);
				Pane1.getChildren().add(numOfLossesText);
				Pane1.getChildren().add(numOfDrawsText);

				Line line = new Line();
				currentY += 3;
				line.setStartX(lineStartX-10);
				line.setEndX(lineEndX+15);
				line.setStartY(currentY);
				line.setEndY(currentY);
				line.setStrokeWidth(0.5);

				Pane1.getChildren().add(line);
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
			primaryStage.setTitle(SeasonsScreen.choosenSeason);
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

