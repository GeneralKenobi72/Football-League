package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import dto.Club;
import dao.ClubDAO;

public class AddTeam {

    @FXML
    private Button CloseButton;

    @FXML
    private Button DoneButton;

    @FXML
    private TextField NameField;

    @FXML
    private TextField AddressField;

    @FXML
    void CloseClicked(MouseEvent event) {
		Stage currentStage = (Stage) CloseButton.getScene().getWindow();
		currentStage.close();
    }

    @FXML
    void DoneClicked(MouseEvent event) {
		String Name = null, Address = null;
		try {
			Name = NameField.getText();
			Address = AddressField.getText();

			Club c = new Club(Name, Address);
			ClubDAO.AddClub(Name, Address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Name + Address);
    }

}

