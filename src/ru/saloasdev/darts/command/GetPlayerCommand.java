package ru.saloasdev.darts.command;

import ru.saloasdev.darts.parser.PlayerParser;

@SuppressWarnings("serial")
public class GetPlayerCommand extends DartsCommand {

	public GetPlayerCommand(int playerId) {
		super("https://testapi.com/getplayer?playerid={playerid}");

		parser = new PlayerParser();
	}
}
