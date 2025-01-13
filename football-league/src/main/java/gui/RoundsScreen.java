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

public class RoundsScreen {

	@FXML
    private Button AddMatchButton;

    @FXML
    private Button AddRoundButton;

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
    private Button AddPlayer;

    @FXML
    private Button AddPomocno;

    @FXML
    private Button AddTeam;

    @FXML
    private Button Leaderboard;

    @FXML
    private Button Players;

    @FXML
    private Button Pomocno;

    @FXML
    private Button Teams;

	@FXML
    private Button BackButton2;

	@FXML
    private Button Rounds;

    @FXML
    private Label Season;

	@FXML
    private Line lineBold;

    @FXML
    private Line lineThin;

	@FXML
    private AnchorPane Pane1;

	int currentY = 0;
	int spaceRound = 28, roundX = 22, spaceBoldLine = 4, spaceTeam = 14, teamX = 100, dateX = 27, timeX = 29, scoreX = 240, lineStartX = 20, lineEndX = 280;

	@FXML
    void AddMatchPressed(MouseEvent event) {

    }

    @FXML
    void AddRoundPressed(MouseEvent event) {

    }

	@FXML
	public void initialize() {
		Season.setText(SeasonsScreen.choosenSeason);
		ObservableList<Round> ol = RoundDAO.dajRunde();
		if(ol==null)
			return;
		for(int i=0;i<ol.size();i++) {
			Text Roundi = new Text();
			String rnds[] = ol.get(i).toString().split(" ");
			Roundi.setText("Round " + rnds[0]);
			Roundi.setX(roundX);
			Roundi.setStyle("-fx-font-weight: bold;");
			Roundi.setStyle("-fx-font-size: 15;");
			currentY += spaceRound;
			Roundi.setY(currentY);
			currentY += spaceBoldLine;
			Line line = new Line();
			line.setStartX(lineStartX);
			line.setEndX(lineEndX);
			line.setStartY(currentY);
			line.setEndY(currentY);
			Roundi.setVisible(true);
			line.setVisible(true);
			
			Pane1.getChildren().add(Roundi);
			Pane1.getChildren().add(line);

			ObservableList<Match> matches = MatchDAO.dajMeceve(i+1);
			if(matches == null)
				continue;
			System.out.println("Mecevi:");
			System.out.println(matches.size() + " " + matches.toString());
			for(int j=0;j<matches.size();j++) {
				String matchStrings[] = matches.get(j).toString().split(" ");
				ObservableList<MatchStats> matchstats = MatchStatsDAO.dajMatchStats(Integer.parseInt(matchStrings[0]));
				ObservableList<Club_has_Match> chm = Club_has_MatchDAO.dajClubhasMatches(Integer.parseInt(matchStrings[0]));
				if(chm == null || chm.toString().equals("[]"))
					continue;
				Text date = new Text();
				Text time = new Text();
				Text club1 = new Text();
				Text club2 = new Text();
				Text goals1 = new Text();
				Text goals2 = new Text();
				String datetime[] = matchStrings[3].split("T");
				date.setText("D " + datetime[0].substring(5));
				time.setText("T " + datetime[1]);
				String chm1[] = chm.get(0).toString().split("-");	
				String chm2[] = chm.get(1).toString().split("-");	
				club1.setText(chm1[0]);
				club2.setText(chm2[0]);
				String ms1[] = matchstats.get(0).toString().split(" ");
				System.out.println("Match stats" + j);
				System.out.println(matchstats.get(0).toString());
				goals1.setText(ms1[0]);
				goals2.setText(ms1[1]);
				date.setX(dateX);
				time.setX(timeX);
				currentY += spaceTeam;
				date.setY(currentY);
				club1.setY(currentY);
				club1.setX(teamX);
				goals1.setY(currentY);
				goals1.setX(scoreX);

				currentY += spaceTeam;
				time.setY(currentY);
				goals2.setX(scoreX);
				goals2.setY(currentY);
				club2.setX(teamX);
				club2.setY(currentY);

				Pane1.getChildren().add(time);
				Pane1.getChildren().add(date);
				Pane1.getChildren().add(club1);
				Pane1.getChildren().add(club2);
				Pane1.getChildren().add(goals1);
				Pane1.getChildren().add(goals2);

				if(j != matches.size() - 1) {
					currentY += spaceBoldLine;
					Line line2 = new Line();
					line2.setStrokeWidth(0.5);
					line2.setStartX(lineStartX);
					line2.setEndX(lineEndX);
					line2.setStartY(currentY);
					line2.setEndY(currentY);
					Pane1.getChildren().add(line2);
				}

			}
			currentY += spaceBoldLine;
			Line line2 = new Line();
			line2.setStartX(lineStartX);
			line2.setEndX(lineEndX);
			line2.setStartY(currentY);
			line2.setEndY(currentY);
			Pane1.getChildren().add(line2);
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
}

