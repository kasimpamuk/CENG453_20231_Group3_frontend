package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.request;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.PlayerActionInfo;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.RequestType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestDTO {

	private RequestType requestType;
	private int gameId;
	private PlayerActionInfo playerActionInfo;

}
