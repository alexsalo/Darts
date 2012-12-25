package ru.saloasdev.darts.dto;

import java.util.Date;

public class Single {

	private int singleId;
	private Date startTime;
	private Date endTime;
	private boolean finished;
	private int player;
	private String singleCol;
	private int round;

	public int getSingleId() {
		return singleId;
	}

	public void setSingleId(int singleId) {
		this.singleId = singleId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public String getSingleCol() {
		return singleCol;
	}

	public void setSingleCol(String singleCol) {
		this.singleCol = singleCol;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

}
