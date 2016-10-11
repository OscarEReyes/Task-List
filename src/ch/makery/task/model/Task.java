package ch.makery.task.model;

public class Task {
	private String taskName;
	private String description;
	private String dueDate;
	private String location;
	private String priority;
	private String status;
	
	public Task (String name){
		taskName = name;
		description = "No description";
		dueDate = "No due date";
		location = "No location";
		priority = "n";
		status = "i";
	}
}
