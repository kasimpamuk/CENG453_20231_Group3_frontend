package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.menu.MenuService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Service
public class JavaFxService {

	private final MenuService menuService;

	@Autowired
	public JavaFxService(MenuService menuService) {
		this.menuService = menuService;
	}

	public void displayMenu() {
		this.menuService.displayMenu();
	}


}
