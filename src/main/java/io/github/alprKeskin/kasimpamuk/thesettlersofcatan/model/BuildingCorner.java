package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildingCorner {

    private int id;
    private Point location;
    private Button button;

    public void clickAction() {
        // TODO: Implement logic...
        System.out.println("Button " +  id + " clicked!");
    }

    public void disableButton() {
        button.setDisable(true);
    }

    public void enableButton() {
        button.setDisable(false);
    }

}
