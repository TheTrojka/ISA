package com.in28minutes.springboot.web.model;

import java.util.Date;

public class Todo {
    private int id;
    private String user;
    private String desc;
    private Date targetDate;
    private boolean isDone;
    
	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Todo(int id, String user, String desc, Date targetDate, boolean isDone) {
		super();
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", user=" + user + ", desc=" + desc + ", targetDate=" + targetDate + ", isDone="
				+ isDone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + id;
		result = prime * result + (isDone ? 1231 : 1237);
		result = prime * result + ((targetDate == null) ? 0 : targetDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (id != other.id)
			return false;
		if (isDone != other.isDone)
			return false;
		if (targetDate == null) {
			if (other.targetDate != null)
				return false;
		} else if (!targetDate.equals(other.targetDate))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	

    
} 
