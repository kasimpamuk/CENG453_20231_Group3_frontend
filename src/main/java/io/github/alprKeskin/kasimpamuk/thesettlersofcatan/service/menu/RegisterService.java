package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.menu;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.restservice.CatanRestService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

	private final Button registerButton = new Button("Register");
	private final TextField usernameField  = new TextField();
	private final TextField passwordField  = new TextField();
	private final CatanRestService catanRestService;
	private VBox registerBox;

	public RegisterService(CatanRestService catanRestService) {
		this.catanRestService = catanRestService;
	}

	public VBox createRegisterBox() {
		registerBox = new VBox(10);
		registerBox.setAlignment(Pos.TOP_RIGHT);
		registerBox.setPadding(new Insets(10));
		registerBox.setStyle("-fx-background-color: #87CEEB;");
		registerBox.getChildren().add(new Label("Register"));

		this.usernameField.setPromptText("Username");
		registerBox.getChildren().add(usernameField);

		this.passwordField.setPromptText("Password");
		registerBox.getChildren().add(passwordField);

		this.registerButton.setOnAction(e -> register());
		registerBox.getChildren().add(registerButton);
		return registerBox;
	}

	private void register() {
		// TODO: code...
		System.out.println("Register button clicked!");
		System.out.println("Username: " + this.usernameField.getText());
		System.out.println("Password: " + this.passwordField.getText());
		Boolean isSuccesfull =  this.catanRestService.register(this.usernameField.getText(), this.passwordField.getText());
		if (isSuccesfull) {
			System.out.println("Register successful!");
			Label registerSuccessful = new Label("Register successful!");
			registerSuccessful.setStyle("-fx-background-color: #5fbd59; -fx-text-fill: #000000; -fx-font-size: 15px;");
			registerSuccessful.setPadding(new Insets(10));
			registerSuccessful.setAlignment(Pos.CENTER);
			registerSuccessful.setPrefWidth(150);
			registerSuccessful.setPrefHeight(50);
			Label registerUsername = new Label("Username: " + this.usernameField.getText());

			registerBox.getChildren().add(registerSuccessful);
			registerBox.getChildren().add(registerUsername);


		} else {
			System.out.println("Register failed!");
			Label registerFailed = new Label("Register failed!");
			registerFailed.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #000000; -fx-font-size: 15px;");
			registerFailed.setPadding(new Insets(10));
			registerFailed.setAlignment(Pos.CENTER);
			registerFailed.setPrefWidth(150);
			registerFailed.setPrefHeight(50);
			registerBox.getChildren().add(registerFailed);

		}
		return;
	}

}
