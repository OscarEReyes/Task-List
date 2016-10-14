package tasklist.task.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
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

	  /**
	   * Gives main application a reference to itself.
	   */
	  
	  public void setMainApp(MainApp mainApp) {
		  this.mainApp = mainApp;

	      // Add observable list data to the table
	      taskTable.setItems(mainApp.getTaskData());
	  }
	  
	  /**
	   * Sets text for labels that display details of task.

	   */
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
	  
	  /**
	   * Delete task
	   * Called on delete button click
	   * Sets warning if no task was selected
	   */

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
	  
	  /**
	   * Creates new task with its number on the list as part of the name
	   * Adds task to taskData if the user clicks ok.
	   */
	  
	  @FXML
	  private void handleNewTask() {
		  int taskNumber = mainApp.getTaskData().size() + 1;
	      Task newTask = new Task("Task " + taskNumber);
	      boolean okClicked = mainApp.showTaskEditDialog(newTask);
	      if (okClicked) {
	          mainApp.getTaskData().add(newTask);
	      }
	  }

	  /**
	   * Handles displaying details of selected task.
	   * Shows display if task exists
	   * Shows the user an error window if no task was selected.
	   */
	  
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
	  
	  /**
	   * Sets selected task to Done an updates status column
	   * Alerts user if no task was selected
	   */
	  
	  @FXML
	  private void handleSetComplete() {
		  Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
		  if (selectedTask != null){
			  selectedTask.setStatus("Done");
			  statusColumn.setText(selectedTask.getStatus());
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
