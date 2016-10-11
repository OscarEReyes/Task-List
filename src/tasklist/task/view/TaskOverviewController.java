package tasklist.task.view;

import javafx.fxml.FXML;
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
	 private TableColumn<Task, String> descriptionColumn;
	 
	 @FXML
	 private TableColumn<Task, String> dueDateColumn;
	 
	 @FXML
	 private TableColumn<Task, String> priorityColumn;
	 
	 @FXML
	 private TableColumn<Task, String> locationColumn;
	 
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
	        // Initialize the person table with the two columns.
		  	// -> chosen overPropertyValueFactory because it is type-safe
	        statusColumn.setCellValueFactory(cellData -> cellData.getValue().StatusProperty());
	        nameColumn.setCellValueFactory(cellData -> cellData.getValue().TaskNameProperty());
	        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().DescriptionProperty());
	        dueDateColumn.setCellValueFactory(cellData -> cellData.getValue().DueDateProperty());
	        locationColumn.setCellValueFactory(cellData -> cellData.getValue().LocationProperty());
	  }

	  // Called by the main application so that it can have a reference to itself.
	  public void setMainApp(MainApp mainApp) {
		  this.mainApp = mainApp;

	      // Add observable list data to the table
	      taskTable.setItems(mainApp.getTaskData());
	  }

}
