package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import javafx.fxml.*;
import dto.*;
import dao.*;
import java.io.*;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.scene.*;
import javafx.scene.control.MenuButton;
import java.sql.*;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;
import javafx.event.*;

public class AddSeason {

    @FXML
    private Button CloseButton;

    @FXML
    private Button DoneButton;

    @FXML
    private TextField YearField;

    @FXML
    void CloseClicked(MouseEvent event) {
		Stage currentStage = (Stage) CloseButton.getScene().getWindow();
		currentStage.close();
    }

    @FXML
    void DoneClicked(MouseEvent event) {
		int Year = 0;
		try {
			Year = Integer.parseInt(YearField.getText());
			System.out.println(Year);
			Season s = new Season(Year);
			SeasonDAO.AddSeason(Year);
			SeasonsScreen.changed = true;
			Stage currentStage = (Stage) CloseButton.getScene().getWindow();
			currentStage.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
