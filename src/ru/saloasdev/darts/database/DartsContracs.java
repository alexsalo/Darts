package ru.saloasdev.darts.database;

import android.provider.BaseColumns;

public class DartsContracs {
	private DartsContracs() {
	}

	public static abstract class PlayerField implements BaseColumns {

		public static final String TABLE_NAME = "player";
		public static final String _ID = "playerid";
		public static final String COLUMN_NAME_PLAYER_NAME = "name";
		public static final String COLUMN_NAME_PASSWORD = "password";
		public static final String COLUMN_NAME_LOGIN = "login";
		public static final String COLUMN_NAME_LASTONLINE = "lastonline";
		public static final String COLUMN_NAME_INFO = "info";
		public static final String COLUMN_NAME_TRAINER_ID = "trainerid";
	}

	public static abstract class SingleField implements BaseColumns {

		public static final String TABLE_NAME = "single";
		public static final String _ID = "singleid";
		public static final String COLUMN_NAME_STARTTIME = "starttime";
		public static final String COLUMN_NAME_FINISHTIME = "finishtime";
		public static final String COLUMN_NAME_FINISHED = "finished";
		public static final String COLUMN_NAME_PLAYER = "playerid";
		public static final String COLUMN_NAME_ROUND = "roundid";
	}

	public abstract class ThrowField implements BaseColumns {

		public static final String TABLE_NAME = "throw";
		public static final String _ID = "throwid";
		public static final String COLUMN_NAME_TIME = "time";
		public static final String COLUMN_NAME_SCORE = "score";
		public static final String COLUMN_NAME_METHOD = "method";
		public static final String COLUMN_NAME_COMMENT = "commentid";
		public static final String COLUMN_NAME_SINGLE = "singleid";
	}
	
	public abstract class CommentField implements BaseColumns {

		public static final String TABLE_NAME = "comment";
		public static final String _ID = "commentid";
		public static final String COLUMN_NAME_TEXT = "text";
	}
	
	public abstract class RoundField implements BaseColumns {

		public static final String TABLE_NAME = "round";
		public static final String _ID = "roundid";
		public static final String COLUMN_NAME_ROUND = "nextRoundId";
	}

	public abstract class TournamentField implements BaseColumns {

		public static final String TABLE_NAME = "tournament";
		public static final String _ID = "tournamentid";
		public static final String COLUMN_NAME_FIRST_ROUND = "firstRoundId";
	}
	
	public abstract class TrainField implements BaseColumns {

		public static final String TABLE_NAME = "train";
		public static final String _ID = "trainid";
		public static final String COLUMN_NAME_STARTTIME = "starttime";
		public static final String COLUMN_NAME_FINISHTIME = "finishtime";
		public static final String COLUMN_NAME_FINISHED = "boolean";
		public static final String COLUMN_NAME_PLAYER = "playerid";
		public static final String COLUMN_NAME_TRAINER = "trainerid";
	}
	
	public abstract class TrainerField implements BaseColumns {

		public static final String TABLE_NAME = "trainer";
		public static final String _ID = "trainerid";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_LASTONLINE = "lastonline";
		public static final String COLUMN_NAME_INFO = "info";
		public static final String COLUMN_NAME_LOGIN = "login";
		public static final String COLUMN_NAME_PASSWORD = "password";
	}
	
}
