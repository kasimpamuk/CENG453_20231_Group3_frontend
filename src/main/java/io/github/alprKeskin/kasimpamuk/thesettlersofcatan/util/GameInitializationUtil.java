package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.util;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain.*;

public class GameInitializationUtil {

	public static ArrayList<Terrain> createTerrainList() {
		ArrayList<Terrain> terrains = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			terrains.add(HILL);
			terrains.add(MOUNTAIN);
		}
		for (int i = 0; i < 4; i++) {
			terrains.add(FOREST);
			terrains.add(FIELD);
			terrains.add(PASTURE);
		}
		Collections.shuffle(terrains);
		return terrains;
	}

	public static ArrayList<Integer> createNumberList() {
		return getIdMocks();
//		ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(2, 12));
//		for (int number : new int[]{3, 4, 5, 6, 8, 9, 10, 11}) {
//			numbers.add(number);
//			numbers.add(number);
//		}
//		Collections.shuffle(numbers);
//		return numbers;
	}

	private static ArrayList<Integer> getIdMocks() {
		return new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18));
	}

}
