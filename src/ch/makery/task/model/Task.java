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
	
	oid setTaskName(String name){
		taskName = name;
	}
	void setDescription(String desc){
		description = desc;
	}
	void setDueDate(String date){
		dueDate = date;
	}
	void setLocation(String loc){
		location = loc;
	}
	void setPriority(char prior){
		priority = prior;
	}
	void setStatus(char sts){
		status = sts;
	}
	
	
	String getTaskName(){
		return taskName;
	}
	String getDescription(){
		return description;
	}
	String getDueDate(){
		return dueDate;
	}
	String getLocation(){
		return location;
	}
	String getPriority(){
		return priority;
	}
	String getStatus(){
		return status;
	}
}

}
