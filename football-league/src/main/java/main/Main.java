package main;

import dto.*;
import dao.*;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import java.io.*;
import gui.*;

public class Main extends Application {
	public static StartScreen GuiInstance;

	public static void main(String[] args) {
		System.out.println("Initial structure");
		launch(args);

		ClubDAO.dajKlub();
		System.exit(0);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		String pathToFXML = "src" + File.separator + "main" + File.separator + "java" + File.separator + "gui" + File.separator + "start_screen.fxml";
		FXMLLoader loader = new FXMLLoader(new File(pathToFXML).toURI().toURL());
		Parent root = loader.load();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Welcome");
		primaryStage.show();

		GuiInstance = loader.getController();
	}
}
