package io.github.alprKeskin.kasimpamuk.thesettlersofcatan;

import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<JavaFX.StageReadyEvent> {
	@Override
	public void onApplicationEvent(JavaFX.StageReadyEvent event) {
		Stage stage = event.getStage();
	}
}
