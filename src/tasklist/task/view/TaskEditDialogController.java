package tasklist.task.view;

package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tasklist.task.model.Task;
import ch.makery.address.model.Person;

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
	
	
	
	
	
}
