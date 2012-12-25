package ru.saloasdev.darts.parser;

import java.util.Date;

import org.json.JSONObject;

import ru.saloasdev.darts.dto.Player;

@SuppressWarnings("serial")
public class PlayerParser implements JSONParser<Player> {
	@Override
	public Player parse(JSONObject jsonObject) {
		Player player = new Player();

		JSONObject response = jsonObject.optJSONObject("response");
		player.setName(response.optString("name"));
		player.setLastOnline(new Date(response.optLong("lastOnline")));
		player.setAdditionalInfo(response.optString("additionalInfo"));
		player.setTrainerId(response.optInt("trainerId"));
		player.setPlayerId(response.optInt("idPlayer"));
		return player;
	}

}
