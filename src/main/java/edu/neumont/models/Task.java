package edu.neumont.models;

import java.time.LocalDateTime;

public class Task {
	private static int ID = 0;
	private int task_id, slate_id;
	private String task_name, task_description;
	private LocalDateTime deadline;
	
	public Task(int slate_id,String task_name, String task_description, LocalDateTime deadline) {
		this.task_id = ID;
		this.slate_id = slate_id;
		this.task_name = task_name;
		this.task_description = task_description;
		this.deadline = deadline;
		ID++;
	}
	public Task() {
		// TODO Auto-generated constructor stub
	}


	public int getSlate_id() {
		return slate_id;
	}
	public void setSlate_id(int slate_id) {
		this.slate_id = slate_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTask_description() {
		return task_description;
	}
	public void setTask_description(String task_description) {
		this.task_description = task_description;
	}
	public LocalDateTime getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int id) {
		this.task_id = id;
	}
	@Override
	public String toString() {
		return task_name + " " + task_description;
	}
	
}
