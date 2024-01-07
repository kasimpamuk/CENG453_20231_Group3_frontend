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

	public RegisterService(CatanRestService catanRestService) {
		this.catanRestService = catanRestService;
	}

	public VBox createRegisterBox() {
		VBox registerBox = new VBox(10);
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
		this.catanRestService.register(this.usernameField.getText(), this.passwordField.getText());
		return;
	}

}
