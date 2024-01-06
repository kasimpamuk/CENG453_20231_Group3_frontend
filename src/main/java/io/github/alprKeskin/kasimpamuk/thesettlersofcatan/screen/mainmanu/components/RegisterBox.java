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
public class RegisterBox {

        private TextField usernameField;
        private TextField passwordField;

        private VBox registerBox;

        private Button registerButton;


        public RegisterBox() {
            createRegisterBox();
        }

        private void createRegisterBox() {
            this.registerBox = new VBox(10);
            this.registerBox.setAlignment(Pos.TOP_LEFT);
            this.registerBox.setPadding(new Insets(10));
            this.registerBox.setStyle("-fx-background-color: #87CEEB;");

            this.registerBox.getChildren().add(new Label("Register"));

            this.usernameField = new TextField();
            this.usernameField.setPromptText("Username");
            this.registerBox.getChildren().add(usernameField);

            this.passwordField = new TextField();
            this.passwordField.setPromptText("Password");
            this.registerBox.getChildren().add(passwordField);

            this.registerButton = new Button("Register");
            this.registerButton.setOnAction(e -> register());
            this.registerBox.getChildren().add(registerButton);
        }

        private void register() {
            return;
        }
}
