package gui;

import javafx.collections.*;
import javafx.event.ActionEvent;
import gui.SeasonsScreen;
import dao.*;
import dto.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javafx.event.*;
import java.time.*;
import java.time.format.*;

public class AddMatch {
	@FXML
    private TextField DateField;

	@FXML
    private TextField TimeField;

    @FXML
    private Button CloseButton;

    @FXML
    private TextField CorncersField;

    @FXML
    private Button DoneButton;

    @FXML
    private TextField FansField;

    @FXML
    private TextField GoalsGuestsField;

    @FXML
    private TextField GoalsHomeField;

    @FXML
    private MenuButton GuestTeamField;

    @FXML
    private MenuButton HomeTeamField;

    @FXML
    private TextField RedCardsField;

    @FXML
    private MenuButton RoundField;

    @FXML
    private TextField YellowCardsField;

    @FXML
    private TextField AgeField;

    @FXML
    private TextField AssistsField;

    @FXML
    private TextField ConcededField;

    @FXML
    private TextField GamesField;

    @FXML
    private TextField GoalsField;

    @FXML
    private TextField JMBGField;

    @FXML
    private TextField MinutesField;

    @FXML
    private TextField NameField;

    @FXML
    private TextField SavesField;

    @FXML
    private TextField SurnameField;

	public static int choosenRound = 0;
	public static String choosenHomeTeam = null;
	public static String choosenGuestTeam = null;
	
	@FXML
	public void initialize() {
		ObservableList<Round> olr = RoundDAO.dajRunde(Integer.parseInt(SeasonsScreen.choosenSeason));
		ObservableList<Club> olc = ClubDAO.dajKlub();
		HomeTeamField.getItems().removeAll();
		GuestTeamField.getItems().removeAll();
		RoundField.getItems().removeAll();
		for(int i=0;i<olr.size();i++) {
			String strings[] = olr.get(i).toString().split(" ");
			MenuItem mi = new MenuItem(strings[0]);
			RoundField.getItems().add(mi);
			mi.setOnAction(new EventHandler<ActionEvent>() {
    			@Override public void handle(ActionEvent e) {
					RoundField.setText(mi.getText());
					choosenRound = Integer.parseInt(mi.getText());
    			}
			});
		}

		for(int i=0;i<olc.size();i++) {
			String clubInfo[] = olc.get(i).toString().split("-");
			if(clubInfo.length < 2)
				continue;
			MenuItem mi = new MenuItem(clubInfo[0]);
			HomeTeamField.getItems().add(mi);
			mi.setOnAction(new EventHandler<ActionEvent>() {
    			@Override public void handle(ActionEvent e) {
					HomeTeamField.setText(mi.getText());
					choosenHomeTeam = mi.getText();
    			}
			});
		}

		for(int i=0;i<olc.size();i++) {
			String clubInfo[] = olc.get(i).toString().split("-");
			if(clubInfo.length < 2)
				continue;
			MenuItem mi = new MenuItem(clubInfo[0]);
			GuestTeamField.getItems().add(mi);
			mi.setOnAction(new EventHandler<ActionEvent>() {
    			@Override public void handle(ActionEvent e) {
					GuestTeamField.setText(mi.getText());
					choosenGuestTeam = mi.getText();
    			}
			});
		}
	}

	@FXML
    void CloseClicked(MouseEvent event) {
		Stage currentStage = (Stage) CloseButton.getScene().getWindow();
		currentStage.close();
    }

    @FXML
    void DoneClicked(MouseEvent event) {
		int matchid=0, season = Integer.parseInt(SeasonsScreen.choosenSeason), goalshome = 0, goalsguests = 0, fans = 0, corners = 0, yellowcards = 0, redcards = 0;
		int round = choosenRound;
		String HomeTeam, GuestTeam;
		String date, time;
		String fulldatetime;
		LocalDateTime datetime = null;

		ObservableList<Match> matches = MatchDAO.dajMeceve();
		matchid = matches.size() + 1;

		try {
			if(GoalsHomeField.getText() != "")
				goalshome = Integer.parseInt(GoalsHomeField.getText());
			if(GoalsGuestsField.getText() != "")
				goalsguests = Integer.parseInt(GoalsGuestsField.getText());
			if(FansField.getText() != "")
				fans = Integer.parseInt(FansField.getText());
			if(CorncersField.getText() != "")
				corners = Integer.parseInt(CorncersField.getText());
			if(YellowCardsField.getText() != "")
				yellowcards = Integer.parseInt(YellowCardsField.getText());
			if(RedCardsField.getText() != "")
				redcards = Integer.parseInt(RedCardsField.getText());
			round = Integer.parseInt(RoundField.getText());
			HomeTeam = choosenHomeTeam;
			GuestTeam = choosenGuestTeam;
			date = DateField.getText();
			time = TimeField.getText();
			fulldatetime = SeasonsScreen.choosenSeason + "-" + date + "T" + time;
			DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			datetime = LocalDateTime.parse(fulldatetime, formatter);
			System.out.println(matchid + " " + HomeTeam + " " + GuestTeam);
			if(matchid != 0 && round != 0 && HomeTeam != "Home Team" && HomeTeam != "" && GuestTeam != "Guest Team" && GuestTeam != "" && datetime != null) {
				Match m = new Match(matchid, round, season, datetime); 
				MatchStats ms = new MatchStats(matchid, goalshome, goalsguests, fans, corners, yellowcards, redcards);
				MatchDAO.AddMatch(matchid, datetime, round, season);
				MatchStatsDAO.AddMatchStats(matchid, goalshome, goalsguests, fans, yellowcards, redcards, corners);
				Club_has_Match chmh = new Club_has_Match(HomeTeam, "Home", matchid);
				Club_has_Match chma = new Club_has_Match(GuestTeam, "Away", matchid);
				Club_has_MatchDAO.AddClub_has_Match(HomeTeam, matchid, "Home");
				Club_has_MatchDAO.AddClub_has_Match(GuestTeam, matchid, "Away");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Stage currentStage = (Stage) CloseButton.getScene().getWindow();
		currentStage.close();
    }

}

