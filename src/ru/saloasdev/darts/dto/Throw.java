package ru.saloasdev.darts.dto;

import java.util.Date;

public class Throw {
	private int trowId;
	private Date time;
	private int score;
	private String methodInsert;
	private int Comment;
	private int singleId;

	public int getTrowId() {
		return trowId;
	}

	public void setTrowId(int trowId) {
		this.trowId = trowId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getMethodInsert() {
		return methodInsert;
	}

	public void setMethodInsert(String methodInsert) {
		this.methodInsert = methodInsert;
	}

	public int getComment() {
		return Comment;
	}

	public void setComment(int comment) {
		Comment = comment;
	}

	public int getSingleId() {
		return singleId;
	}

	public void setSingleId(int singleId) {
		this.singleId = singleId;
	}
}
