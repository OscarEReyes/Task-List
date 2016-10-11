package tasklist.task.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
	private final StringProperty taskName;
	private final StringProperty description;
	private final StringProperty dueDate;
	private final StringProperty location;
	private final StringProperty priority;
	private final StringProperty status;
	
	public Task (String name){
		taskName = new SimpleStringProperty(name);
		description = new SimpleStringProperty("No description");
		dueDate = new SimpleStringProperty("No due date");
		location = new SimpleStringProperty("No location");
		priority = new SimpleStringProperty("n");
		status = new SimpleStringProperty("i");
	}
	
	public void setTaskName(String name){
		taskName.set(name);
	}
	
	public void setDescription(String desc){
		description.set(desc);
	}
	
	public void setDueDate(String date){
		dueDate.set(date);
	}
	
	public void setLocation(String loc){
		location.set(loc);
	}
	
	public void setPriority(String prior){
		priority.set(prior);
	}
	
	public void setStatus(String sts){
		status.set(sts);
	}
	
	
	public String getTaskName(){
		return taskName.get();
	}
	
	public String getDescription(){
		return description.get();
	}
	public String getDueDate(){
		return dueDate.get();
	}
	
	public String getLocation(){
		return location.get();
	}
	
	public String getPriority(){
		return priority.get();
	}
	
	public String getStatus(){
		return status.get();
	}
	
	public StringProperty TaskNameProperty(){
		return taskName;
	}
	
	public StringProperty DescriptionProperty(){
		return description;
	}
	
	public StringProperty DueDateProperty(){
		return dueDate;
	}
	
	public StringProperty LocationProperty(){
		return location;
	}
	
	public StringProperty PriorityProperty(){
		return priority;
	}
	
	public StringProperty StatusProperty(){
		return status;
	}
}


