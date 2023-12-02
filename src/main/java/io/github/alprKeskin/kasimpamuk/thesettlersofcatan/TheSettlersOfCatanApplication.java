package io.github.alprKeskin.kasimpamuk.thesettlersofcatan;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheSettlersOfCatanApplication {

	public static void main(String[] args) {
		new Thread(() -> Application.launch(JavaFX.class, args)).start();
		SpringApplication.run(TheSettlersOfCatanApplication.class, args);
	}

}
