package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.mainmanu.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBox {

        private String username;
        private String password;
        private VBox loginBox;
        private Button loginButton;
        private TextField usernameField;
        private TextField passwordField;


        public LoginBox() {
            createLoginBox();
        }
        private void createLoginBox() {
            this.loginBox = new VBox(10);
            this.loginBox.setAlignment(Pos.TOP_RIGHT);
            this.loginBox.setPadding(new Insets(10));
            this.loginBox.setStyle("-fx-background-color: #87CEEB;");

            this.loginBox.getChildren().add(new Label("Login"));

            this.usernameField = new TextField();
            this.usernameField.setPromptText("Username");
            this.loginBox.getChildren().add(usernameField);

            this.passwordField = new TextField();
            this.passwordField.setPromptText("Password");
            this.loginBox.getChildren().add(passwordField);


            this.loginButton = new Button("Login");
            this.loginButton.setOnAction(e -> login());
            this.loginBox.getChildren().add(loginButton);

        }

        private void login() {
            return;
        }
}
