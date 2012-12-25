package ru.saloasdev.darts.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 
 * @author nekdenis
 * @formatter:off
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "Darts.db";
	private static final int DATABASE_VERSION = 1;

	private static final String TEXT_TYPE = " TEXT";
	private static final String INT_TYPE = " INT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
	    "CREATE TABLE " + DartsContracs.PlayerField.TABLE_NAME + " (" +
	    DartsContracs.PlayerField._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
	    DartsContracs.PlayerField.COLUMN_NAME_PLAYER_NAME + TEXT_TYPE + COMMA_SEP +
	    DartsContracs.PlayerField.COLUMN_NAME_LOGIN + TEXT_TYPE + COMMA_SEP +
	    DartsContracs.PlayerField.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
	    DartsContracs.PlayerField.COLUMN_NAME_LASTONLINE + INT_TYPE + COMMA_SEP +
	    DartsContracs.PlayerField.COLUMN_NAME_INFO + TEXT_TYPE + COMMA_SEP +
	    DartsContracs.PlayerField.COLUMN_NAME_TRAINER_ID +" INTEGER NOT NULL ,FOREIGN KEY (+" +
	    DartsContracs.PlayerField.COLUMN_NAME_TRAINER_ID+ ") REFERENCES " +
	    DartsContracs.TrainerField.TABLE_NAME + " ("+ DartsContracs.TrainerField._ID+") "+
	    " )" +
	    "CREATE TABLE " + DartsContracs.TrainerField.TABLE_NAME + " (" +
	    DartsContracs.TrainerField._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
	    DartsContracs.TrainerField.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
	    DartsContracs.TrainerField.COLUMN_NAME_LOGIN + TEXT_TYPE + COMMA_SEP +
	    DartsContracs.TrainerField.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
	    DartsContracs.TrainerField.COLUMN_NAME_LASTONLINE + INT_TYPE + COMMA_SEP +
	    DartsContracs.TrainerField.COLUMN_NAME_INFO + TEXT_TYPE + COMMA_SEP +
	    " )" +
	    "CREATE TABLE " + DartsContracs.TrainField.TABLE_NAME + " (" +
	    DartsContracs.TrainField._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
	    DartsContracs.TrainField.COLUMN_NAME_STARTTIME+ INT_TYPE + COMMA_SEP +
	    DartsContracs.TrainField.COLUMN_NAME_FINISHTIME + INT_TYPE + COMMA_SEP +
	    DartsContracs.TrainField.COLUMN_NAME_FINISHED + INT_TYPE + COMMA_SEP +
	    DartsContracs.TrainField.COLUMN_NAME_PLAYER +" INTEGER NOT NULL ,FOREIGN KEY (+" +
	    DartsContracs.TrainField.COLUMN_NAME_PLAYER+ ") REFERENCES " +
	    DartsContracs.PlayerField.TABLE_NAME + " ("+ DartsContracs.PlayerField._ID+") "+
	    DartsContracs.TrainField.COLUMN_NAME_TRAINER +" INTEGER NOT NULL ,FOREIGN KEY (+" +
	    DartsContracs.TrainField.COLUMN_NAME_TRAINER+ ") REFERENCES " +
	    DartsContracs.TrainerField.TABLE_NAME + " ("+ DartsContracs.TrainerField._ID+") "+
	    " )" +
	    "CREATE TABLE " + DartsContracs.SingleField.TABLE_NAME + " (" +
	    DartsContracs.SingleField._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
	    DartsContracs.SingleField.COLUMN_NAME_STARTTIME+ INT_TYPE + COMMA_SEP +
	    DartsContracs.SingleField.COLUMN_NAME_FINISHTIME + INT_TYPE + COMMA_SEP +
	    DartsContracs.SingleField.COLUMN_NAME_FINISHED + INT_TYPE + COMMA_SEP +
	    DartsContracs.SingleField.COLUMN_NAME_PLAYER +" INTEGER NOT NULL ,FOREIGN KEY (+" +
	    DartsContracs.SingleField.COLUMN_NAME_PLAYER+ ") REFERENCES " +
	    DartsContracs.PlayerField.TABLE_NAME + " ("+ DartsContracs.PlayerField._ID+") "+
	    DartsContracs.SingleField.COLUMN_NAME_ROUND +" INTEGER NOT NULL ,FOREIGN KEY (+" +
	    DartsContracs.SingleField.COLUMN_NAME_ROUND+ ") REFERENCES " +
	    DartsContracs.RoundField.TABLE_NAME + " ("+ DartsContracs.RoundField._ID+") "+
	    " )" +
	    "CREATE TABLE " + DartsContracs.ThrowField.TABLE_NAME + " (" +
	    DartsContracs.ThrowField._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
	    DartsContracs.ThrowField.COLUMN_NAME_TIME+ INT_TYPE + COMMA_SEP +
	    DartsContracs.ThrowField.COLUMN_NAME_SCORE + INT_TYPE + COMMA_SEP +
	    DartsContracs.ThrowField.COLUMN_NAME_METHOD + TEXT_TYPE + COMMA_SEP +
	    DartsContracs.ThrowField.COLUMN_NAME_COMMENT +" INTEGER NOT NULL ,FOREIGN KEY (+" +
	    DartsContracs.ThrowField.COLUMN_NAME_COMMENT+ ") REFERENCES " +
	    DartsContracs.CommentField.TABLE_NAME + " ("+ DartsContracs.CommentField._ID+") "+
	    DartsContracs.ThrowField.COLUMN_NAME_SINGLE +" INTEGER NOT NULL ,FOREIGN KEY (+" +
	    DartsContracs.ThrowField.COLUMN_NAME_SINGLE+ ") REFERENCES " +
	    DartsContracs.SingleField.TABLE_NAME + " ("+ DartsContracs.SingleField._ID+") "+
	    " )" +
	    "CREATE TABLE " + DartsContracs.CommentField.TABLE_NAME + " (" +
	    DartsContracs.CommentField._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
	    DartsContracs.CommentField.COLUMN_NAME_TEXT+ TEXT_TYPE + COMMA_SEP +
	    " )" +
	    "CREATE TABLE " + DartsContracs.RoundField.TABLE_NAME + " (" +
	    DartsContracs.RoundField._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
	    DartsContracs.RoundField.COLUMN_NAME_ROUND +" INTEGER NOT NULL ,FOREIGN KEY (+" +
	    DartsContracs.RoundField.COLUMN_NAME_ROUND+ ") REFERENCES " +
	    DartsContracs.RoundField.TABLE_NAME + " ("+ DartsContracs.RoundField._ID+") "+
	    " )" +
	    "CREATE TABLE " + DartsContracs.TournamentField.TABLE_NAME + " (" +
	    DartsContracs.TournamentField._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
	    DartsContracs.TournamentField.COLUMN_NAME_FIRST_ROUND +" INTEGER NOT NULL ,FOREIGN KEY (+" +
	    DartsContracs.TournamentField.COLUMN_NAME_FIRST_ROUND + ") REFERENCES " +
	    DartsContracs.RoundField.TABLE_NAME + " ("+ DartsContracs.RoundField._ID+") "+
	    " )";

	private static final String SQL_DELETE_ENTRIES =
	    "DROP TABLE IF EXISTS " + DartsContracs.PlayerField.TABLE_NAME +
	    " DROP TABLE IF EXISTS "+ DartsContracs.TrainField.TABLE_NAME + 
	    " DROP TABLE IF EXISTS "+ DartsContracs.TrainerField.TABLE_NAME + 
	    " DROP TABLE IF EXISTS "+ DartsContracs.SingleField.TABLE_NAME + 
	    " DROP TABLE IF EXISTS "+ DartsContracs.RoundField.TABLE_NAME + 
	    " DROP TABLE IF EXISTS "+ DartsContracs.CommentField.TABLE_NAME + 
	    " DROP TABLE IF EXISTS "+ DartsContracs.RoundField.TABLE_NAME + 
	    " DROP TABLE IF EXISTS "+ DartsContracs.TournamentField.TABLE_NAME;
	

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		if (!db.isReadOnly()) {
			// Enable foreign key constraints
			db.execSQL("PRAGMA foreign_keys=ON;");
		}
	}
}