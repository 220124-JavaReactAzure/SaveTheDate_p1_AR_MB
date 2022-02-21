package com.revature.saveTheDate.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Attendee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="attendee_id")
	private int id;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="wedding_id")
	private int weddingId;
	
	@Column(name="isattending")
	private boolean isAttending;
	
	@Column(name="dinner_id")
	private int dinnerId;
	
	@Column(name="plus_one")
	private boolean plusOne;
	
	@Column(name="pluse_one_dinner_id")
	private int plusOneDinnerId;

	public Attendee() {
		super();
	}

	public Attendee(int id, int userId, int weddingId, boolean isAttending, int dinnerId, boolean plusOne,
			int plusOneDinnerId) {
		super();
		this.id = id;
		this.userId = userId;
		this.weddingId = weddingId;
		this.isAttending = isAttending;
		this.dinnerId = dinnerId;
		this.plusOne = plusOne;
		this.plusOneDinnerId = plusOneDinnerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getWeddingId() {
		return weddingId;
	}

	public void setWeddingId(int weddingId) {
		this.weddingId = weddingId;
	}

	public boolean isAttending() {
		return isAttending;
	}

	public void setAttending(boolean isAttending) {
		this.isAttending = isAttending;
	}

	public int getDinnerId() {
		return dinnerId;
	}

	public void setDinnerId(int dinnerId) {
		this.dinnerId = dinnerId;
	}

	public boolean isPlusOne() {
		return plusOne;
	}

	public void setPlusOne(boolean plusOne) {
		this.plusOne = plusOne;
	}

	public int getPlusOneDinnerId() {
		return plusOneDinnerId;
	}

	public void setPlusOneDinnerId(int plusOneDinnerId) {
		this.plusOneDinnerId = plusOneDinnerId;
	}

	@Override
	public String toString() {
		return "Attendee [id=" + id + ", userId=" + userId + ", weddingId=" + weddingId + ", isAttending=" + isAttending
				+ ", dinnerId=" + dinnerId + ", plusOne=" + plusOne + ", plusOneDinnerId=" + plusOneDinnerId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dinnerId, id, isAttending, plusOne, plusOneDinnerId, userId, weddingId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attendee other = (Attendee) obj;
		return dinnerId == other.dinnerId && id == other.id && isAttending == other.isAttending
				&& plusOne == other.plusOne && plusOneDinnerId == other.plusOneDinnerId && userId == other.userId
				&& weddingId == other.weddingId;
	}
}
