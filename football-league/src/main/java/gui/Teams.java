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

public class Teams {

	@FXML
    private Text Date;

	@FXML
    private Button AddTeamButton;

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

	public static final String GET_CLUBSTATS = "select * from ClubStats";

	int nameX = 10, CoachX = 100, ValueX = 186, ButtonX = 260, spaceY = 20, WLDX = 250;
	int currentY;
	int spaceBoldLine = 4, lineStartX = 20, lineEndX = 280;

	@FXML
	public void initialize() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		ArrayList<Club> result = new ArrayList<>();
		currentY = 26;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_CLUBSTATS);

			while(rs.next()) {
				String ClubName = rs.getString(1);
				int TotalMatches = rs.getInt(3);
				int ScoredGoals = rs.getInt(2);
				int Wins = rs.getInt(4);
				int Losses = rs.getInt(5);
				int Draws = rs.getInt(6);

				currentY += spaceY;

				Text ClubNameText = new Text(ClubName);
				Text TotalMatchesText = new Text(new Integer(TotalMatches).toString());
				Text ScoredGoalsText = new Text(new Integer(ScoredGoals).toString());
				Text WinsText = new Text(new Integer(Wins).toString() + "-");
				Text LossesText = new Text(new Integer(Losses).toString() + "-");
				Text DrawsText = new Text(new Integer(Draws).toString());

				ClubNameText.setX(nameX);
				ClubNameText.setY(currentY);
				TotalMatchesText.setX(ValueX+24);
				TotalMatchesText.setY(currentY);
				ScoredGoalsText.setX(CoachX+24);
				ScoredGoalsText.setY(currentY);
				WinsText.setX(WLDX+4);
				WinsText.setY(currentY);
				LossesText.setX(WLDX+19);
				LossesText.setY(currentY);
				DrawsText.setX(WLDX+34);
				DrawsText.setY(currentY);

				Pane1.getChildren().add(ClubNameText);
				Pane1.getChildren().add(ScoredGoalsText);
				Pane1.getChildren().add(TotalMatchesText);
				Pane1.getChildren().add(WinsText);
				Pane1.getChildren().add(LossesText);
				Pane1.getChildren().add(DrawsText);

				Line line = new Line();
				currentY += 3;
				line.setStartX(lineStartX-10);
				line.setEndX(lineEndX+15);
				line.setStartY(currentY);
				line.setEndY(currentY);
				line.setStrokeWidth(0.5);

				Pane1.getChildren().add(line);
			}
		} catch (SQLException e) {
			Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if(conn != null) {
				ConnectionPool.getInstance().checkIn(conn);
			}

			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, e);
				}
			}

			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
	}

	@FXML
    void BackPressed2(MouseEvent event) {
		try {
			String pathToFXML = "src" + File.separator + "main" + File.separator + "java" + File.separator + "gui" + File.separator + "seasons_screen.fxml";
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
    void RefreshClicked(MouseEvent event) {
		initialize();
    }

	@FXML
    void AddButtonClicked(MouseEvent event) {
		try {
			String pathToFXML = "src" + File.separator + "main" + File.separator + "java" + File.separator + "gui" + File.separator + "add_team.fxml";
			FXMLLoader loader = new FXMLLoader(new File(pathToFXML).toURI().toURL());
			Parent root = loader.load();

			Stage primaryStage = new Stage();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Add Team");
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

