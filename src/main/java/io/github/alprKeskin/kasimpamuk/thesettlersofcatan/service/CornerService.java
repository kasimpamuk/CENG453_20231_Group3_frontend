package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.uı.SettlementCorner;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class CornerService {

	private final List<SettlementCorner> settlementCorners;

	public CornerService(List<SettlementCorner> settlementCorners) {
		this.settlementCorners = settlementCorners;
	}

	public void disableAllSettlementCornerButtons() {
		for (SettlementCorner settlementCorner : this.settlementCorners) {
			settlementCorner.disableButton();
		}
	}

	public void enableAllSettlementCornerButtons() {
		for (SettlementCorner settlementCorner : this.settlementCorners) {
			settlementCorner.enableButton();
		}
	}

}
