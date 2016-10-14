package tasklist.task.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The purpose of this class is to help "wrap" a task list.
 * It will be used to save a task list in XML.
 */
@XmlRootElement(name = "tasks")

public class TaskListWrapper {
	private List<Task> tasks;
	@XmlElement(name = "task")
	public List<Task> getTasks() {
		return tasks;
	}

    public void setTasks(List<Task> tasks) {
    	this.tasks = tasks;
	}

}
