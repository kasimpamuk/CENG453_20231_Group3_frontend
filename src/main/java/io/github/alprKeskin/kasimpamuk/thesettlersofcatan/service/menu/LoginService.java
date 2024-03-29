package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.menu;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.restservice.CatanRestService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.util.ClientInfo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	private final Button loginButton = new Button("Login");
	private final TextField usernameField  = new TextField();
	private final TextField passwordField  = new TextField();
	private final CatanRestService catanRestService;
	private MenuService menuService;
	VBox loginBox;
	Label loginSuccessful;
	Label loginFailed;

	@Lazy
	public LoginService(CatanRestService catanRestService, MenuService menuService) {
		this.catanRestService = catanRestService;
		this.menuService = menuService;
	}

	public VBox createLoginBox() {
		this.loginBox = new VBox(10);
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
		String token = this.catanRestService.login(this.usernameField.getText(), this.passwordField.getText());
		if (token != null) {
			ClientInfo.token = token;
			System.out.println("Login successful!");
			this.menuService.getBtPlay().setDisable(false);
			loginSuccessful = new Label("Login successful!");
			loginSuccessful.setStyle("-fx-background-color: #5fbd59; -fx-text-fill: #000000; -fx-font-size: 17px;");
			loginSuccessful.setPadding(new Insets(10));
			loginSuccessful.setAlignment(Pos.CENTER);
			loginSuccessful.setPrefWidth(150);
			loginSuccessful.setPrefHeight(50);
			this.loginBox.getChildren().removeAll(loginFailed);
			this.loginBox.getChildren().removeAll(loginSuccessful);

			this.loginBox.getChildren().add(loginSuccessful);
		} else {
			ClientInfo.token = "";
			System.out.println("Login failed!");
			this.menuService.getBtPlay().setDisable(true);

			loginFailed = new Label("Login failed!");
			loginFailed.setStyle("-fx-background-color: #d06060; -fx-text-fill: #000000; -fx-font-size: 17px;");
			loginFailed.setPadding(new Insets(10));
			loginFailed.setAlignment(Pos.CENTER);
			loginFailed.setPrefWidth(150);
			loginFailed.setPrefHeight(50);
			this.loginBox.getChildren().removeAll(loginFailed);
			this.loginBox.getChildren().removeAll(loginSuccessful);

			this.loginBox.getChildren().add(loginFailed);
		}
		return;
	}

}
