package tasklist.task.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import tasklist.task.MainApp;
import tasklist.task.model.Task;

public class TaskOverviewController {
	 @FXML
	 private TableView<Task> taskTable;
	 
	 @FXML
	 private TableColumn<Task, String> statusColumn;
	 
	 @FXML
	 private TableColumn<Task, String> nameColumn;
	 
	 @FXML
	 private Label descriptionLabel;
	 
	 @FXML
	 private Label dueDateLabel;
	 
	 @FXML
	 private Label priorityLabel;
	 
	 @FXML
	 private Label locationLabel;
	 
	 private MainApp mainApp;
	 
	 // Class constructor, called before the initialize() method
	 public TaskOverviewController() {
	 }

	    /**
	     * Initializes the controller class. 
	     * Called once the FXML class has been loaded.
	     */
	 
	  @FXML
	  private void initialize() {
	        // Initialize the task table with the two columns.
		  	// -> chosen overPropertyValueFactory because it is type-safe
		  
	        statusColumn.setCellValueFactory(cellData -> cellData.getValue().StatusProperty());
	        nameColumn.setCellValueFactory(cellData -> cellData.getValue().TaskNameProperty());
	        
	        // Clear task details.
	        showTaskDetails(null);

	        // Listen for selection changes and update labels upon changes.
	        taskTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showTaskDetails(newValue));
	  }

	  // Called by the main application so that it can have a reference to itself.
	  public void setMainApp(MainApp mainApp) {
		  this.mainApp = mainApp;

	      // Add observable list data to the table
	      taskTable.setItems(mainApp.getTaskData());
	  }
	  
	  private void showTaskDetails(Task task) {
		    if (task!= null) {
		        // Display every task detail in its appropriate label
		        descriptionLabel.setText(task.getDescription());
		        dueDateLabel.setText(task.getDueDate());
		        locationLabel.setText(task.getLocation());
		        priorityLabel.setText(task.getPriority());

		    } else {
		        // Clear labels if task is null. 
		        descriptionLabel.setText("");
		        dueDateLabel.setText("");
		        locationLabel.setText("");
		        priorityLabel.setText("");
		    }
		}
	  
	  // Delete task
	  // Called on delete button click

	  @FXML
	  private void handleDeleteTask() {
	      int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();

	      if (selectedIndex >= 0) {
	    	  // Delete selected item
	          taskTable.getItems().remove(selectedIndex);
	      } else {
	    	  // Alert user he has not selected a task to delete.
	          Alert alert = new Alert(AlertType.WARNING);
	          alert.initOwner(mainApp.getPrimaryStage());
	          
	          alert.setTitle("No Selection Made");
	          alert.setHeaderText("No Task Was Selected");
	          alert.setContentText("Please select a task from the list.");

	          alert.showAndWait();
	      }
	  }
	  

	  @FXML
	  private void handleNewTask() {
	      Task newTask = new Task("Unnamed Task");
	      boolean okClicked = mainApp.showTaskEditDialog(newTask);
	      if (okClicked) {
	          mainApp.getTaskData().add(newTask);
	      }
	  }


	  @FXML
	  private void handleEditTask() {
	      Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
	      if (selectedTask != null) {
	          boolean okClicked = mainApp.showTaskEditDialog(selectedTask);
	          if (okClicked) {
	              showTaskDetails(selectedTask);
	          }

	      } else {
	          // Warn user of error.
	          Alert alert = new Alert(AlertType.WARNING);
	          alert.initOwner(mainApp.getPrimaryStage());
	          alert.setTitle("Error: No Task Selected");
	          alert.setContentText("Please select a task from the list.");
	          
	          // Show alert an wait for response
	          alert.showAndWait();
	      }
	  }
	  
	  
}
