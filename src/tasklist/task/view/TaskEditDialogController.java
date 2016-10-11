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
		descriptionField.setText(task.getDescription());
		dueDateField.setText(task.getDueDate());
		priorityField.setText(task.getPriority());
		locationField.setText(task.getLocation());
	}
	
	private boolean OkIsClicked() {
		return okClicked;
	}
	
	@FXML
	private void onOk(){
		if (checkInputValidity()) {
			task.setTaskName(nameField.getText());
			task.setDescription(descriptionField.getText());
			task.setDueDate(dueDateField.getText());
			task.setPriority(priorityField.getText());
			task.setLocation(locationField.getText());
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	 @FXML
	    private void onCancel() {
	        dialogStage.close();
	    }
	 
	private boolean checkInputValidity() {
        if (nameField.getText() == null || nameField.getText().length() == 0) {
        	// Alert user of error.
        	
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("No Task Name Given");
            alert.setHeaderText("Please Provide A Task Name");
            alert.setContentText("No valid first name!\n");

            alert.showAndWait();

            return false; 
            
        } else {
            return true;
        }
	}
}
