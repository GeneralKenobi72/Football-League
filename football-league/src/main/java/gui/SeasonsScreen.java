package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.MenuButton;
import java.io.*;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;
import javafx.event.*;
import dao.*;
import dto.*;

public class SeasonsScreen {

    @FXML
    private Button AddSeason;

    @FXML
    private Button Back;

    @FXML
    private Button Select;

    @FXML
    public MenuButton menuButton;

	public static String choosenSeason;
	public static boolean changed = false;

	@FXML
	public void initialize() {
		ObservableList<Season> ol = SeasonDAO.dajSezone();
		menuButton.getItems().removeAll();
		for(int i=0;i<ol.size();i++) {
			MenuItem mi = new MenuItem(ol.get(i).toString());
			menuButton.getItems().add(mi);
			mi.setOnAction(new EventHandler<ActionEvent>() {
    			@Override public void handle(ActionEvent e) {
					menuButton.setText(mi.getText());
					choosenSeason = mi.getText();
    			}
			});
		}
	}

    @FXML
    void AddSeasonClicked(MouseEvent event) {
		try {
			String pathToFXML = "src" + File.separator + "main" + File.separator + "java" + File.separator + "gui" + File.separator + "add_season.fxml";
			FXMLLoader loader = new FXMLLoader(new File(pathToFXML).toURI().toURL());
			Parent root = loader.load();

			Stage primaryStage = new Stage();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Add Season");
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void BackClicked(MouseEvent event) {
		try {
			String pathToFXML = "src" + File.separator + "main" + File.separator + "java" + File.separator + "gui" + File.separator + "start_screen.fxml";
			FXMLLoader loader = new FXMLLoader(new File(pathToFXML).toURI().toURL());
			Parent root = loader.load();

			Stage primaryStage = new Stage();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Welcome");
			primaryStage.show();

			// Main.GuiInstance = loader.getController();

			Stage currentStage = (Stage) Back.getScene().getWindow();
			currentStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void SelectClicked(MouseEvent event) {
		if(menuButton.getText().compareTo("Choose Season") == 0)
			return;
		try {
			String pathToFXML = "src" + File.separator + "main" + File.separator + "java" + File.separator + "gui" + File.separator + "main_screen.fxml";
			FXMLLoader loader = new FXMLLoader(new File(pathToFXML).toURI().toURL());
			Parent root = loader.load();

			Stage primaryStage = new Stage();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Welcome");
			primaryStage.show();

			// Main.GuiInstance = loader.getController();

			Stage currentStage = (Stage) Back.getScene().getWindow();
			currentStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }	

	@FXML
    void menuButtonClicked(MouseEvent event) {
		if(changed == true)	{
			menuButton.getItems().clear();

			ObservableList<Season> ol = SeasonDAO.dajSezone();
			for(int i=0;i<ol.size();i++) {
				MenuItem mi = new MenuItem(ol.get(i).toString());
				menuButton.getItems().add(mi);
				mi.setOnAction(new EventHandler<ActionEvent>() {
    				@Override public void handle(ActionEvent e) {
						menuButton.setText(mi.getText());
						choosenSeason = mi.getText();
    				}
				});
			}
			changed = false;
		}
    }
}

