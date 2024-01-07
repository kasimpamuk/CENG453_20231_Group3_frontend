package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResourceInfo {
	private int brickAmount = 3; // EV TAŞI (TUĞLA)
	private int grainAmount = 1; // TAHIL
	private int lumberAmount = 3; // AĞAÇ
	private int oreAmount = 0; // MADEN (KÜÇÜK TAŞLAR)
	private int woolAmount = 1; // KOYUN
}

// House: Ağaç, tuğla, koyun, tahıl
// Road: Bir tuğla bir ağaç