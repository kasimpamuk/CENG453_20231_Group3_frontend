package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.PlayerActionInfo;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDTO {

	private ResponseType responseType;
	private PlayerActionInfo playerActionInfo;

}
