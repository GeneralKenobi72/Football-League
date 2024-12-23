package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.*;
import javafx.stage.*;

public class AddPlayer {

    @FXML
    private TextField AgeField;

    @FXML
    private TextField AssistsField;

    @FXML
    private Button CloseButton;

    @FXML
    private TextField ConcededField;

    @FXML
    private Button DoneButton;

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

	    @FXML
    void CloseClicked(MouseEvent event) {
		Stage currentStage = (Stage) CloseButton.getScene().getWindow();
		currentStage.close();
    }

    @FXML
    void DoneClicked(MouseEvent event) {
		String Name = null, Surname = null, JMBG = null;
		int Age = 0, Goals = 0, Assists = 0, Saves = 0, Conceded = 0, Games = 0, Minutes = 0;
		try {
			Name = NameField.getText();
			Surname = SurnameField.getText();
			JMBG = JMBGField.getText();
			Age = Integer.parseInt(AgeField.getText());
			Goals = Integer.parseInt(GoalsField.getText());
			Assists = Integer.parseInt(AssistsField.getText());
			Saves = Integer.parseInt(SavesField.getText());
			Conceded = Integer.parseInt(ConcededField.getText());
			Games = Integer.parseInt(GamesField.getText());
			Minutes = Integer.parseInt(MinutesField.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Name + Surname + JMBG + Age + Goals + Assists + Saves + Conceded + Games + Minutes);
    }

}

