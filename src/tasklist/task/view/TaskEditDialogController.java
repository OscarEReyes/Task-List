package tasklist.task.view;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tasklist.task.model.Task;

public class TaskEditDialogController {
	
	@FXML
	private TextField nameField;
	@FXML
	private TextField descriptionField;
	@FXML
	private TextField dueDateField;
	@FXML
	private TextField priorityField;
	@FXML
	private TextField locationField;
	
	private Stage dialogStage;
	private Task task;
	private boolean okClicked;
	
	@FXML
	private void initialize() {
	
	}
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	
	public void setTask(Task task){
		this.task = task;
		
		nameField.setText(task.getTaskName());
		descriptionField.setText(task.getDescription())
		dueDateField.setText(task.getDueDate());
		priorityField.setText(task.getPriority());
		locationField.setText(task.getLocation());
	}
	
	private boolean OkIsClicked() {
		return okClicked;
	}
	
	
	
	
	
	
}
