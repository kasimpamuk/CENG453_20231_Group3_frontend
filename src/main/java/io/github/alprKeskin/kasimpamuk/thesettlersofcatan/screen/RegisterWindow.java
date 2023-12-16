package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterWindow {

    private final GridPane pane;

    public RegisterWindow() {
        this.pane = new GridPane();
    }

    public void displayRegisterWindow() {

        initializeWindowConfigurations();

        createRegistrationForm();

        createRegistrationButton();

        showStage();
    }

    private void initializeWindowConfigurations() {
        this.pane.setAlignment(Pos.CENTER);
        this.pane.setPadding(new Insets(11, 12, 13, 14));
        this.pane.setHgap(5);
        this.pane.setVgap(5);
    }

    private void createRegistrationForm() {
        this.pane.add(new Label("Email:"), 0, 0);
        this.pane.add(new TextField(), 1, 0);
        this.pane.add(new Label("Password:"), 0, 1);
        this.pane.add(new TextField(), 1, 1);
    }

    private void createRegistrationButton() {
        Button btAdd = new Button("Add User");
        this.pane.add(btAdd, 1, 2);
        GridPane.setHalignment(btAdd, HPos.RIGHT);
    }

    private void showStage() {
        Stage register_st = new Stage();
        register_st.setTitle("Register User");
        register_st.setScene(new Scene(this.pane, 300, 200));
        register_st.show();
    }

}
