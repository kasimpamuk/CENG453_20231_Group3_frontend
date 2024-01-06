package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.authentication;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	private final Button loginButton = new Button("Login");
	private final TextField usernameField  = new TextField();
	private final TextField passwordField  = new TextField();

	public VBox createLoginBox() {
		VBox loginBox = new VBox(10);
		loginBox.setAlignment(Pos.TOP_RIGHT);
		loginBox.setPadding(new Insets(10));
		loginBox.setStyle("-fx-background-color: #87CEEB;");

		loginBox.getChildren().add(new Label("Login"));

		this.usernameField.setPromptText("Username");
		loginBox.getChildren().add(usernameField);

		this.passwordField.setPromptText("Password");
		loginBox.getChildren().add(passwordField);

		this.loginButton.setOnAction(e -> login());
		loginBox.getChildren().add(loginButton);
		return loginBox;
	}

	private void login() {
		// TODO: code...
		System.out.println("Login button clicked!");
		return;
	}

}
