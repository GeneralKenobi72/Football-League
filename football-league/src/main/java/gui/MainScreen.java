package gui;

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

public class MainScreen {

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
    private Button BackButton;

	@FXML
    private Button Rounds;

    @FXML
    private Label Season;

	@FXML
	public void initialize() {
		Season.setText(SeasonsScreen.choosenSeason);
	}

    @FXML
    void AddPlayerPressed(MouseEvent event) {
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

    @FXML
    void AddTeamsPressed(MouseEvent event) {
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

    @FXML
    void PlayersPressed(MouseEvent event) {
		try {
			String pathToFXML = "src" + File.separator + "main" + File.separator + "java" + File.separator + "gui" + File.separator + "players.fxml";
			FXMLLoader loader = new FXMLLoader(new File(pathToFXML).toURI().toURL());
			Parent root = loader.load();

			Stage primaryStage = new Stage();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Players");
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void LeaderboardPress(MouseEvent event) {
		try {
			String pathToFXML = "src" + File.separator + "main" + File.separator + "java" + File.separator + "gui" + File.separator + "leaderboard.fxml";
			FXMLLoader loader = new FXMLLoader(new File(pathToFXML).toURI().toURL());
			Parent root = loader.load();

			Stage primaryStage = new Stage();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Players");
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void TeamsPressed(MouseEvent event) {
		try {
			String pathToFXML = "src" + File.separator + "main" + File.separator + "java" + File.separator + "gui" + File.separator + "teams.fxml";
			FXMLLoader loader = new FXMLLoader(new File(pathToFXML).toURI().toURL());
			Parent root = loader.load();

			Stage primaryStage = new Stage();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Team");
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@FXML
    void BackPressed(MouseEvent event) {
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

			Stage currentStage = (Stage) BackButton.getScene().getWindow();
			currentStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void RoundsPressed(MouseEvent event) {
		try {
			String pathToFXML = "src" + File.separator + "main" + File.separator + "java" + File.separator + "gui" + File.separator + "rounds_screen.fxml";
			FXMLLoader loader = new FXMLLoader(new File(pathToFXML).toURI().toURL());
			Parent root = loader.load();

			Stage primaryStage = new Stage();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Round");
			primaryStage.show();

			Stage currentStage = (Stage) BackButton.getScene().getWindow();
			currentStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

