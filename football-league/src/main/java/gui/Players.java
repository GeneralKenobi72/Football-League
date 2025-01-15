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

public class Players {

	@FXML
    private Text Date;

	@FXML
    private Button AddPlayerButton;

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

	public static final String GET_MATCHES = "select * from PlayerStats";

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

		Pane1.getChildren().clear();
		Text NameLabel = new Text("Name");
		Text AgeLabel = new Text("Age");
		Text GamesLabel = new Text("Games");
		Text MinutesLabel = new Text("Minutes");
		Text GoalsLabel = new Text("G");
		Line lineHeader = new Line();

		NameLabel.setX(10);
		NameLabel.setY(25);
		NameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");
		AgeLabel.setX(122);
		AgeLabel.setY(25);
		AgeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");
		GamesLabel.setX(156);
		GamesLabel.setY(25);
		GamesLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");
		MinutesLabel.setX(211);
		MinutesLabel.setY(25);
		MinutesLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");
		GoalsLabel.setX(283);
		GoalsLabel.setY(25);
		GoalsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");
		lineHeader.setStartX(115-108);
		lineHeader.setEndX(115+187);
		lineHeader.setStartY(33);
		lineHeader.setEndY(33);

		Pane1.getChildren().add(NameLabel);
		Pane1.getChildren().add(AgeLabel);
		Pane1.getChildren().add(GamesLabel);
		Pane1.getChildren().add(MinutesLabel);
		Pane1.getChildren().add(lineHeader);
		Pane1.getChildren().add(GoalsLabel);

		try {
			conn = ConnectionPool.getInstance().checkOut();
			s = conn.createStatement();
			rs = s.executeQuery(GET_MATCHES);

			while(rs.next()) {
				String Name = rs.getString(1);
				String Surname = rs.getString(2);
				int Age = rs.getInt(3);
				int Matches = rs.getInt(4);
				int Minutes = rs.getInt(5);
				int Goals = rs.getInt(6);

				currentY += spaceY;

				Text NameSurnameText = new Text(Name + Surname);
				Text AgeText = new Text(new Integer(Age).toString());
				Text MatchesText = new Text(new Integer(Matches).toString());
				Text MinutesText = new Text(new Integer(Minutes).toString());
				Text GoalsText = new Text(new Integer(Goals).toString());

				NameSurnameText.setX(10);
				NameSurnameText.setY(currentY);
				AgeText.setX(125);
				AgeText.setY(currentY);
				MatchesText.setX(173);
				MatchesText.setY(currentY);
				MinutesText.setX(225);
				MinutesText.setY(currentY);
				GoalsText.setX(283);
				GoalsText.setY(currentY);

				Pane1.getChildren().add(NameSurnameText);
				Pane1.getChildren().add(AgeText);
				Pane1.getChildren().add(MatchesText);
				Pane1.getChildren().add(MinutesText);
				Pane1.getChildren().add(GoalsText);

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
			String pathToFXML = "src" + File.separator + "main" + File.separator + "java" + File.separator + "gui" + File.separator + "add_player.fxml";
			FXMLLoader loader = new FXMLLoader(new File(pathToFXML).toURI().toURL());
			Parent root = loader.load();

			Stage primaryStage = new Stage();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Add Player");
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}

